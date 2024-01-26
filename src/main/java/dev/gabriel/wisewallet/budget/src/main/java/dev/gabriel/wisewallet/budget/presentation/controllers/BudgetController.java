package dev.gabriel.wisewallet.budget.presentation.controllers;

import dev.gabriel.wisewallet.budget.infrastructure.services.BudgetService;
import dev.gabriel.wisewallet.budget.presentation.dtos.BudgetItemRequestDto;
import dev.gabriel.wisewallet.budget.presentation.dtos.BudgetListResponseDto;
import dev.gabriel.wisewallet.budget.presentation.dtos.BudgetRequestDto;
import dev.gabriel.wisewallet.budget.presentation.dtos.BudgetResponseDto;
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
@RequestMapping(value = "/api/budgets")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "Budget endpoints")
public class BudgetController {
    private final BudgetService budgetService;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Returns a budget by budget UUID.")
    public ResponseEntity<BudgetResponseDto> getBudget(@PathVariable UUID id) {
        return ResponseEntity.ok().body(budgetService.getBudget(id));
    }

    @GetMapping
    @Operation(summary = "Returns a page of budgets by user UUID.")
    public ResponseEntity<BudgetListResponseDto> getBudgets(@NonNull @RequestParam("userId") UUID userId,
                                                            @RequestParam(value = "name", required = false) String name,
                                                            @RequestParam(value = "page", required = false) Integer page,
                                                            @RequestParam(value = "limit", required = false) Integer limit
    ) {
        return ResponseEntity.ok().body(budgetService.getBudgets(userId, name, page == null ? 0 : page, limit == null ? 4 : limit));
    }

    @PostMapping
    @Operation(summary = "Creates a budget.")
    public ResponseEntity<BudgetResponseDto> newBudget(@RequestBody BudgetRequestDto request) {
        return ResponseEntity.ok().body(budgetService.newBudget(request));
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Updates a budget.")
    public ResponseEntity<BudgetResponseDto> updateBudget(@PathVariable UUID id, @RequestBody BudgetRequestDto request) {
        request = new BudgetRequestDto(
                id,
                request.name(),
                request.description(),
                request.currencyCode(),
                request.userId()
        );

        return ResponseEntity.ok().body(budgetService.updateBudget(request));
    }

    @PatchMapping(value = "/add/{id}")
    @Operation(summary = "Add an item to specified budget.")
    public ResponseEntity<BudgetResponseDto> addItem(@PathVariable UUID id, @RequestBody BudgetItemRequestDto request) {
        return ResponseEntity.ok().body(budgetService.addItem(id, request));
    }

    @PatchMapping(value = "/remove/{id}")
    @Operation(summary = "Remove an item from specified budget.")
    public ResponseEntity<BudgetResponseDto> removeItem(@PathVariable UUID id, @RequestBody BudgetItemRequestDto request) {
        return ResponseEntity.ok().body(budgetService.removeItem(id, request.billId()));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a budget.")
    public ResponseEntity<Void> deleteBudget(@PathVariable UUID id) {
        budgetService.deleteBudget(id);

        return ResponseEntity.ok().body(null);
    }
}
