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
public class Delivery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="iddelivery")
    private Long IdDelivery;
    @Temporal(TemporalType.DATE)
    private Date StarDate ;
    @Temporal(TemporalType.DATE)
    private Date estimatedDate ;
    @Enumerated(EnumType.STRING)
    private StatusType StatusType;


    @OneToOne
    @JsonManagedReference
    User deliveredBy;
    @OneToMany (mappedBy = "delivery")
    @JsonIgnore
    List<Orders> OrdersList;

    public static class Role {
    }
}

