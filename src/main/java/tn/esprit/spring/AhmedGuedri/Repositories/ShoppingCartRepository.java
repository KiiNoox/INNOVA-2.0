package tn.esprit.spring.AhmedGuedri.Repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.AhmedGuedri.entities.Products;
import tn.esprit.spring.AhmedGuedri.entities.ShoppingCart;
import tn.esprit.spring.AhmedGuedri.entities.User;

@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {


    ShoppingCart findShoppingCartByUser(User user);
}

