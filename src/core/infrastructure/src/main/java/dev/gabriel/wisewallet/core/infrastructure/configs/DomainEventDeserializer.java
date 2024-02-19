package dev.gabriel.wisewallet.core.infrastructure.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gabriel.wisewallet.core.domain.events.DomainEvent;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class DomainEventDeserializer implements Deserializer<DomainEvent> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public DomainEvent deserialize(String topic, byte[] data) {
        try {
            if(data == null) {
                return null;
            }
            return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), DomainEvent.class);
        }catch(Exception e) {
            throw new SerializationException("Failed to deserialize event from bytes.");
        }
    }

    @Override
    public void close() {

    }
}
