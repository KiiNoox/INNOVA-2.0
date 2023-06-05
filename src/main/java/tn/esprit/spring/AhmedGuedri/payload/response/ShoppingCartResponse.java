package tn.esprit.spring.AhmedGuedri.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.AhmedGuedri.entities.Products;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShoppingCartResponse {
    long id;
    List<Map<String ,Object >> products;
    float total;
    float totalTax;
    float totalAfterTax;

}
