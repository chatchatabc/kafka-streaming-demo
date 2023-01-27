package arnado.mike.kafkaconsumer.impl.domain.service.kafka;

import arnado.mike.common.domain.event.NameGeneratedEvent;
import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log
@Service
public class KafkaConsumerServiceImpl {

    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @KafkaListener(topics = NameGeneratedEvent.TOPIC, groupId = "kafka-consumer")
    public void processEvent(NameGeneratedEvent event) {
        log.info("[" + formatter.format(new Date(event.getTimestamp())) + "] EVENT RECEIVED: " + event.getFullName().getFirstName() + " " + event.getFullName().getLastName());
    }
}
