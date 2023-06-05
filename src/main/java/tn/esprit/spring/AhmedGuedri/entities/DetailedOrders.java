package tn.esprit.spring.AhmedGuedri.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetailedOrders implements Serializable {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_d", nullable = false)
    private Long idOrderD;
    private Long ordernumber;
    private Long Product;
    private Float Price;
    private CurrencyType Currency;
    private Long Supplier;
    private Date BoughtDate;
}
