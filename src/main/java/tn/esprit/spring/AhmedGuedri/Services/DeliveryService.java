package tn.esprit.spring.AhmedGuedri.Services;
import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageResponse;
//import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.Repositories.DeliveryRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.OrdersRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.UserRepository;
import tn.esprit.spring.AhmedGuedri.entities.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeliveryService implements IDeliveryService{
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    private static final double EARTH_RADIUS = 6371; // in kilometers




    //api
    public double[] getAddressCoordinate(String address){
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("16dd58b0920043c695dd0b4e58be478f");
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(address);
        request.setMinConfidence(1);
        request.setNoAnnotations(false);
        request.setNoDedupe(true);
        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        return new double[] {response.getResults().get(0).getGeometry().getLat(),response.getResults().get(0).getGeometry().getLng()};

    }

    //theoreme
    public double calculateDistance(String address1,String address2) {
        double lat1=getAddressCoordinate(address1)[0];
        double lon1=getAddressCoordinate(address1)[1];
        double lat2=getAddressCoordinate(address2)[0];
        double lon2=getAddressCoordinate(address2)[1];
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }
    public Delivery assignDelivery(Long idOrder) {
        Orders orders=ordersRepository.findById(idOrder).orElse(null);
        String address=orders.getShippingAdresse();
       // List<User> deliverys = userRepository.findAll().stream().filter(d->d.getRoles().equals(RolesTypes.ROLE_DELIVERY)).collect(Collectors.toList());
        System.out.println(address);
        List<User> deliverys = userRepository.All_Delivery();
        System.out.println(deliverys.get(0).getFirstName());
        // List<User> deliverys=userRepository.find
        //api address
        double lat1, lon1;
        lat1 = getAddressCoordinate(address)[0];
        lon1 = getAddressCoordinate(address)[1];

        //map de livreur et distance entre livreur et lieu de livraison
        Map<User, Double> deliveryDistance = new HashMap<>();
        for (User delivery : deliverys) {
            double distance = calculateDistance(delivery.getAdress(),address);
            deliveryDistance.put(delivery, distance);
        }
        //why entry?
        List<Map.Entry<User, Double>> distanceList = new ArrayList<>(deliveryDistance.entrySet());
        distanceList.sort(Map.Entry.comparingByValue());
        User assignedDelivery=distanceList.get(0).getKey();
        List<Delivery> deliveryss=deliveryRepository.findDeliveryByDeliveredBy(assignedDelivery);
        System.out.println(deliveryss);
        Delivery delivery;
        if(deliveryss.size()==0){
            delivery=new Delivery();
            delivery.setDeliveredBy(assignedDelivery);
            delivery.setStarDate(new Date());
            delivery.setEstimatedDate(new Date());
            delivery.setOrdersList(Arrays.asList(orders));
            delivery.setStatusType(StatusType.Pending);

            deliveryRepository.save(delivery);
        }
        else{
            delivery=deliveryss.get(deliveryss.size()-1);
            if(delivery.getOrdersList().size()>4){
                delivery=new Delivery();
                delivery.setDeliveredBy(assignedDelivery);
                delivery.setStarDate(new Date());
                delivery.setEstimatedDate(new Date());
                delivery.setOrdersList(Arrays.asList(orders));
                delivery.setStatusType(StatusType.Pending);
                deliveryRepository.save(delivery);
            }
            else{
                delivery.getOrdersList().add(orders);
            }
        }


        orders.setDelivery(delivery);
        orders.setStatusOrders(OrdersType.Delivering);
        ordersRepository.save(orders);
        return delivery;




    }
    public List<Orders> getListOrdersPath(Long idDelivery){
        Delivery delivery=deliveryRepository.findById(idDelivery).orElse(null);
        List<Orders> ordersList=delivery.getOrdersList();
        List<String> places=new ArrayList<>();
        places.add(0,delivery.getDeliveredBy().getAdress()); //adresse du livreur

        for(Orders orders:ordersList){
            places.add(orders.getShippingAdresse());
        }
        //places.add(delivery.getDeliveredBy().getAdress());
        //build distance graph
        int n=places.size();
        double[][] graph=new double[n][n];
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                if(i==j){
                    graph[i][j]=0;
                }
                else{
                    graph[i][j]=calculateDistance(places.get(i), places.get(j));
                    graph[j][i]=calculateDistance(places.get(i), places.get(j));
                }

            }

        }
        //
        System.out.println(Arrays.deepToString(graph));
        List<Integer> resulttour=tsp2opt(graph,0);

        System.out.println(resulttour);
        List<Orders> ordersListPath=new ArrayList<>();
        for(int i=1;i<resulttour.size();i++){
            ordersListPath.add(ordersList.get(resulttour.get(i)-1));
                //why i-1
        }


        return ordersListPath;
    }
    public static List<Integer> tsp2opt(double[][] graph, int start) {
        // Generate initial tour
        List<Integer> tour = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            if (i != start) {
                tour.add(i);
            }
        }
        Collections.shuffle(tour);
        tour.add(0, start);

        // Iterate until no further improvements can be made
        boolean improved = true;
        while (improved) {
            improved = false;
            for (int i = 1; i < tour.size() - 2; i++) {
                for (int j = i + 1; j < tour.size() - 1; j++) {
                    double delta = (graph[tour.get(i - 1)][tour.get(j)] + graph[tour.get(i)][tour.get(j + 1)]) -
                            (graph[tour.get(i - 1)][tour.get(i)] + graph[tour.get(j)][tour.get(j + 1)]);
                    if (delta < 0) {
                        // Swap edges
                        Collections.reverse(tour.subList(i, j + 1));
                        improved = true;
                    }
                }
            }
        }

        return tour;
    }

    public List<Orders> normaleOrders(Long idDelivery){
        Delivery delivery=deliveryRepository.findById(idDelivery).orElse(null);
        return delivery.getOrdersList();
    }
    public List<Delivery> deleteDelivery(Long idDelivery){
        Delivery delivery=deliveryRepository.findById(idDelivery).orElse(null);
        for(Orders orders:delivery.getOrdersList()){
            orders.setDelivery(null);
            orders.setStatusOrders(OrdersType.Canceled);
            ordersRepository.save(orders);
        }

        deliveryRepository.deleteById(idDelivery);

        return deliveryRepository.findAll();
    }
    public List<Delivery> deleteOrderFromDelivery(Long idOrder){
        Orders orders=ordersRepository.findById(idOrder).orElse(null);
        orders.setDelivery(null);
        orders.setStatusOrders(OrdersType.Canceled);
        ordersRepository.save(orders);
        return deliveryRepository.findAll();
    }
    public Delivery finishingDelivery(Long idDelivery, Date endDate, StatusType statusType){
        Delivery delivery=deliveryRepository.findById(idDelivery).orElse(null);
       List <Orders> orders= ordersRepository.findByDelivery(delivery);
       for (Orders o:orders) {
           o.setStatusOrders(OrdersType.Delivered);
           ordersRepository.save(o);
       }
        delivery.setEstimatedDate(endDate);
        delivery.setStatusType(statusType);
        deliveryRepository.save(delivery);
        return delivery;
    }
    public List<Delivery> displayDelivery(){
        return deliveryRepository.findAll();
    }
    public double avreageTime(){
        return deliveryRepository.findAverageTimeBetweenStartAndEstimatedDateByStatus(StatusType.Succeeded);
    }
    public String mostFrequentAdresse(){
        List<String> places=new ArrayList<>();
        for(Delivery delivery:deliveryRepository.findAll()){
            for(Orders orders:delivery.getOrdersList()){
                places.add(orders.getShippingAdresse());
            }
        }
        Map<String,Integer> placeCount=new HashMap<>();
        for(String place:places){
            placeCount.put(place,placeCount.getOrDefault(place,0)+1);
        }
        String mostFrequentPlace=null;

        int highestCount=-1;
        for(Map.Entry<String,Integer> entry:placeCount.entrySet()){
            if(entry.getValue()>highestCount){
                highestCount=entry.getValue();
                mostFrequentPlace=entry.getKey();
            }
        }
        return "Most frequent place delivered to:"+mostFrequentPlace+" with highest count="+highestCount;
    }
    public void cancelDelivery(Long idDelivery){
        Delivery d=deliveryRepository.findById(idDelivery).orElse(null);
        d.setStatusType(StatusType.Canceled);
        deliveryRepository.save(d);
    }


}
