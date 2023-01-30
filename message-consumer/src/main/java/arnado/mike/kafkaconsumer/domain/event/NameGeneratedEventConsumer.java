package arnado.mike.kafkaconsumer.domain.event;

import arnado.mike.common.domain.event.NameGeneratedEvent;

import java.util.function.Consumer;

public interface NameGeneratedEventConsumer extends Consumer<NameGeneratedEvent> {
}
