package dev.gabriel.wisewallet.wallet.presentation.controllers;

import dev.gabriel.wisewallet.wallet.domain.models.WalletType;
import dev.gabriel.wisewallet.wallet.infrastructure.services.WalletService;
import dev.gabriel.wisewallet.wallet.infrastructure.services.dtos.WalletListResponseDto;
import dev.gabriel.wisewallet.wallet.infrastructure.services.dtos.WalletRequestDto;
import dev.gabriel.wisewallet.wallet.infrastructure.services.dtos.WalletResponseDto;
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
@RequestMapping(value = "/api/wallets")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "Wallet endpoints")
public class WalletController {
    private final WalletService walletService;

    @GetMapping(value = "/{walletId}")
    @Operation(summary = "Returns a wallet by wallet uuid.")
    public ResponseEntity<WalletResponseDto> getWallet(@PathVariable UUID walletId) {
        return ResponseEntity.ok().body(walletService.getWallet(walletId));
    }

    @GetMapping
    @Operation(summary = "Returns a page of wallets by user uuid.")
    public ResponseEntity<WalletListResponseDto> getWallets(@NonNull @RequestParam("userId") UUID userId,
                                                            @RequestParam(value = "name", required = false) String name,
                                                            @RequestParam(value = "type", required = false) WalletType type,
                                                            @RequestParam(value = "page", required = false) Integer page,
                                                            @RequestParam(value = "limit", required = false) Integer limit
    ) {
        return ResponseEntity
                .ok()
                .body(walletService.getWallets(
                        userId,
                        name,
                        type,
                        page == null ? 0 : page,
                        limit == null ? 4 : limit));
    }
    @PostMapping
    @Operation(summary = "Creates a wallet.")
    public ResponseEntity<WalletResponseDto> newWallet(@RequestBody WalletRequestDto request) {
        return ResponseEntity.ok().body(walletService.newWallet(request));
    }

    @PutMapping(value = "/{walletId}")
    @Operation(summary = "Updates a wallet.")
    public ResponseEntity<WalletResponseDto> updateWalletData(@PathVariable UUID walletId, @RequestBody WalletRequestDto request) {
        request = new WalletRequestDto(
                walletId,
                request.name(),
                request.description(),
                request.balance(),
                request.currencyCode(),
                request.type(),
                request.userId()
        );
        return ResponseEntity.ok().body(walletService.updateWalletData(request));
    }

    @DeleteMapping(value = "/{walletId}")
    @Operation(summary = "Deletes a wallet.")
    public ResponseEntity<Void> deleteWallet(@PathVariable UUID walletId) {
        walletService.deleteWallet(walletId);
        return ResponseEntity.ok().body(null);
    }
}
