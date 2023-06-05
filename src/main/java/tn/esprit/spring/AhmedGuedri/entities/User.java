package tn.esprit.spring.AhmedGuedri.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamicUpdate
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String firstName;
    private String lastName;
    private Date BirthDate;
    //relation Roles
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();
    private String Adress;
    private String email;
    //Anti Bot + PWD Recover
    private Long Token;
    private Long PhoneNumber;
    private String Img_URL;
    private boolean Enabled;

    private String Country;
    @Temporal(TemporalType.DATE)
    private Date joined;
//Relation Messages
    @ManyToMany(fetch = FetchType.LAZY)
    List<Message> SentList;
    @ManyToMany(fetch = FetchType.LAZY)

    List<Message> ReceivedList;
    //Relation PWD
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    HashedPWD hashedPWD;
    //Relation fees
    @OneToOne
    Fees fees;

    //Relation Inquiry
    @JsonManagedReference
    @OneToMany(mappedBy = "userInquiries")
    List<Inquiry> InquiryList;
    //Relation Product
    @ManyToMany(mappedBy = "userProducts")
    List<Products> ProductList;
    //Relation Orders
    @OneToMany(cascade = CascadeType.ALL)
    //@JsonManagedReference
    List<Orders> User_orders;
    @JsonManagedReference
    @OneToOne
    ShoppingCart shoppingCart;

    @OneToOne
    @JsonBackReference
    Delivery delivery;
    @ManyToMany(fetch = FetchType.EAGER)
    List<ChatRoom> chatRooms;
}

