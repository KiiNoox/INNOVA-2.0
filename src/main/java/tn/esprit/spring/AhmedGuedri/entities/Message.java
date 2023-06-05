package tn.esprit.spring.AhmedGuedri.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idmsg")
    private Long IdMsg;
    private String Text;
    private boolean visibility;
    @Temporal(TemporalType.TIMESTAMP)
    private Date Sent ;
    private boolean Seen;
    //Relation->ChaTRoom
    @ManyToOne
    @JsonBackReference
    ChatRoom chatRoom;


}

