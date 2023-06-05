package tn.esprit.spring.AhmedGuedri.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.AhmedGuedri.Repositories.UserRepository;
import tn.esprit.spring.AhmedGuedri.Services.IMessageService;
import tn.esprit.spring.AhmedGuedri.Services.IUserService;
import tn.esprit.spring.AhmedGuedri.Services.MessageService;
import tn.esprit.spring.AhmedGuedri.entities.ChatRoom;
import tn.esprit.spring.AhmedGuedri.entities.Message;
import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.List;


@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/message/")
public class MessageController {


    private IMessageService iMessageService;

    private MessageService messageService;

    private IUserService iUserServices;
    private final UserRepository userRepository;

    @GetMapping("/Retrieve-Messages/{id}")
    public Message RetrieveMessages(@PathVariable("id") Long Message) {
        return iMessageService.RetrieveMessages(Message);
    }



    @PostMapping("addMessage")
    @MessageMapping("/ws/{IdChatRoom}/{email}")
    @SendTo("/chat/emit")
    public String AjouterMessages(@RequestBody Message Message, @DestinationVariable Long IdChatRoom, @DestinationVariable String email) {
        System.out.println("El Message : "+Message);
        System.out.println("El Message : "+IdChatRoom);
        System.out.println("El Message : "+email);
        System.out.printf(String.valueOf(Message));
        if (iUserServices.VerifyUserInChatRoom(email, IdChatRoom)) {
            Message m = iMessageService.AjouterMessages(Message, IdChatRoom);
            iUserServices.SendAndReceive(email, m.getIdMsg());
            return "Message added";
        } else {
            return "User not in chatroom";
        }
        // iMessageService.AjouterMessages(Message,IdChatRoom);
        // 5L is the id of the user recepteur


//        return new ResponseEntity<Message>(HttpStatus.CREATED);


    }


    @PutMapping("/updateidMessage")
    public Message updateMessage(@RequestBody Message e, Long IdChatRoom) {

        return iMessageService.UpdateMessages(e, IdChatRoom);

    }

    @GetMapping("/removeMessage/{idMessage}")
    public Message RemoveMessages(@PathVariable("idMessage") Long idMessage) {

        return iMessageService.RemoveMessages(idMessage);

    }

    @GetMapping("/countMessagesByChatRoom/{idChatRoom}")
    public int countMessagesByChatRoom(@PathVariable("idChatRoom") Long idChatRoom) {
        return iMessageService.countMessagesByChatRoom(idChatRoom);
    }
     /*
     @PostMapping("/insertMessage")
     public void insertMessage(@RequestBody Long iduser, Long sent_list_idmsg) {
        iMessageService.insertMessage(iduser,sent_list_idmsg);
     }*/

    //ListMessagesByChatRoom
    @GetMapping("/ListsentMessagesByChatRoom")
    public List<Object[]> ListsentMessagesByChatRoom(Long id) {
        return iMessageService.ListsentMessagesByChatRoom(id);

    }

    @GetMapping("/ListreceivedMessagesByChatRoom")
    public List<Object[]> ListreceivedMessagesByChatRoom() {
        return iMessageService.ListreceivedMessagesByChatRoom();

    }

    //ListMessagesByChatRoom
    @GetMapping("/ListMessagesByChatRoom/{idChatRoom}")
    public ChatRoom ListMessagesByChatRoom(@PathVariable("idChatRoom") Long id) {
        return iMessageService.ListMessagesByChatRoom(id);
    }



}