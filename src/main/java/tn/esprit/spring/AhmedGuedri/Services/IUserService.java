package tn.esprit.spring.AhmedGuedri.Services;

//import com.sun.org.apache.xpath.internal.operations.Bool;
import tn.esprit.spring.AhmedGuedri.entities.ChatRoom;
import tn.esprit.spring.AhmedGuedri.entities.User;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> retrieveAllUsers();
    User addUser(User u); // Add User
     String updateUser(User u); // Update User
     String deleteUser(String Email); // Delete User

    String ActivateUser(String Email); // Activate User

    String VerifyUserToken(String Email,Long token); // Verify User Token
    //USER forgot password
    String ForgotPassword(String Email,Boolean EmailorPhone,String phonenumber); // Forgot Password
    String VerifyForgotPasswordToken(String Email,String PrevPass,String NewPass,Long token); // Verify Forgot Password Token

    List<String> TopTierSellers();
    void AntiBot();
    int NumberOfSubs();

    void Authenticate(String Email);

    Long TokenGenerator(int ends);

    String UserVerificationReturnEmail(HttpServletRequest request);

    String SendSMS(String to, String body);

    void AffectToChatRoom(String email, ChatRoom r);
    String AddUserToChatRoom(String email, Long r);
    Boolean VerifyUserInChatRoom(String email, Long r);
    public void SendAndReceive(String Sender,Long IdMsg);

    User GetUserInfo(String Email);

    //get all users
    List<User> getAllUsers();

    //userExists
    boolean userExists(String email);

}
