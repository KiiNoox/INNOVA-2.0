package tn.esprit.spring.AhmedGuedri.Services;

import org.springframework.data.repository.query.Param;
import tn.esprit.spring.AhmedGuedri.entities.ChatRoom;
import tn.esprit.spring.AhmedGuedri.entities.Message;
import tn.esprit.spring.AhmedGuedri.entities.User;


import java.util.List;

public interface IMessageService {

    List<Message> retrieveAllMessages();

    Message AjouterMessages (Message Message, Long IdChatRoom);

    Message UpdateMessages (Message e,Long ChatRoom);


    Message RetrieveMessages(Long idMessage);


    Message RemoveMessages(Long idMessage);

public int countMessagesByChatRoom(Long idChatRoom);
  //  public void insertMessage(Long iduser, Long sent_list_idmsg);
  List<Object[]>  ListsentMessagesByChatRoom(Long id);
  //ListreceivedMessagesByChatRoom
    List<Object[]>  ListreceivedMessagesByChatRoom();

  //  public void recieveMessage(Long iduser,Long received_list_idmsg);
    //ListMessagesByChatRoom
  ChatRoom ListMessagesByChatRoom(Long id);

}
