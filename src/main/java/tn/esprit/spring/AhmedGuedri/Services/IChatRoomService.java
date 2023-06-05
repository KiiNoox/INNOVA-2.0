package tn.esprit.spring.AhmedGuedri.Services;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.AhmedGuedri.entities.*;


import java.util.List;

public interface IChatRoomService {
    List<ChatRoom> retrieveAllChatrooms();
    ChatRoom AjouterChatrooms (String email,ChatRoom chatroom);

    ChatRoom Updatechatrooms (ChatRoom e);



    public ChatRoom retrieveChatRoom(String id);
    public List<ChatRoom> retrieveAllChatroomsSortedByName(String name);

    public List<User> searchUserByFirstLastName(String First,String Last);
    public List<User> searchUserRoles(RolesTypes role);

    List<Inquiry> getInquiryByCategory(ProductCategory productCategory);
    public int countChatRoomByUser(Long userid);
    public List<Object[]> ListUsersByChatRoom(Long chatroomid);

    //GetALLChatroomsforUser
    public List<ChatRoom> GetALLChatroomsforUser(String useremail);
//delete chatroom
    public void deleteChatRoom(Long id);

    public ChatRoom searchbyid(Long id);

 public int countChatRoomByUser( String email);
}
