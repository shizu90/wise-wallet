package dev.gabriel.wisewallet.category.presentation.controllers;

import dev.gabriel.wisewallet.category.infrastructure.services.CategoryService;
import dev.gabriel.wisewallet.category.presentation.dtos.CategoryListResponseDto;
import dev.gabriel.wisewallet.category.presentation.dtos.CategoryRequestDto;
import dev.gabriel.wisewallet.category.presentation.dtos.CategoryResponseDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/categories")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SuppressWarnings("unused")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(categoryService.getById(id));
    }

    @GetMapping
    public ResponseEntity<CategoryListResponseDto> getCategories(@NonNull @RequestParam("userId") UUID userId,
                                                                 @RequestParam(value = "name", required = false) String name,
                                                                 @RequestParam(value = "page", required = false) Integer page,
                                                                 @RequestParam(value = "limit", required = false) Integer limit
    ) {
        return ResponseEntity.ok().body(categoryService.get(userId, name, page == null ? 0 : page, limit == null ? 4 : limit));
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto request) {
        return ResponseEntity.ok().body(categoryService.create(request));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable UUID id, @RequestBody CategoryRequestDto request) {
        request = new CategoryRequestDto(
                id,
                request.name(),
                request.userId()
        );
        return ResponseEntity.ok().body(categoryService.update(request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.ok().body(null);
    }
}
