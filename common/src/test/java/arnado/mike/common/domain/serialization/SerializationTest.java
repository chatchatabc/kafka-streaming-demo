package arnado.mike.common.domain.serialization;

import arnado.mike.common.domain.model.FullName;
import org.junit.jupiter.api.Test;

class SerializationTest {

    @Test
    void testSerialization() {
        FullName fullName = new FullName("Mike", "Arnado");

        GenericSerializer<FullName> serializer = new GenericSerializer<FullName>();
        GenericDeserializer<FullName> deserializer = new GenericDeserializer<>(FullName.class);

        byte[] serialized = serializer.serialize("test", fullName);
        FullName deserialized = deserializer.deserialize("test", serialized);

        assert(deserialized.equals(fullName));
    }
}