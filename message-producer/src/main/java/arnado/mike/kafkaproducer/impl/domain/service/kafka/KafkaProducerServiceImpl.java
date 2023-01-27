package arnado.mike.kafkaproducer.impl.domain.service.kafka;

import arnado.mike.common.domain.event.NameGeneratedEvent;
import arnado.mike.common.domain.model.FullName;
import arnado.mike.kafkaproducer.domain.service.kafka.KafkaProducerService;
import arnado.mike.kafkaproducer.domain.service.NameGeneratorService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Log
@Service
@EnableScheduling
public class KafkaProducerServiceImpl implements KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, NameGeneratedEvent> kafkaTemplate;

    @Autowired
    NameGeneratorService nameGeneratorService;

    @Scheduled(fixedRate = 3000)
    public void intervalSend() {
        FullName fullName = nameGeneratorService.generate();

        log.info("Sending name to Kafka: " + fullName.getFirstName() + " " + fullName.getLastName());

        kafkaTemplate.send(NameGeneratedEvent.TOPIC, new NameGeneratedEvent(fullName));
    }
}
