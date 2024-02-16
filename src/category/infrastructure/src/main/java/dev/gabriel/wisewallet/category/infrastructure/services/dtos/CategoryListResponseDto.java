package dev.gabriel.wisewallet.category.infrastructure.services.dtos;

import java.util.List;

public record CategoryListResponseDto(
        List<CategoryResponseDto> categories,
        long totalElements,
        long totalPages
) {}
