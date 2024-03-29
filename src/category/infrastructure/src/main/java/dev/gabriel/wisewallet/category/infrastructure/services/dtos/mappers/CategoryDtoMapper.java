package dev.gabriel.wisewallet.category.infrastructure.services.dtos.mappers;

import dev.gabriel.wisewallet.category.domain.models.Category;
import dev.gabriel.wisewallet.category.infrastructure.projection.CategoryProjection;
import dev.gabriel.wisewallet.category.infrastructure.services.dtos.CategoryListResponseDto;
import dev.gabriel.wisewallet.category.infrastructure.services.dtos.CategoryResponseDto;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoMapper {
    public CategoryResponseDto toResponseDto(@Nullable Category category) {
        if(category == null) return null;
        return new CategoryResponseDto(
                category.getId(),
                category.getName().getValue(),
                category.getUserId(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }

    public CategoryResponseDto toResponseDto(@Nullable CategoryProjection categoryProjection) {
        if(categoryProjection == null) return null;
        return new CategoryResponseDto(
                categoryProjection.getId(),
                categoryProjection.getName(),
                categoryProjection.getUserId(),
                categoryProjection.getCreatedAt(),
                categoryProjection.getUpdatedAt()
        );
    }

    public CategoryListResponseDto toResponseDto(@Nullable Page<CategoryProjection> categoryProjectionList) {
        if(categoryProjectionList == null) return null;
        return new CategoryListResponseDto(
                categoryProjectionList.getContent().stream().map(this::toResponseDto).toList(),
                categoryProjectionList.getTotalElements(),
                categoryProjectionList.getTotalPages()
        );
    }
}
