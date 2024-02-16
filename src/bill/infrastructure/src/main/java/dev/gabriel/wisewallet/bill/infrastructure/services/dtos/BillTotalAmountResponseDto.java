package dev.gabriel.wisewallet.bill.infrastructure.services.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record BillTotalAmountResponseDto(
        UUID walletId,
        String currencyCode,
        BigDecimal totalIncomes,
        BigDecimal totalExpenses
) {}
