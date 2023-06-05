package tn.esprit.spring.AhmedGuedri.Controllers;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.AhmedGuedri.Services.IOrderService;
import tn.esprit.spring.AhmedGuedri.Services.IShoppingCartService;
import tn.esprit.spring.AhmedGuedri.Services.OrderService;
import tn.esprit.spring.AhmedGuedri.Services.StripeService;
import tn.esprit.spring.AhmedGuedri.entities.Invoices;
import tn.esprit.spring.AhmedGuedri.entities.Orders;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Order")
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
public class OrderController {
    @Autowired
    IOrderService orderService;
    @Autowired
    IShoppingCartService shoppingCartService;
    @Autowired
    StripeService stripeService;

    //http://localhost:8083/ratatoskr/Order/addOrder
    @PutMapping("/addOrder/{idShoppingCart}")
    @ResponseBody
    public Orders addOrder(@PathVariable Long idShoppingCart ){
        Orders o=   new Orders();
         o= orderService.addOrders(idShoppingCart);
        shoppingCartService.clearShoppingCart(idShoppingCart.toString());
        return o;
    }

    //http://localhost:8083/ratatoskr/Order/updateOrders
    @PutMapping("/updateOrders")
    @ResponseBody
    public void updateOrders(@RequestBody Orders o)
    {
        orderService.updateOrders(o);
    }

    //http://localhost:8083/ratatoskr/Order/deleteOrders/1
    @DeleteMapping("/deleteOrders/{id}")
    @ResponseBody
    public void deleteOrders(@PathVariable Long id){
        orderService.deleteOrders(id);
    }

    //http://localhost:8083/ratatoskr/Order/retrieveAllOrders
    @GetMapping("/retrieveAllOrders")
    @ResponseBody
    public List<Orders> retrieveAllOrders() {

        return orderService.retrieveAllOrders();
    }


    //http://localhost:8083/ratatoskr/Order/retrieveOrders/1
    @GetMapping("/retrieveOrders/{id}")
    @ResponseBody
    public Orders retrieveOrders(@PathVariable Long id){
        return orderService.retrieveOrders(id);
    }

    //http://localhost:8083/ratatoskr/Order/AddPanierToOrders/1/1
    @PostMapping("/AddPanierToOrders/{idpanier}/{idOrders}")
    @ResponseBody
    public Orders addPanierToOrder(@PathVariable Long idpanier,@PathVariable Long idOrders ){
        return orderService.addPanierToOrder(idpanier,idOrders);
    }
    //http://localhost:8083/ratatoskr/Order/stripe/token/1/1
    @PostMapping("/stripe")
    @ResponseBody
    public double createCharge(@RequestParam String email,@RequestParam String token, @RequestParam Long idUser, @RequestParam Long idOrders) throws StripeException {
        return stripeService.createCharge(email,token,idUser,idOrders);
    }
/*
    @GetMapping("/exportOrder/pdf")
    public void exportToPDF(HttpServletResponse response) throws IOException, com.lowagie.text.DocumentException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Orders> listOrders = OrderService.;

        O exporter = new OfferPDFExporter(listOffers);
        exporter.export(response);

    }*/



}
