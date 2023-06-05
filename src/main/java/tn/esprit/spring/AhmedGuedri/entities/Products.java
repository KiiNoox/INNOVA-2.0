package tn.esprit.spring.AhmedGuedri.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Products implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idproducts")
    private Long IdProducts;
    private String NameProducts;
    private String Description;
    private Float Price;
    private String AdressProducts;
    private String RegionProducts;
    private boolean Available;
    private int NumberOfStock;
    //enum annotation
    @Enumerated(EnumType.STRING)
    private ProductCategory Category;
    //Relation --> Inquiry
    @JsonIgnore
    @ManyToMany(mappedBy = "ProductList")
    List<Inquiry> InquiryList;
    //Relation --> USER
    @JsonIgnore
    @ManyToMany
    List<User> userProducts;
    //Relation -->Currency
    @JsonIgnore
    @ManyToMany
    List<Currency> CurrencyList;
    //Relation --> Tax
    @JsonIgnore
    @ManyToOne
    Tax tax;
    //Relation --> Feedbacks
    @JsonIgnore
    @OneToMany(mappedBy = "feedbacksProd")
    List<Feedbacks> FeedbackList;

    //Relation --> Orders
    @ManyToMany
    List<Orders> Product_order;


}

