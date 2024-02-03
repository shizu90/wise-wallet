package dev.gabriel.wisewallet.user.infrastructure.eventstore;

import dev.gabriel.wisewallet.user.domain.models.AggregateType;
import dev.gabriel.wisewallet.user.domain.models.User;
import dev.gabriel.wisewallet.core.infrastructure.eventstore.services.AggregateService;
import dev.gabriel.wisewallet.user.domain.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserEventStore implements UserRepository {
    private final AggregateService aggregateService;

    @Override
    public Optional<User> load(@NonNull UUID userId) {
        User user = (User) aggregateService.load(AggregateType.USER.toString(), userId, null);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> load(@NonNull UUID userId, Long version) {
        User user = (User) aggregateService.load(AggregateType.USER.toString(), userId, version);
        return Optional.ofNullable(user);
    }

    @Override
    public void saveChanges(@NonNull User user) {
        aggregateService.save(user);
    }
}
