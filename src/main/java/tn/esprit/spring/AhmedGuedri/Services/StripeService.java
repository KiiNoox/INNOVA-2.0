package tn.esprit.spring.AhmedGuedri.Services;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.Repositories.OrdersRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.PaymentRepository;
import org.springframework.web.client.RestTemplate;
import tn.esprit.spring.AhmedGuedri.Repositories.UserRepository;
import tn.esprit.spring.AhmedGuedri.entities.Orders;
import tn.esprit.spring.AhmedGuedri.entities.Payement;
import tn.esprit.spring.AhmedGuedri.entities.PaymentType;
import tn.esprit.spring.AhmedGuedri.entities.User;
import tn.esprit.spring.AhmedGuedri.payload.mailing.EmailDetails;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDateTime;

@Service
public class StripeService {
    @Autowired
    ShoppingCartServiceImpl shoppingCartService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    OrdersRepository orderRepository;
    @Autowired
    private IEmailService emailService;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    OrderService serviceOder;


    @Value("sk_test_51Mhg25BYRBc0J1wpoNuBuucIzrAq0xcEZOqxcuVaEnF3LbbVeEDZt7z7BLUeashdvG6IOyRWp9U6YsHP6eAcs9Gy00EQtJABl5")
    String stripeKey;

    public Payement payment(long idUser, long idOrder, Payement p) throws StripeException {
        Stripe.apiKey = stripeKey;
        User user = userRepository.findById(idUser).get();
        Orders orders = orderRepository.findById(idOrder).get();
        Map<String, Object> params = new HashMap<>();
        params.put("name", user.getFirstName());
        params.put("email", user.getEmail());
        //CHANGEME orders.getPanier().getTotalPrice()
        params.put("amount*100", 100);
        params.put("created",p.getCreated());
        //  Customer customer = Customer.create(params);
        //p.setCustomerId(customer.getId());
        return p;
    }

    public double createCharge(String email,String token, Long idUser, Long IdShopCart) throws StripeException {
        Optional<User> user = userRepository.findById(idUser);
        Orders orders = orderRepository.findById(IdShopCart).get();
        String idOr=IdShopCart.toString();
        String id;
        Stripe.apiKey = stripeKey;
        Map<String, Object> chargeParams = new HashMap<>();
        System.out.println("ok" +shoppingCartService.getTotalPriceShoppingCart(idOr));
        chargeParams.put("amount", Math.round(shoppingCartService.getTotalPriceShoppingCart(idOr)*100));
        chargeParams.put("currency", "usd");
        chargeParams.put("receipt_email", email);
        chargeParams.put("description", "Charge for " + email);
        chargeParams.put("source", token); // ^ obtained with Stripe.js
        //create a charge
        Charge charge = Charge.create(chargeParams);
        id = charge.getId();
        if (id == null) {
            throw new RuntimeException("Something went wrong!");
        }
        Payement payment = new Payement(); // Create a new Payment object
// Set the properties of the Payment object as required
        payment.setCreated(1);
        //payment.setUser(user.get());
        payment.setPaymentType(PaymentType.CreditCard);
        payment.setConfirmationPayments(true);
        orders.setPayment(payment);
        Date date=Date.from((LocalDateTime.now()).atZone(ZoneId.systemDefault()).toInstant());
        payment.setPaymentDate(date);
       // payment.setIdorder(idOrders);
        paymentRepository.save(payment);
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(email);
        emailDetails.setSubject("Payment Confirmation");
        emailDetails.setMsgBody("Payment with sucess , Thank you for your purchase !");
        emailService.sendSimpleMail(emailDetails);

        // payment successfully
        return shoppingCartService.getTotalPriceShoppingCart(idOr);
    }


    private String getReceipt(String id) throws StripeException {
        Charge charge = Charge.retrieve(id);
        String receiptUrl = charge.getReceiptEmail();
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(receiptUrl, String.class);
    }


}