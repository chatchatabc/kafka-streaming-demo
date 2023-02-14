package arnado.mike.eventprocessor.domain.event;

import arnado.mike.common.domain.event.UserCreatedEvent;
import arnado.mike.common.domain.model.User;

import java.util.function.Function;

public interface UserEventProcessor {

    Function<User, UserCreatedEvent> transformUserToEvent();
    UserCreatedEventConsumer processEvent();

    UserCreatedEventConsumer count();
}
