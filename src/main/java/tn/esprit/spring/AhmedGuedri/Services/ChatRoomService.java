package tn.esprit.spring.AhmedGuedri.Services;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tn.esprit.spring.AhmedGuedri.Repositories.ChatRoomRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.InquiryRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.ProductsRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.UserRepository;
import tn.esprit.spring.AhmedGuedri.entities.*;


import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class ChatRoomService implements IChatRoomService {

    ChatRoomRepository chatRoomRepository;
    ProductsRepository productsRepository;
    UserRepository userRepository;
    InquiryRepository inquiryRepository;
    IUserService userService;


    @Override
    public List<ChatRoom> retrieveAllChatrooms() {
        return (List<ChatRoom>) chatRoomRepository.findAll();
    }



    @Override
    public ChatRoom AjouterChatrooms(String email,@RequestBody ChatRoom ChatRoom) {
         chatRoomRepository.save(ChatRoom);
        userService.AffectToChatRoom(email,ChatRoom);
        return ChatRoom;

    }

    @Override
    public ChatRoom Updatechatrooms(ChatRoom e) {

        return chatRoomRepository.save(e);
    }




    @Override
    public ChatRoom retrieveChatRoom(String id) {
        return chatRoomRepository.findById(Long.parseLong(id)).get();
    }


    @Override
    public List<ChatRoom> retrieveAllChatroomsSortedByName(String name) {
        List<ChatRoom> chatroom =chatRoomRepository.retrieveAllChatroomsSortedByName(name);
        for (ChatRoom chatRoom : chatroom) {
            System.out.println(chatRoom);
        }
        return chatroom;
    }

    @Override
    public List<User> searchUserByFirstLastName(String First, String Last) {
        return userRepository.findByFirstNameOrLastNameContainingIgnoreCase(First, Last);
    }

    @Override
    public List<User> searchUserRoles(RolesTypes role) {
        return userRepository.searchUserRoles(role);
    }

    @Override
    public List<Inquiry> getInquiryByCategory(ProductCategory productCategory) {
        return inquiryRepository.getInquiryByCategory(productCategory);
    }
//count chatroom by user
    @Override
    public int countChatRoomByUser(Long userid) {
        return userRepository.countChatRoomByUser(userid);
    }
    //list users by chatroom
    @Override
    public List<Object[]> ListUsersByChatRoom(Long chatroomid) {
        return chatRoomRepository.ListUsersByChatRoom(chatroomid);
    }

    @Override
    public List<ChatRoom> GetALLChatroomsforUser(String useremail) {

        User u = userRepository.findByEmailEquals(useremail);
        List<ChatRoom> chatRooms2 = new ArrayList<>();
        List<ChatRoom> chatRooms = u.getChatRooms();
        System.out.println(u.getChatRooms());
        for (ChatRoom chatRoom : chatRooms) {
            if (chatRoom.getVisibility() == true)
                chatRooms2.add(chatRoom);
        }
        return chatRooms2;
    }

    @Override
    public void deleteChatRoom(Long id) {
        ChatRoom cr= chatRoomRepository.findById(id).get();
        cr.setVisibility(false);
        chatRoomRepository.save(cr);
    }

    @Override
    public ChatRoom searchbyid(Long id) {
        return chatRoomRepository.findById(id).get();
    }
//count chatroom by user
    @Override
    public int countChatRoomByUser(String email) {

        return chatRoomRepository.countChatRoomByUser(email);
    }

}

