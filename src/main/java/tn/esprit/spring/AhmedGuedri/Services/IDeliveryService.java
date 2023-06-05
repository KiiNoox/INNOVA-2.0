package tn.esprit.spring.AhmedGuedri.Services;

import tn.esprit.spring.AhmedGuedri.entities.Delivery;
import tn.esprit.spring.AhmedGuedri.entities.Orders;
import tn.esprit.spring.AhmedGuedri.entities.StatusType;
import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.Date;
import java.util.List;

public interface IDeliveryService {
    public List<Orders> getListOrdersPath(Long idDelivery);
    List<Orders> normaleOrders(Long idDelivery);
    Delivery assignDelivery(Long idOrder);
    List<Delivery> deleteDelivery(Long idDelivery);
    Delivery finishingDelivery(Long idDelivery, Date endDate, StatusType statusType);
    List<Delivery> displayDelivery();
    double avreageTime();
    List<Delivery> deleteOrderFromDelivery(Long idOrder);
    public String mostFrequentAdresse();
    double[] getAddressCoordinate(String address);
    void cancelDelivery(Long idDelivery);
}
