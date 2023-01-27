package arnado.mike.common.domain.serialization;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;

public class GenericSerializer<T> implements Serializer<T> {

    Gson gson = new Gson();

    @Override
    public byte[] serialize(String s, T t) {
        return gson.toJson(t).getBytes();
    }
}

