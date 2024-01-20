package dev.gabriel.wisewallet.bill.presentation.dtos;

import java.util.List;

public record BillListResponseDto(
        List<BillResponseDto> bills,
        long totalElements,
        long totalPages
) {}
