package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.AhmedGuedri.entities.Delivery;
import tn.esprit.spring.AhmedGuedri.entities.StatusType;
import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
    List<Delivery> findDeliveryByDeliveredBy(User delivredBy);

    @Query("SELECT AVG(d.estimatedDate - d.StarDate) FROM Delivery d WHERE d.StatusType = :status")
    public Double findAverageTimeBetweenStartAndEstimatedDateByStatus(@Param("status") StatusType status);


}
