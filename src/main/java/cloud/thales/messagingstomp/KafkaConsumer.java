package cloud.thales.messagingstomp;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.annotation.*;
import org.springframework.messaging.simp.*;
import org.springframework.stereotype.*;

@Service
public class KafkaConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private SimpMessagingTemplate messageSender;

    @KafkaListener(topics = "first-topic", groupId = "group_id")
    public void consume(String message) {
        LOG.info("received: {}", message);
        messageSender.convertAndSend("/topic/greetings", new Greeting(message));
    }
}
