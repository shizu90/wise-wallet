package dev.gabriel.wisewallet.bill.presentation.controllers;

import dev.gabriel.wisewallet.bill.domain.models.BillType;
import dev.gabriel.wisewallet.bill.infrastructure.services.BillService;
import dev.gabriel.wisewallet.bill.infrastructure.services.dtos.BillListResponseDto;
import dev.gabriel.wisewallet.bill.infrastructure.services.dtos.BillRequestDto;
import dev.gabriel.wisewallet.bill.infrastructure.services.dtos.BillResponseDto;
import dev.gabriel.wisewallet.bill.infrastructure.services.dtos.BillTotalAmountResponseDto;
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
@RequestMapping(value = "/api/bills")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "Bill endpoints")
public class BillController {
    private final BillService billService;

    @GetMapping(value = "/{billId}")
    @Operation(summary = "Returns a bill by a bill uuid.")
    public ResponseEntity<BillResponseDto> getBill(@PathVariable UUID billId) {
        return ResponseEntity.ok().body(billService.getBill(billId));
    }

    @GetMapping(value = "/total/{walletId}/{currencyCode}")
    @Operation(summary = "Returns total incomes and total expenses from specified wallet.")
    public ResponseEntity<BillTotalAmountResponseDto> getTotalAmount(@PathVariable UUID walletId, @PathVariable String currencyCode) {
        return ResponseEntity.ok().body(billService.getTotalAmount(walletId, currencyCode));
    }

    @GetMapping
    @Operation(summary = "Returns a page of bills by wallet uuid.")
    public ResponseEntity<BillListResponseDto> getBills(@NonNull @RequestParam("walletId") UUID walletId,
                                                        @RequestParam(value = "name", required = false) String name,
                                                        @RequestParam(value = "type", required = false) BillType type,
                                                        @RequestParam(value = "categoryId", required = false) UUID categoryId,
                                                        @RequestParam(value = "page", required = false) Integer page,
                                                        @RequestParam(value = "limit", required = false) Integer limit
    ) {
        return ResponseEntity
                .ok()
                .body(billService.getBills(walletId, name, type, categoryId, page == null ? 0 : page, limit == null ? 4 : limit));
    }

    @PostMapping
    @Operation(summary = "Creates a bill.")
    public ResponseEntity<BillResponseDto> createBill(@RequestBody BillRequestDto request) {
        return ResponseEntity.ok().body(billService.newBill(request));
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Updates a bill.")
    public ResponseEntity<BillResponseDto> updateBill(@PathVariable UUID id, @RequestBody BillRequestDto request) {
        request = new BillRequestDto(
                id,
                request.name(),
                request.description(),
                request.amount(),
                request.currencyCode(),
                request.type(),
                request.walletId(),
                request.categoryId()
        );
        return ResponseEntity.ok().body(billService.updateBillData(request));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a bill.")
    public ResponseEntity<Void> deleteBill(@PathVariable UUID id) {
        billService.deleteBill(id);
        return ResponseEntity.ok().body(null);
    }
}
