//implement entity class ShoppingCart
package tn.esprit.spring.AhmedGuedri.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Cart_Id;
   //relation Product
    @ManyToMany
    private List<Products> productsList;
    //Relation User
    @JsonBackReference
    @OneToOne
    private User user;
    public char[] getIdShoppingCart() {
        return Cart_Id.toString().toCharArray();
    }

}