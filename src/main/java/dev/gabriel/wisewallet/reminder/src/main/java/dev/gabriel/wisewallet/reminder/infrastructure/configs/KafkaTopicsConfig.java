package dev.gabriel.wisewallet.reminder.infrastructure.configs;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.TopicBuilder;

@SuppressWarnings("unused")
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaTopicsConfig {
    private final Environment env;

    @Bean
    public NewTopic reminderIntegrationEventsTopic() {
        return TopicBuilder
                .name(env.getProperty("kafka.topics.name"))
                .partitions(10)
                .replicas(1)
                .build();
    }
}
