package tn.esprit.spring.AhmedGuedri.Services;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.AhmedGuedri.Repositories.PWDRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.UserRepository;
import tn.esprit.spring.AhmedGuedri.entities.HashedPWD;
import tn.esprit.spring.AhmedGuedri.entities.User;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor

public class PWDService implements IPWDService {
    static int i=0;
    UserRepository userRepository;
    PWDRepository pwdRepository;
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void AssignPasswordToUser(User u, String password) {
        System.out.println("Been Visited : "+ i++);
        HashedPWD newUserPass = new HashedPWD();
        Date date = new Date();
        newUserPass.setPassword(password);
        newUserPass.setChangeDate(date);
        //newUserPass.setUser(userRepository.findByEmail("amal@ehkbhbjhn.TYYYh").get());//test
        newUserPass.setUser(u);
        //pwdRepository.save(newUserPass);
       // u.setHashedPWD(pwdRepository.findByUser(u));
       // userRepository.save(u);
    }
    @Transactional
    @Override
    public String EditPassword (String Email,String PrevPassword,String NewPassword){
        User user = userRepository.findByEmailEquals(Email);
        System.out.println("Email : "+Email +" PrevPassword : "+PrevPassword+" NewPassword : "+NewPassword);

        Date datenow = new Date();
        if (user.isEnabled()==true) {
            HashedPWD pass = pwdRepository.findById(user.getHashedPWD().getPassId()).get();
            System.out.println("User Current Password : "+pass.getPassword());
            System.out.println(pass);
            long diff = datenow.getTime() - pass.getChangeDate().getTime();
            if( ((diff / (24 * 60 * 60 * 1000) >= 14))){
                //MUST CHANGE DATE
                if (passwordEncoder.matches(PrevPassword, pass.getPassword())) {

                    System.out.println(PrevPassword);
                    System.out.println(NewPassword);

                    pass.setPassword(passwordEncoder.encode(NewPassword));
                    pass.setChangeDate(datenow);
                    pwdRepository.save(pass);
                    return "Password Changed";
                } else
                    return "Wrong Password";
            } else
                return "Password Date Not yet Expired";
        }
        return "SomeThing Went Wrong ?!";
    }
//            HashedPWD newUserPass = new HashedPWD();
//            Date date = new Date();
//            newUserPass.setPassword(password);
//            newUserPass.setChangeDate(date);
//            newUserPass.setUser(u);
//            pwdRepository.save(newUserPass);


    @Override
    public String RetievePasswordInfo(String email) {
        User u=userRepository.findByEmailEquals(email);
        if (u==null)
            return "User Not Found";
        System.out.println("el user :"+u);

         return u.getHashedPWD().getChangeDate().toString();
        // return pwdRepository.findByUser(u).getChangeDate().toString();

    }
}
