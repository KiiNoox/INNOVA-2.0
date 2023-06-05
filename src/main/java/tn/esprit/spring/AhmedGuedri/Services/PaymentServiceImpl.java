package tn.esprit.spring.AhmedGuedri.Services;

import com.twilio.rest.api.v2010.account.call.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.Repositories.OrdersRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.PaymentRepository;
import tn.esprit.spring.AhmedGuedri.entities.Orders;
import tn.esprit.spring.AhmedGuedri.entities.Payement;
import tn.esprit.spring.AhmedGuedri.entities.PaymentType;
import tn.esprit.spring.AhmedGuedri.security.services.UserDetailsImpl;

import java.util.Date;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    OrdersRepository ordersRepository;
    @Override
    public Payement pay(){
        try {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Orders orders =  ordersRepository.findOneByShoppingCart_User_Id(((UserDetailsImpl) auth.getPrincipal()).getId());
        Payement payment = new Payement();
        payment.setOrders(orders);
        payment.setPaymentDate(new Date());
        payment.setPaymentType(PaymentType.CreditCard);

            Thread.sleep(5000);
            return paymentRepository.save(payment);
        } catch (InterruptedException e) {
            throw new RuntimeException("creation failed" );
        }
    }

}
