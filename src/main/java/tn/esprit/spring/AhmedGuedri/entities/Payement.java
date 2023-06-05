package tn.esprit.spring.AhmedGuedri.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Payement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdPayment;
    @Enumerated(EnumType.STRING)
    private PaymentType PaymentType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date PaymentDate;
    private boolean ConfirmationPayments;
    //Relation Orders
    @OneToOne (mappedBy = "payment")
    @JsonBackReference
    Orders orders;
    //Relation Invoices
    @OneToOne
    Invoices PaymentInvoice;

    //stripe
    int created;

}
