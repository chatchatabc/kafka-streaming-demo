package arnado.mike.kafkaconsumer.impl.domain.service.kafka;

import arnado.mike.common.domain.event.NameGeneratedEvent;
import arnado.mike.common.domain.serialization.GenericDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
public class KafkaConsumerConfiguration {

    /*
     * KafkaProperties is autoconfigured through application.properties.
     * There are still values that we still need there, so instead of creating a long list of properties,
     * we can just use the autoconfigured KafkaProperties instead.
     */
    @Autowired
    KafkaProperties kafkaProperties;

    /*
     * Autoconfiguration through application.properties only allows deserializers that work with a no-arg constructor.
     * GenericDeserializer needs a class to deserialize to, so we need to instantiate it here with a target class.
     */
    @Bean
    ConsumerFactory<?, ?> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                kafkaProperties.getConsumer().buildProperties(),
                new GenericDeserializer<>(String.class),
                new GenericDeserializer<>(NameGeneratedEvent.class)
        );
    }
}
