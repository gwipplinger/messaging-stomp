package cloud.thales.messagingstomp;

import org.springframework.beans.factory.annotation.*;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.*;

@Controller
public class GreetingController {

    @Autowired
    KafkaProducer kafkaProducer;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        final String msg = "Hello, " + message.getName() +"!";
        kafkaProducer.sendMessage(msg);
        Thread.sleep(1000);
        return new Greeting(msg);
    }
}
