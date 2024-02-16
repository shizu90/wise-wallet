package dev.gabriel.wisewallet.recurringbill.infrastructure.services.dtos;

import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBillType;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record RecurringBillResponseDto(
        UUID id,
        String name,
        String description,
        BigDecimal amount,
        String currencyCode,
        RecurringBillType type,
        Long recurrence,
        Long maxPeriods,
        Long currentPeriods,
        LocalDate lastPeriod,
        UUID walletId,
        UUID categoryId,
        Instant createdAt,
        Instant updatedAt
) {}
