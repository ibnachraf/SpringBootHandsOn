package ibn.achraf.demo.model;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public record SwapResult(int amount, int from, int to, ProvidersEnum provider) {
    public static Serde<SwapResult> swapResultSerde() {
        JsonSerializer<SwapResult> serializer = new JsonSerializer<>();
        JsonDeserializer<SwapResult> deserializer = new JsonDeserializer<>(SwapResult.class);
        deserializer.addTrustedPackages("*");
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
