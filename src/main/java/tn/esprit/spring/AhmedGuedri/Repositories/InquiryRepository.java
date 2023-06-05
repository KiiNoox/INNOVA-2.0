package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.AhmedGuedri.entities.Inquiry;
import tn.esprit.spring.AhmedGuedri.entities.ProductCategory;

import java.util.List;

public interface InquiryRepository extends CrudRepository<Inquiry, Long> {

    @Query("SELECT i FROM Inquiry i WHERE i.Category = :productCategory")
    public List<Inquiry>  getInquiryByCategory(@Param("productCategory") ProductCategory productCategory);

}

