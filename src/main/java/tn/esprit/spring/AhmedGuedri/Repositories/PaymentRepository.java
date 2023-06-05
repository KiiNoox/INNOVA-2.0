package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.AhmedGuedri.entities.Fees;
import tn.esprit.spring.AhmedGuedri.entities.Payement;
@Repository
public interface PaymentRepository extends CrudRepository<Payement,Long> {

}
