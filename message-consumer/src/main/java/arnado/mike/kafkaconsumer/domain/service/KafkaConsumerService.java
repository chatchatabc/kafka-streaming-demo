package arnado.mike.kafkaconsumer.domain.service;

import arnado.mike.common.domain.event.NameGeneratedEvent;

public interface KafkaConsumerService {
    void processEvent(NameGeneratedEvent event);
}
