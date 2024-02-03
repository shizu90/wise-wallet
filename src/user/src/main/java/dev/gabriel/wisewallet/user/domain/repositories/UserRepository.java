package dev.gabriel.wisewallet.user.domain.repositories;

import dev.gabriel.wisewallet.user.domain.models.User;
import dev.gabriel.wisewallet.core.domain.repositories.DomainRepository;
import lombok.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends DomainRepository {
    Optional<User> load(@NonNull UUID id);
    Optional<User> load(@NonNull UUID id, Long version);
    void saveChanges(@NonNull User user);
}
