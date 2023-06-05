package tn.esprit.spring.AhmedGuedri.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idorders")
    private Long IdOrders;
    @Enumerated(EnumType.STRING)
    private OrdersType StatusOrders;
    @Temporal(TemporalType.TIMESTAMP)
    private Date BroughtDate;
    private boolean Confirmation;
    //for delivery usage
    private String shippingAdresse;
    //Delivery
    @ManyToOne
    Delivery delivery;

    //Relation Payment
    @OneToOne
    @JsonManagedReference
    Payement payment;
    //Relation -->Invoices
    @OneToOne
    Invoices OrdersInvoice;


    //Relation Product
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Products> productsList;

    //Relation-->ShoppingCart
    @OneToOne
    ShoppingCart shoppingCart;

}

