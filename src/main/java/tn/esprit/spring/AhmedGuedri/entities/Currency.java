package tn.esprit.spring.AhmedGuedri.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Currency implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcurrency")
    private Long IdCurrency;
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
    @Temporal(TemporalType.DATE)
    private Date DateC ;
    private Float ExchangeRate;
    //Relation --> Products
    @ManyToMany (mappedBy = "CurrencyList")
    List<Products> ProductsList;
}

