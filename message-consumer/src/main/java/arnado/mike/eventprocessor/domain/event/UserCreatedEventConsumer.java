package arnado.mike.eventprocessor.domain.event;

import arnado.mike.common.domain.event.UserCreatedEvent;

import java.util.function.Consumer;

@FunctionalInterface
public interface UserCreatedEventConsumer extends Consumer<UserCreatedEvent> {
}
