package tn.esprit.spring.AhmedGuedri.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HashedPWD implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long PassId;
    //RISK 90%
    private String Password;
    @Temporal(TemporalType.DATE)
    private Date ChangeDate;
    @JsonBackReference
    @OneToOne
    User user;

}

