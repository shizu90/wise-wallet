package dev.gabriel.wisewallet.core.infrastructure.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gabriel.wisewallet.core.domain.events.DomainEvent;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class DomainEventSerializer implements Serializer<DomainEvent> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, DomainEvent event) {
        try {
            if(event == null) {
                return null;
            }
            return objectMapper.writeValueAsBytes(event);
        }catch(Exception e) {
            throw new SerializationException(e.getMessage());
        }
    }

    @Override
    public void close() {

    }
}
