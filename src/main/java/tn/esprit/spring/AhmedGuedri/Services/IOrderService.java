package tn.esprit.spring.AhmedGuedri.Services;

import tn.esprit.spring.AhmedGuedri.entities.Orders;

import java.util.List;

public interface IOrderService {
    List<Orders> retrieveAllOrders();

    public Orders updateOrders(Orders orders);

    void deleteOrders(Long id);

    Orders addOrders (Long idShoppingCart );

    Orders retrieveOrders(Long id);

    Orders addPanierToOrder(Long panierId, Long orderId);
    public Float TotalOrdersTVA (Long orderId);

}
