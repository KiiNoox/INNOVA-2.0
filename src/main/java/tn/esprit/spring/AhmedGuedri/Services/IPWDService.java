package tn.esprit.spring.AhmedGuedri.Services;

import tn.esprit.spring.AhmedGuedri.entities.User;
import java.util.Date;
import java.util.List;

public interface IPWDService {
     void AssignPasswordToUser(User u,String password); // Assign Password To User
     String EditPassword(String Email,String password1,String password2); // Update Password
     String RetievePasswordInfo(String email); // Retrieve User Expiration dATE

}
