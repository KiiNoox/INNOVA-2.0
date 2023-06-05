package tn.esprit.spring.AhmedGuedri.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import tn.esprit.spring.AhmedGuedri.Repositories.UserRepository;
import tn.esprit.spring.AhmedGuedri.Services.IUserService;
import tn.esprit.spring.AhmedGuedri.security.jwt.JwtUtils;
import tn.esprit.spring.AhmedGuedri.security.sms.TwilioService;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @Autowired
  UserRepository users;
  @Autowired
  private TwilioService twilioService;
  @Autowired
  IUserService iUserService;
  @Autowired
  private WebApplicationContext appContext;
  @GetMapping("/all")
  public String allAccess() {

   // return users.findByEmailEquals("midou192@live.fr2").toString();
    return "Public Content.";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('PROVIDER') or hasRole('ADMIN')")
  public String userAccess() {




    return "User Content.";
  }

  @GetMapping("/provider")
  @PreAuthorize("hasRole('PROVIDER') or hasRole('ADMIN')")
  public String moderatorAccess(HttpServletRequest request) {
    System.out.println("Connected Account : "+iUserService.UserVerificationReturnEmail(request));
    return "Provider Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }
}
