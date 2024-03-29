package dev.gabriel.wisewallet.category.infrastructure.services;

import dev.gabriel.wisewallet.category.domain.commands.CategoryCommand;
import dev.gabriel.wisewallet.category.domain.commands.CreateCategoryCommand;
import dev.gabriel.wisewallet.category.domain.commands.DeleteCategoryCommand;
import dev.gabriel.wisewallet.category.domain.commands.RenameCategoryCommand;
import dev.gabriel.wisewallet.category.domain.models.Category;
import dev.gabriel.wisewallet.category.infrastructure.projection.CategoryProjectionRepository;
import dev.gabriel.wisewallet.category.infrastructure.services.dtos.CategoryListResponseDto;
import dev.gabriel.wisewallet.category.infrastructure.services.dtos.CategoryRequestDto;
import dev.gabriel.wisewallet.category.infrastructure.services.dtos.CategoryResponseDto;
import dev.gabriel.wisewallet.category.infrastructure.services.dtos.mappers.CategoryDtoMapper;
import dev.gabriel.wisewallet.core.application.CommandBus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryService {
    private final CommandBus<CategoryCommand> commandBus;
    private final CategoryProjectionRepository categoryProjectionRepository;
    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryResponseDto getCategory(UUID id) {
        return categoryDtoMapper.toResponseDto(categoryProjectionRepository.find(id).orElse(null));
    }

    public CategoryListResponseDto getCategories(UUID userId, String name, int page, int limit) {
        return categoryDtoMapper.toResponseDto(categoryProjectionRepository.find(userId, name, PageRequest.of(page, limit)));
    }

    public CategoryResponseDto newCategory(CategoryRequestDto request) {
        Category category = (Category) commandBus.execute(new CreateCategoryCommand(
                UUID.randomUUID(),
                request.name(),
                request.userId()
        ));

        return categoryDtoMapper.toResponseDto(category);
    }

    public CategoryResponseDto updateCategoryData(CategoryRequestDto request) {
        Category category = null;
        if(!(request.name() == null || request.name().isBlank())) {
            category = (Category) commandBus.execute(new RenameCategoryCommand(
                    request.id(),
                    request.name()
            ));
        }
        return categoryDtoMapper.toResponseDto(category);
    }

    public void deleteCategory(UUID id) {
        commandBus.execute(new DeleteCategoryCommand(id));
    }
}
