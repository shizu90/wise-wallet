package dev.gabriel.wisewallet.auth.infrastructure.entities;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthCredentialsRepository extends MongoRepository<AuthCredentials, UUID> {
}
