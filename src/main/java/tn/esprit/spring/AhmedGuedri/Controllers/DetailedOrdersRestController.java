package tn.esprit.spring.AhmedGuedri.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.AhmedGuedri.Services.IProductService;
import tn.esprit.spring.AhmedGuedri.entities.DetailedOrders;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/dtorders")
public class DetailedOrdersRestController {

    IProductService productService;
    @GetMapping("/get/{dates}/{datee}/{id}")
    List<DetailedOrders> getDetailedOrdersbyDaterange(@PathVariable("dates") Date dates, @PathVariable("datee") Date datee, @PathVariable("id") Long id){
        return productService.getDetailedOrdersbyDaterange(dates,datee,id);
    }
    /*@PostMapping("/sendemail")
    public ResponseEntity<String> sendEmail(@RequestParam String toEmail,
                                            @RequestParam String subject,
                                            @RequestParam String message){
        productService.sendEmail(javaMailSender,toEmail,subject,message);
        return ResponseEntity.ok("Email sent successfully");
    }*/
}
