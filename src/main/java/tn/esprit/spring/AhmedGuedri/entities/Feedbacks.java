package tn.esprit.spring.AhmedGuedri.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Feedbacks implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idfeedbacks")
    private Long IdFeedBacks;
    private int Stars;
    @ManyToOne
    Products feedbacksProd;

    //Relation --> Feedobject
    @OneToOne
    FeedObject feedObject;

  
}

