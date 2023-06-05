//create Currency Repository   
package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.AhmedGuedri.entities.Currency;
import tn.esprit.spring.AhmedGuedri.entities.CurrencyType;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency findByCurrencyType(CurrencyType currencyType);
    @Query("select c from Currency c where c.IdCurrency = :id")
    Currency findByIdCurrency(Long id);
}