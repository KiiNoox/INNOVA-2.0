package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.AhmedGuedri.entities.DetailedOrders;

@Repository
public interface DetailedOrdersRepository extends CrudRepository<DetailedOrders, Long> {
    @Modifying
    @Query("DELETE FROM DetailedOrders")
    void deleteAll();
    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE detailed_orders AUTO_INCREMENT = 1", nativeQuery = true)
    void resetId();

    /*List<DetailedOrders> getDetailedOrdersBySupplier(Long supplier);
    List<DetailedOrders> findBySupplier(Long supplier);*/
}