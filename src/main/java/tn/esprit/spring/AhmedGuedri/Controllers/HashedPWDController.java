package tn.esprit.spring.AhmedGuedri.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import tn.esprit.spring.AhmedGuedri.Services.IPWDService;
import tn.esprit.spring.AhmedGuedri.Services.IUserService;
import tn.esprit.spring.AhmedGuedri.entities.User;
import tn.esprit.spring.AhmedGuedri.payload.request.NewPasswordRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/password/")
public class HashedPWDController {
    private IUserService iUserService;
    private IPWDService ipwdService;
    private PasswordEncoder passwordEncoder;


    @PutMapping("/edit")
    @PreAuthorize("hasRole('USER') or hasRole('PROVIDER') or hasRole('ADMIN') or hasRole('DELIVERY')")
    public ResponseEntity updateUser(HttpServletRequest request, @RequestBody NewPasswordRequest newPasswordRequest) {
        String email= iUserService.UserVerificationReturnEmail(request);
        if (email.equals("Token Doesn't Match Authenfied User"))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("false user");
        String response= ipwdService.EditPassword(email,newPasswordRequest.getPassword(), newPasswordRequest.getNewPassword());
        if (response.equals("Password Date Not yet Expired"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("expiration false");
        else if (response.equals("Wrong Password"))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("wrong");
        else if (response.equals("Password Changed"))
            return ResponseEntity.status(HttpStatus.OK).body(null);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("bad");


    }
}