package arnado.mike.kafkaproducer.impl.domain.service;

import arnado.mike.common.domain.model.FullName;
import arnado.mike.kafkaproducer.domain.service.NameGeneratorService;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class NameGeneratorServiceImpl implements NameGeneratorService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Faker faker = new Faker(new Locale("en-US"));

    @Override
    public FullName generate() {
        Name generated = faker.name();
        logger.info("Generated name: " + generated.firstName() + " " + generated.lastName());
        return new FullName(generated.firstName(), generated.lastName());
    }
}
