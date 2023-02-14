package arnado.mike.common.domain.event;

import arnado.mike.common.domain.model.User;
import lombok.NonNull;


public record UserCreatedEvent(
        Long timestamp,
        @NonNull User user
) {
    public static UserCreatedEvent of(User user) {
        return new UserCreatedEvent(System.currentTimeMillis(), user);
    }
}
