package tn.esprit.spring.AhmedGuedri.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tax implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codetax")
    private Long CodeTax;
    private Float TaxableAmount;
    private String DescribeTax;
    //Relation --> Product
    @OneToMany(mappedBy = "tax")
    List<Products> ProductsList;
}

