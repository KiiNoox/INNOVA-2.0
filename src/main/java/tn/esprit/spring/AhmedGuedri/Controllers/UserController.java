package tn.esprit.spring.AhmedGuedri.Controllers;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.AhmedGuedri.Repositories.UserRepository;
import tn.esprit.spring.AhmedGuedri.Services.IPWDService;
import tn.esprit.spring.AhmedGuedri.Services.IUserService;
import tn.esprit.spring.AhmedGuedri.entities.HashedPWD;
import tn.esprit.spring.AhmedGuedri.entities.User;
import tn.esprit.spring.AhmedGuedri.payload.request.NewPasswordRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user/")
public class UserController {

//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
    private IUserService iUserService;
    private IPWDService ipwdService;
    private final UserRepository userRepository;

    //    @Autowired
  //  private PasswordEncoder passwordEncoder;
    @PostMapping("addUser")
    public ResponseEntity<User> addUser(@RequestBody User User) {


            iUserService.addUser(User);
        System.out.println( User.getHashedPWD().getPassword());
            ipwdService.AssignPasswordToUser(User, User.getHashedPWD().getPassword());


        return new ResponseEntity<User>(HttpStatus.CREATED);
    }

    @PutMapping("/updateUser")
    @PreAuthorize("hasRole('USER') or hasRole('PROVIDER') or hasRole('ADMIN') or hasRole('DELIVERY')")
    public String updateUser(HttpServletRequest request,@RequestBody User u) {
        String email= iUserService.UserVerificationReturnEmail(request);
        if (email.equals("Token Doesn't Match Authenfied User"))
            return "Token Doesn't Match Authenfied User";

        if (u.getEmail().equals(email))
        return iUserService.updateUser(u);
        else
            return "Email Doesn't Match Authenfied User";
    }
    @GetMapping("/{email}/id")
    public Long getUserIdByEmail(@PathVariable String email) {
        return userRepository.findIdByEmail(email);
    }

    @GetMapping("/deleteUser")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(HttpServletRequest request ,@RequestParam String emailAdress) {
        String email= iUserService.UserVerificationReturnEmail(request);
        if (email.equals("Token Doesn't Match Authenfied User"))
            return "Token Doesn't Match Authenfied User";
        return iUserService.deleteUser(emailAdress);
    }
    @GetMapping("/activateUser")
    @PreAuthorize("hasRole('ADMIN')")
    public String ActivateUser(HttpServletRequest request, @RequestParam String emailAdress) {
        String email= iUserService.UserVerificationReturnEmail(request);
        if (email.equals("Token Doesn't Match Authenfied User"))
            return "Token Doesn't Match Authenfied User";
        return iUserService.ActivateUser(emailAdress);


    }
    //implementing the method RetievePasswordInfo
    @GetMapping("/RetievePasswordInfo")
    @PreAuthorize("hasRole('USER') or hasRole('PROVIDER') or hasRole('ADMIN') or hasRole('DELIVERY')")
    public ResponseEntity RetievePasswordInfo(HttpServletRequest request) {
        String email= iUserService.UserVerificationReturnEmail(request);
        if (email.equals("Token Doesn't Match Authenfied User"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token Doesn't Match Authenfied User");

        HashMap<String, String> map = new HashMap<>();
        map.put("Expiration_Date", ipwdService.RetievePasswordInfo(email));
        return ResponseEntity.status(HttpStatus.OK).body(map);

    }
    //Implementing VerifyUserToken

    @GetMapping("/confirm-account")
    @PreAuthorize("hasRole('USER') or hasRole('PROVIDER') or hasRole('ADMIN') or hasRole('DELIVERY')")
    public String VerifyUserToken(HttpServletRequest request, @RequestParam Long token) {
        String email= iUserService.UserVerificationReturnEmail(request);
        if (email.equals("Token Doesn't Match Authenfied User"))
            return "Token Doesn't Match Authenfied User";
        return iUserService.VerifyUserToken(email, token);

    }
    //Implementing TopTierSellers
    @GetMapping("/TopTierSellers")
    @PreAuthorize("hasRole('USER') or hasRole('PROVIDER') or hasRole('ADMIN') or hasRole('DELIVERY')")
    public List<String> TopTierSellers() {
        return iUserService.TopTierSellers();

    }
    //Implementing Authenticate
    @GetMapping("/Authenticate")
    public void Authenticate(@RequestParam String email) {
        iUserService.Authenticate(email);

    }
    //Forgot Password
    @GetMapping("/recovery/ForgotPassword")
    public ResponseEntity ForgotPassword(@RequestParam String email,@RequestParam Boolean Phone,@RequestParam String PhoneNum) {

       if ( iUserService.ForgotPassword(email,Phone, PhoneNum).equals("User Not Found"))
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
       else
           return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    //Verify Forgot Password Token
    @PostMapping("/recovery/VerifyForgotPasswordToken")
    public ResponseEntity VerifyForgotPasswordToken(@RequestParam String email, @RequestBody NewPasswordRequest newPasswordRequest, @RequestParam Long token) {
        //return "gg works2";
        if ( iUserService.VerifyForgotPasswordToken(email, newPasswordRequest.getPassword(), newPasswordRequest.getNewPassword(), token).equals("User Not Found"))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
// SendAndReceive

    //Get User By Email
    @GetMapping("/GetUserByEmail")
    @PreAuthorize("hasRole('USER') or hasRole('PROVIDER') or hasRole('ADMIN') or hasRole('DELIVERY')")
    public ResponseEntity<User> GetUserByEmail(HttpServletRequest request,@RequestParam String email) {
        if (iUserService.userExists(email))
            // return iUserService.GetUserInfo(email);
            return ResponseEntity.status(HttpStatus.OK).body(iUserService.GetUserInfo(email));

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    //Get all users
    @GetMapping("/GetAllUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> GetAllUsers() {

        return iUserService.getAllUsers();
    }

    @PutMapping("/admin/updateUser")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateUser(@RequestBody User u) {
            return iUserService.updateUser(u);

    }
    //userExists
    @GetMapping("/userExists")
    public ResponseEntity userExists(@RequestParam String email) {

       if (iUserService.userExists(email))
       {
           return ResponseEntity.status(HttpStatus.FOUND).body(null);
       }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/recovery/userRecovery")
    public ResponseEntity userRecovery(@RequestParam String email) {


        if (iUserService.userExists(email))
        {
            User u =iUserService.GetUserInfo(email);
            HashMap<String, String> map = new HashMap<>();
            map.put("email", u.getEmail());
            map.put("phone", u.getPhoneNumber().toString());

            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
