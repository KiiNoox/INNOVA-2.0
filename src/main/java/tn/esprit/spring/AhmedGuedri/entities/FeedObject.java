package tn.esprit.spring.AhmedGuedri.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FeedObject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idfeedobject")
    private Long IdFeedObject;
    private boolean GoodQuality;
    private boolean Trusted;
    private boolean Friendly;
    private String Description;
}

