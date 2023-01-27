package arnado.mike.common.domain.serialization;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;

import java.lang.reflect.Type;

public class GenericDeserializer<T> implements Deserializer<T> {

    Gson gson = new Gson();
    Type type;

    // Deserialized class needs to be defined due to Type Erasure.
    public GenericDeserializer(Class<T> tClass) {
        this.type = tClass;
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        return gson.fromJson(new String(bytes), type);
    }
}
