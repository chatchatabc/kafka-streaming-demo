package arnado.mike.common.domain.event;

import arnado.mike.common.domain.model.FullName;
import lombok.Data;

@Data
public class NameGeneratedEvent {
    public static final String TOPIC = "name-generated";

    Long timestamp;
    FullName fullName;
    public NameGeneratedEvent(FullName fullName) {
        this.fullName = fullName;
        this.timestamp = System.currentTimeMillis();
    }

    public NameGeneratedEvent(Long timestamp, FullName fullName) {
        this.timestamp = timestamp;
        this.fullName = fullName;
    }
}
