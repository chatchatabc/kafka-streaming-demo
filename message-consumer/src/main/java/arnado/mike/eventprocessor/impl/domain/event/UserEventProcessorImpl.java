package arnado.mike.eventprocessor.impl.domain.event;

import arnado.mike.common.domain.event.UserCreatedEvent;
import arnado.mike.common.domain.model.User;
import arnado.mike.eventprocessor.domain.event.UserEventProcessor;
import arnado.mike.eventprocessor.domain.event.UserCreatedEventConsumer;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Log
@Service
public class UserEventProcessorImpl implements UserEventProcessor {

    @Bean
    public Function<User, UserCreatedEvent> transformUserToEvent() {
        log.info("Created bean for transformUserToEvent");
        return user -> {
            UserCreatedEvent event = UserCreatedEvent.of(user);
            log.info("Transformed user: " + user + " to event: " + event);
            return event;
        };
    }

    @Bean
    public UserCreatedEventConsumer processEvent() {
        log.info("Created bean for processEvent");
        return event -> {
            String userString = "%s %s".formatted(event.user().firstName(), event.user().lastName());
            log.info("Received user: " + userString);
        };
    }

    int count = 0;

    @Bean
    public UserCreatedEventConsumer count() {
        log.info("Created bean for count");
        return event -> {
            count++;
            log.info("Times called: " + count);
        };
    }
}
