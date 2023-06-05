package tn.esprit.spring.AhmedGuedri.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.AhmedGuedri.Services.IDeliveryService;
import tn.esprit.spring.AhmedGuedri.Services.IPaymentService;
import tn.esprit.spring.AhmedGuedri.entities.Delivery;
import tn.esprit.spring.AhmedGuedri.entities.Orders;
import tn.esprit.spring.AhmedGuedri.entities.Payement;
import tn.esprit.spring.AhmedGuedri.entities.StatusType;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
public class PaymentController {
    @Autowired
    private IPaymentService paymentService;
//3
    @PutMapping("/pay")
    public Payement getPath(){
        return paymentService.pay();
    }
    //2

}
