package dev.gabriel.wisewallet.recurringbill.presentation.controllers;

import dev.gabriel.wisewallet.recurringbill.infrastructure.services.RecurringBillService;
import dev.gabriel.wisewallet.recurringbill.presentation.dtos.RecurringBillRequestDto;
import dev.gabriel.wisewallet.recurringbill.presentation.dtos.RecurringBillResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@SuppressWarnings("unused")
@RestController
@RequestMapping(value = "/api/recurringbills")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "Recurring bill endpoints")
public class RecurringBillController {
    private final RecurringBillService service;

    @GetMapping(value = "/{recurringBillId}")
    @Operation(summary = "Returns a recurring bill by a recurring bill uuid.")
    public ResponseEntity<RecurringBillResponseDto> getRecurringBill(@PathVariable UUID recurringBillId) {
        return ResponseEntity.ok().body(service.getRecurringBill(recurringBillId));
    }

    @PostMapping
    @Operation(summary = "Creates a recurring bill.")
    public ResponseEntity<RecurringBillResponseDto> newRecurringBill(@RequestBody RecurringBillRequestDto request) {
        return ResponseEntity.ok().body(service.newRecurringBill(request));
    }

    @PutMapping(value = "/{recurringBillId}")
    @Operation(summary = "Updates a recurring bill.")
    public ResponseEntity<RecurringBillResponseDto> updateRecurringBillData(@PathVariable UUID recurringBillId, @RequestBody RecurringBillRequestDto request) {
        request = new RecurringBillRequestDto(
                recurringBillId,
                request.name(),
                request.description(),
                request.amount(),
                request.currencyCode(),
                request.recurrence(),
                request.type(),
                request.maxPeriods(),
                request.walletId(),
                request.categoryId()
        );

        return ResponseEntity.ok().body(service.updateRecurringBillData(request));
    }

    @PatchMapping(value = "/execute/{recurringBillId}/{numberOfTimes}")
    @Operation(summary = "Executes a recurring bill period.")
    public ResponseEntity<Void> executeRecurringBillPeriod(@PathVariable UUID recurringBillId, @PathVariable Long numberOfTimes) {
        service.executeRecurringBillPeriod(recurringBillId, numberOfTimes);
        return ResponseEntity.ok().body(null);
    }

    @PatchMapping(value = "/reset/{recurringBillId}")
    @Operation(summary = "Resets a recurring bill.")
    public ResponseEntity<Void> resetRecurringBill(@PathVariable UUID recurringBillId) {
        service.resetRecurringBill(recurringBillId);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping(value = "/{recurringBillId}")
    @Operation(summary = "Deletes a recurring bill.")
    public ResponseEntity<Void> deleteRecurringBill(@PathVariable UUID recurringBillId) {
        service.deleteRecurringBill(recurringBillId);
        return ResponseEntity.ok().body(null);
    }
}
