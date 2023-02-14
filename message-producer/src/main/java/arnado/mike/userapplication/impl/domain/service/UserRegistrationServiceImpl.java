package arnado.mike.userapplication.impl.domain.service;

import arnado.mike.common.domain.model.User;
import arnado.mike.userapplication.domain.service.UserRegistrationService;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Log
@Service
@EnableScheduling
public class UserRegistrationServiceImpl implements UserRegistrationService {

    Faker faker = new Faker();

    @Autowired
    StreamBridge bridge;

    @Scheduled(fixedRate = 3000)
    public User register() {
        Name name = faker.name();
        User user = new User(name.firstName(), name.lastName());

        log.info("Sending user: " + user);

        Message<User> message = MessageBuilder.withPayload(user).build();
        bridge.send("user-topic", message);

        return user;
    }
}