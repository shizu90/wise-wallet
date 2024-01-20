package dev.gabriel.wisewallet.category.infrastructure.services;

import dev.gabriel.wisewallet.category.domain.commands.CategoryCommand;
import dev.gabriel.wisewallet.category.domain.commands.CreateCategoryCommand;
import dev.gabriel.wisewallet.category.domain.commands.DeleteCategoryCommand;
import dev.gabriel.wisewallet.category.domain.commands.RenameCategoryCommand;
import dev.gabriel.wisewallet.category.domain.models.Category;
import dev.gabriel.wisewallet.category.infrastructure.projection.CategoryProjectionRepository;
import dev.gabriel.wisewallet.category.presentation.dtos.CategoryListResponseDto;
import dev.gabriel.wisewallet.category.presentation.dtos.CategoryRequestDto;
import dev.gabriel.wisewallet.category.presentation.dtos.CategoryResponseDto;
import dev.gabriel.wisewallet.category.presentation.dtos.mappers.CategoryDtoMapper;
import dev.gabriel.wisewallet.core.application.CommandBus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryService {
    private final CommandBus<CategoryCommand> commandBus;
    private final CategoryProjectionRepository categoryProjectionRepository;
    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryResponseDto getById(UUID id) {
        return categoryDtoMapper.toResponseDto(categoryProjectionRepository.find(id).orElse(null));
    }

    public CategoryListResponseDto get(UUID userId, String name, int page, int limit) {
        if(name == null)
            return categoryDtoMapper.toResponseDto(categoryProjectionRepository.findByUserId(userId, PageRequest.of(page, limit)));

        return categoryDtoMapper.toResponseDto(categoryProjectionRepository.findByUserIdAndName(userId, name, PageRequest.of(page, limit)));
    }

    public CategoryResponseDto create(CategoryRequestDto request) {
        Category category = (Category) commandBus.execute(new CreateCategoryCommand(
                request.id(),
                request.name(),
                request.userId()
        ));

        return categoryDtoMapper.toResponseDto(category);
    }

    public CategoryResponseDto update(CategoryRequestDto request) {
        Category category = null;
        if(!(request.name() == null || request.name().isEmpty() || request.name().isBlank())) {
            category = (Category) commandBus.execute(new RenameCategoryCommand(
                    request.id(),
                    request.name()
            ));
        }
        return categoryDtoMapper.toResponseDto(category);
    }

    public void delete(UUID id) {
        commandBus.execute(new DeleteCategoryCommand(id));
    }
}
