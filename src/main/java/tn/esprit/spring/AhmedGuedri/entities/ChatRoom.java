package tn.esprit.spring.AhmedGuedri.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "ChatRoom")
public class ChatRoom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idchatroom")
    private Long IdChatRoom;
    private String NameChat;
    private Boolean visibility;
    private Boolean IsActive;
    //Relation-->Message
    @JsonIgnore
    @OneToMany(mappedBy = "chatRoom")
    @JsonManagedReference
    List<Message> messages;
    //Relation-->User

}

