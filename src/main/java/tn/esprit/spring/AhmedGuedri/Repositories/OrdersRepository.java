package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import tn.esprit.spring.AhmedGuedri.entities.Delivery;
import tn.esprit.spring.AhmedGuedri.entities.Orders;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {

    List<Orders> findByDelivery(Delivery d);
    Orders findOneByShoppingCart_User_Id(long id);
}

