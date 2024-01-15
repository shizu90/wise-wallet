package dev.gabriel.wisewallet.core.domain.events;

import java.math.BigInteger;

public record DomainEventWithId<T extends DomainEvent> (Long id, BigInteger transactionId, T event) {}
