package tn.esprit.spring.AhmedGuedri.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.Repositories.*;
import tn.esprit.spring.AhmedGuedri.entities.*;
import tn.esprit.spring.AhmedGuedri.security.services.UserDetailsImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrdersRepository orderRepo;
    @Autowired
    ShoppingCartRepository panierRepo;
    @Autowired
    ProductsRepository productRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public List<Orders> retrieveAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public Orders updateOrders(Orders orders) {
        return orderRepo.save(orders);
    }

    @Override
    public void deleteOrders(Long id) {
         orderRepo.deleteById(id);
    }

    @Override
    public Orders addOrders(Long idShoppingCart ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findById(((UserDetailsImpl) auth.getPrincipal()).getId()).get();
        ShoppingCart shoppingCart=panierRepo.findById(idShoppingCart).get();
        Orders order=new Orders();
        List <Products> productsList=new ArrayList<>(shoppingCart.getProductsList());
        //update user
        List<Orders> ListOrders=new ArrayList<>(user.getUser_orders());

       // ListOrders.
        order.setBroughtDate(new Date());
        order.setConfirmation(true);
        order.setStatusOrders(OrdersType.Preparing);
        order.setShippingAdresse(user.getAdress());
        order.setShoppingCart(shoppingCart);
        order.setProductsList(productsList);
        //save Order
        orderRepo.save(order);
        ListOrders.add(order);
        user.setUser_orders(ListOrders);
        userRepo.save(user);
       // ListOrders.add(orders);
     //   user.setUser_orders(ListOrders);
   //     orders.setShoppingCart(user.getShoppingCart());
     //   orders.setStatusOrders(OrdersType.Preparing);
      //  ShoppingCart s= ShoppingCart.builder().productsList(null).build();
        //userRepo.save(user);
        //end update
        //CHANGEME
        // orders.setUser(user);
        return order;
    }

    @Override
    public Orders retrieveOrders(Long id) {
        Orders o = orderRepo.findById(id).orElse(null);
        return o;
    }

    @Override
    public Orders addPanierToOrder(Long panierId, Long orderId) {
        ShoppingCart panier = panierRepo.findById(panierId).orElse(null);
        Orders order = orderRepo.findById(orderId).orElse(null);
           // order.setPanier(panier);
           // order.setTotal(panier.getTotalPrice());
            orderRepo.save(order);

        return order;
    }
    @Override
    public Float TotalOrdersTVA (Long orderId){
        Orders order = orderRepo.findById(orderId).orElse(null);
        float total = 0;
       // total = (float) ((order.getPanier().getTotalPrice()*order.getTax())+ order.getPanier().getTotalPrice());
        return total;
    }


}
