package dev.gabriel.wisewallet.bill.infrastructure.services.dtos;

import java.util.List;

public record BillListResponseDto(
        List<BillResponseDto> bills,
        long totalElements,
        long totalPages
) {}
