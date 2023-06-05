package tn.esprit.spring.AhmedGuedri.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Inquiry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idinquiry")
    private Long IdInquiry;
    private String Description;
    private String Status;
    //enum product category
    @Enumerated(EnumType.STRING)
    private ProductCategory Category;
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreateDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ExpireDate;
    //Relation-->User
    @JsonBackReference
    @ManyToOne
    User userInquiries;
    //Relation-->Product
    @ManyToMany
    List<Products> ProductList;

}

