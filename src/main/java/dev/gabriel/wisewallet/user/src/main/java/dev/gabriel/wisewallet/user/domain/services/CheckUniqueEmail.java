package dev.gabriel.wisewallet.user.domain.services;

import dev.gabriel.wisewallet.core.domain.services.DomainService;

public interface CheckUniqueEmail extends DomainService {
    long exists(String email);
}
