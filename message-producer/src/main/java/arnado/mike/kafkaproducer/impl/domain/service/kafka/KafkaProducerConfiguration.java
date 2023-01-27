package arnado.mike.kafkaproducer.impl.domain.service.kafka;

import arnado.mike.common.domain.event.NameGeneratedEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaProducerConfiguration {

    @Bean
    public NewTopic createSampleTopic() {
        return TopicBuilder.name(NameGeneratedEvent.TOPIC)
                // For local development
                .partitions(1)
                .replicas(1)
                .compact()
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "gzip")
                .build();
    }
}