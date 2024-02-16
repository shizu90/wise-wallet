package dev.gabriel.wisewallet.category.presentation.controllers;

import dev.gabriel.wisewallet.category.infrastructure.services.CategoryService;
import dev.gabriel.wisewallet.category.infrastructure.services.dtos.CategoryListResponseDto;
import dev.gabriel.wisewallet.category.infrastructure.services.dtos.CategoryRequestDto;
import dev.gabriel.wisewallet.category.infrastructure.services.dtos.CategoryResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@SuppressWarnings("unused")
@RestController
@RequestMapping(value = "/api/categories")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "Category endpoints")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Returns a category by category UUID.")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable UUID id) {
        return ResponseEntity.ok().body(categoryService.getCategory(id));
    }

    @GetMapping
    @Operation(summary = "Returns a page of categories by user UUID.")
    public ResponseEntity<CategoryListResponseDto> getCategories(@NonNull @RequestParam("userId") UUID userId,
                                                                 @RequestParam(value = "name", required = false) String name,
                                                                 @RequestParam(value = "page", required = false) Integer page,
                                                                 @RequestParam(value = "limit", required = false) Integer limit
    ) {
        return ResponseEntity.ok().body(categoryService.getCategories(userId, name, page == null ? 0 : page, limit == null ? 4 : limit));
    }

    @PostMapping
    @Operation(summary = "Creates a category.")
    public ResponseEntity<CategoryResponseDto> newCategory(@RequestBody CategoryRequestDto request) {
        return ResponseEntity.ok().body(categoryService.newCategory(request));
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Updates a category.")
    public ResponseEntity<CategoryResponseDto> updateCategoryData(@PathVariable UUID id, @RequestBody CategoryRequestDto request) {
        request = new CategoryRequestDto(
                id,
                request.name(),
                request.userId()
        );
        return ResponseEntity.ok().body(categoryService.updateCategoryData(request));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a category.")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().body(null);
    }
}
