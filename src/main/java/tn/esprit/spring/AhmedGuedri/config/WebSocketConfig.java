package tn.esprit.spring.AhmedGuedri.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;








@Configuration
@EnableWebSocketMessageBroker
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:4200")
                .withSockJS();
    }

    @Override
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app")
                .enableSimpleBroker("/chat");
    }
}

