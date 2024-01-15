package dev.gabriel.wisewallet.user.domain.repositories;

import dev.gabriel.wisewallet.core.domain.repositories.DomainRepository;
import dev.gabriel.wisewallet.user.domain.models.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends DomainRepository {
    Optional<User> load(UUID userId);
    void saveChanges(User user);
}
