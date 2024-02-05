package dev.gabriel.wisewallet.recurringbill.presentation.dtos;

import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBillType;

import java.math.BigDecimal;
import java.util.UUID;

public record RecurringBillRequestDto(
        UUID id,
        String name,
        String description,
        BigDecimal amount,
        String currencyCode,
        Long recurrence,
        RecurringBillType type,
        Long maxPeriods,
        UUID walletId,
        UUID categoryId
) {}
