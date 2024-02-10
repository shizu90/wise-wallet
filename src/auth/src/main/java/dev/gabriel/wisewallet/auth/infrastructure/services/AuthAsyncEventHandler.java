package dev.gabriel.wisewallet.auth.infrastructure.services;

import dev.gabriel.wisewallet.auth.infrastructure.entities.AuthCredentials;
import dev.gabriel.wisewallet.auth.infrastructure.entities.AuthCredentialsRepository;
import dev.gabriel.wisewallet.user.domain.events.UserCreatedEvent;
import dev.gabriel.wisewallet.user.domain.events.UserDeletedEvent;
import dev.gabriel.wisewallet.user.domain.events.UserEmailChangedEvent;
import dev.gabriel.wisewallet.user.domain.events.UserPasswordChangedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthAsyncEventHandler {
    private final AuthCredentialsRepository authCredentialsRepository;

    @KafkaListener(topics = "UserCreatedEvent")
    public void handle(UserCreatedEvent event, Acknowledgment ack) {
        AuthCredentials authCredentials = AuthCredentials
                .create(event.getAggregateId(), event.getEmail(), event.getPassword(), null);

        authCredentialsRepository.save(authCredentials);

        ack.acknowledge();
    }

    @KafkaListener(topics = "UserDeletedEvent")
    public void handle(UserDeletedEvent event, Acknowledgment ack) {
        authCredentialsRepository.deleteById(event.getAggregateId());

        ack.acknowledge();
    }

    @KafkaListener(topics = "UserEmailChangedEvent")
    public void handle(UserEmailChangedEvent event, Acknowledgment ack) {
        AuthCredentials authCredentials = authCredentialsRepository.findById(event.getAggregateId()).orElse(null);

        if(authCredentials != null) {
            authCredentials.setEmail(event.getEmail());
            authCredentialsRepository.save(authCredentials);
        }

        ack.acknowledge();
    }

    @KafkaListener(topics = "UserPasswordChangedEvent")
    public void handle(UserPasswordChangedEvent event, Acknowledgment ack) {
        AuthCredentials authCredentials = authCredentialsRepository.findById(event.getAggregateId()).orElse(null);

        if(authCredentials != null) {
            authCredentials.setPassword(event.getPassword());
            authCredentialsRepository.save(authCredentials);
        }

        ack.acknowledge();
    }
}
