package ibn.achraf.demo.streams;

import java.util.Map;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;

import ibn.achraf.demo.model.SwapResult;


@Configuration
public class competerStream {
    	private static final Logger logger = LoggerFactory.getLogger(competerStream.class);

    @Bean(name = "swapBuilder")
    public StreamsBuilderFactoryBean swapFactory(
        @Value("${applicationId}") String applicationId,
        @Value("${bootstrapServers}") String bootstrapServers
        ) {
        return  new StreamsBuilderFactoryBean(new KafkaStreamsConfiguration(
            Map.of(
                StreamsConfig.APPLICATION_ID_CONFIG,applicationId,
                StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers
            )));
    }


    @Bean
    public KStream<String, SwapResult> buildSwapTopology(StreamsBuilder builder, @Value("${inputTopic}") String inputTopic) {
        return builder.stream(
            inputTopic,
            Consumed.with(Serdes.String(), SwapResult.swapResultSerde())
        ).map((k, v) -> {
            logger.info("Key={}, Value={}", k, v);
            return new KeyValue<>(k, v);
        });
    }


}
