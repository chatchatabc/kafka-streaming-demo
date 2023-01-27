package arnado.mike.kafkaproducer;

import arnado.mike.common.domain.model.FullName;
import arnado.mike.kafkaproducer.impl.domain.service.NameGeneratorServiceImpl;
import arnado.mike.kafkaproducer.domain.service.NameGeneratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = NameGeneratorServiceImpl.class)
class KafkaProducerApplicationTests {

    @Autowired
    NameGeneratorService nameGeneratorService;

    @Test
    void contextLoads() { }

    @Test
    void testNameGeneration() {
        FullName generated = nameGeneratorService.generate();
        assert generated != null;
        assert generated.getFirstName() != null;
        assert generated.getLastName() != null;
    }
}
