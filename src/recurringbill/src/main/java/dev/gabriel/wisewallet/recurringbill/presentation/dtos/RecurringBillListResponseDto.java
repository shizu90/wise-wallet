package dev.gabriel.wisewallet.recurringbill.presentation.dtos;

import java.util.List;

public record RecurringBillListResponseDto(
        List<RecurringBillResponseDto> recurringBills,
        long totalElements,
        long totalPages
) {}
