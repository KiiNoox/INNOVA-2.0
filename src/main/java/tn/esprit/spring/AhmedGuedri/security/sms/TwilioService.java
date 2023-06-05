package tn.esprit.spring.AhmedGuedri.security.sms;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;

@Service
public class TwilioService {
    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String phoneNumber;

    public void sendSMS(String to, String body) {
        Twilio.init(accountSid, authToken);
        Message message = Message.creator(
                        new PhoneNumber(to),
                        new PhoneNumber(phoneNumber),
                        body)
                .create();
        System.out.println("SMS sent: " + message.getSid());
    }
}
