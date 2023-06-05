package tn.esprit.spring.AhmedGuedri.Repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.AhmedGuedri.entities.Feedbacks;
import tn.esprit.spring.AhmedGuedri.entities.Products;
import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.List;

@Repository
public interface FeedbacksRepository extends JpaRepository<Feedbacks, Long> {
List<Feedbacks> findByFeedbacksProd(Products product);

    //JPQL Example
//    @Query("SELECT CONCAT (u.FirstName,u.LastName) FROM User u,Feedbacks b,Products p" +
//            " WHERE 3<(select AVG (f.Stars) FROM Feedbacks f WHERE u.Roles ='Supplier' AND b.feedbacksProd.IdProducts=p.IdProducts AND f.feedObject.Friendly =true OR f.feedObject.GoodQuality=true OR f.feedObject.Trusted=true) " +
//            "GROUP BY b.feedbacksProd.IdProducts")
@Query(value = "SELECT CONCAT (u.first_name) FROM user u,feedbacks b,products p \n" +
        "WHERE 3<(select AVG (f.Stars)\n" +
        "FROM Feedbacks f,feed_ object fb\n" +
        "WHERE u.Roles ='Supplier' AND b.feedbacks_prod_idproducts=p.idproducts  AND fb.friendly =true " +
        "OR fb.good_quality=true OR fb.trusted=true" +
        "     ORDER BY DESC AVG (f.Stars)) \n" +
        "GROUP BY u.id",nativeQuery = true)

    public List<String> TopTierSellers();
    //  "AND u.userProducts.IdProducts=b.feedbacksProd.IdProducts " +
}