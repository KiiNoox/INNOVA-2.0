package tn.esprit.spring.AhmedGuedri.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.AhmedGuedri.Services.IDeliveryService;
import tn.esprit.spring.AhmedGuedri.entities.Delivery;
import tn.esprit.spring.AhmedGuedri.entities.Orders;
import tn.esprit.spring.AhmedGuedri.entities.StatusType;
import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    private IDeliveryService deliveryService;
    @GetMapping("/coordinates")
    public double[] getAddressCoordinate(@RequestParam String address) {
        return deliveryService.getAddressCoordinate(address);
    }
//3
    @GetMapping("/getPath/{idDelivery}")
    public List<Orders> getPath(@PathVariable Long idDelivery){
        return deliveryService.getListOrdersPath(idDelivery);
    }
    //2
    @GetMapping("/getNormalOrders/{idDelivery}")
    public List<Orders> getNormalOrders(@PathVariable Long idDelivery){
        return deliveryService.normaleOrders(idDelivery);
    }
    //1
    @GetMapping("/getAssignedUser/{idOrder}")
    public Delivery getAssignedUser(@PathVariable Long idOrder){
        return deliveryService.assignDelivery(idOrder);
    }

    @DeleteMapping("/deleteDelivery/{idDelivery}")
    public List<Delivery> deleteDelivery(@PathVariable Long idDelivery){
        return deliveryService.deleteDelivery(idDelivery);
    }
    //4
    @PutMapping("/finishingDelivery/{idDelivery}/{endDate}/{statu}")
    public Delivery finishingDelivery(@PathVariable Long idDelivery,@PathVariable StatusType statu, @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
        return deliveryService.finishingDelivery(idDelivery,endDate,statu);
    }
    //4

    @GetMapping("/displayDelivery")
    public List<Delivery> displayDelivery(){
        return deliveryService.displayDelivery();
    }
    //5
    @GetMapping("/avreageTime")
    public double avreageTime(){
        return deliveryService.avreageTime();
    }
    @DeleteMapping("/deleteOrderFromDelivery/{idOrder}")
    public List<Delivery> deleteOrderFromDelivery(@PathVariable Long idOrder){
        return deliveryService.deleteOrderFromDelivery(idOrder);
    }
    @GetMapping("/mostFrequentAdresse")
    public String mostFrequentAdresse(){
        return deliveryService.mostFrequentAdresse();
    }
    @GetMapping("/cancelDelivery/{idDelivery}")
    public void cancelDelivery(@PathVariable Long idDelivery){
        deliveryService.cancelDelivery(idDelivery);
    }

}
