package dev.gabriel.wisewallet.reminder.domain.services;

import dev.gabriel.wisewallet.core.domain.services.DomainService;

import java.util.UUID;

public interface CheckUniqueReminderName extends DomainService {
    boolean exists(String name, UUID userId);
}
