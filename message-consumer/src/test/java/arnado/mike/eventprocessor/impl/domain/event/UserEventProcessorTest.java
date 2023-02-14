package arnado.mike.eventprocessor.impl.domain.event;

import arnado.mike.common.domain.event.UserCreatedEvent;
import arnado.mike.common.domain.model.User;
import arnado.mike.eventprocessor.domain.event.UserCreatedEventConsumer;
import arnado.mike.eventprocessor.domain.event.UserEventProcessor;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserEventProcessorTest {

    @Autowired
    UserEventProcessor eventProcessor;

    @MockBean
    Function<User, UserCreatedEvent> transformUserToEvent;

    @MockBean
    @Qualifier("processEvent")
    UserCreatedEventConsumer processEvent;

    @MockBean
    @Qualifier("count")
    UserCreatedEventConsumer count;

    @Autowired
    StreamBridge streamBridge;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @Captor
    ArgumentCaptor<UserCreatedEvent> eventCaptor;

    User user = new User("Test", "User");
    UserCreatedEvent event = UserCreatedEvent.of(user);

    @Test
    void testEventFlow() {
        when(transformUserToEvent.apply(user)).thenReturn(event);

        streamBridge.send("user-topic", user);

        verify(transformUserToEvent, times(1)).apply(userCaptor.capture());
        verify(processEvent, times(1)).accept(eventCaptor.capture());
        verify(count, times(1)).accept(eventCaptor.capture());

        User capturedUser = userCaptor.getValue();

        List<UserCreatedEvent> capturedEvents = eventCaptor.getAllValues();

        assert capturedUser.firstName().equals(user.firstName());
        assert capturedUser.lastName().equals(user.lastName());

        assert capturedEvents.size() == 2;

        assertEquals(2, capturedEvents.stream().filter(e -> e.hashCode() == event.hashCode()).count());
    }

    @Test
    void testTransformUserToEvent() {
        var transformer = eventProcessor.transformUserToEvent();

        var transformed = transformer.apply(user);

        assertEquals(user, transformed.user());
        assertNotNull(transformed.timestamp());
    }

    @Test
    void testCount() {
        when(transformUserToEvent.apply(user)).thenReturn(event);

        streamBridge.send("user-topic", user);
        streamBridge.send("user-topic", user);

        verify(count, times(2)).accept(eventCaptor.capture());

        var capturedEvents = eventCaptor.getAllValues();

        assertEquals(2, capturedEvents.stream().filter(e -> e.hashCode() == event.hashCode()).count());
    }

    @Test
    void testProcessEvent() {
        when(transformUserToEvent.apply(user)).thenReturn(event);

        streamBridge.send("user-topic", user);

        verify(processEvent, only()).accept(eventCaptor.capture());

        UserCreatedEvent capturedEvent = eventCaptor.getValue();

        assert capturedEvent.hashCode() == event.hashCode();
    }
}