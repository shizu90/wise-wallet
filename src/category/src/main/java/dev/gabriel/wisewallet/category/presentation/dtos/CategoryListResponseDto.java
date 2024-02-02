package dev.gabriel.wisewallet.category.presentation.dtos;

import java.util.List;

public record CategoryListResponseDto(
        List<CategoryResponseDto> categories,
        long totalElements,
        long totalPages
) {}
