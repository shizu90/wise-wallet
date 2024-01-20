package dev.gabriel.wisewallet.wallet.presentation.controllers;

import dev.gabriel.wisewallet.wallet.domain.models.WalletType;
import dev.gabriel.wisewallet.wallet.infrastructure.services.WalletService;
import dev.gabriel.wisewallet.wallet.presentation.dtos.WalletListResponseDto;
import dev.gabriel.wisewallet.wallet.presentation.dtos.WalletRequestDto;
import dev.gabriel.wisewallet.wallet.presentation.dtos.WalletResponseDto;
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
public class WalletController {
    private final WalletService walletService;

    @GetMapping(value = "/{walletId}")
    public ResponseEntity<WalletResponseDto> getById(@PathVariable UUID walletId) {
        return ResponseEntity.ok().body(walletService.getById(walletId));
    }

    @GetMapping
    public ResponseEntity<WalletListResponseDto> getWallets(@NonNull @RequestParam("userId") UUID userId,
                                                            @RequestParam(value = "name", required = false) String name,
                                                            @RequestParam(value = "type", required = false) WalletType type,
                                                            @RequestParam(value = "page", required = false) Integer page,
                                                            @RequestParam(value = "limit", required = false) Integer limit
    ) {
        return ResponseEntity
                .ok()
                .body(walletService.get(
                        userId,
                        name,
                        type,
                        page == null ? 0 : page,
                        limit == null ? 4 : limit));
    }
    @PostMapping
    public ResponseEntity<WalletResponseDto> createWallet(@RequestBody WalletRequestDto request) {
        return ResponseEntity.ok().body(walletService.create(request));
    }

    @PutMapping(value = "/{walletId}")
    public ResponseEntity<WalletResponseDto> updateWallet(@PathVariable UUID walletId, @RequestBody WalletRequestDto request) {
        request = new WalletRequestDto(
                walletId,
                request.name(),
                request.description(),
                request.balance(),
                request.currencyCode(),
                request.main(),
                request.type(),
                request.userId()
        );
        return ResponseEntity.ok().body(walletService.update(request));
    }

    @DeleteMapping(value = "/{walletId}")
    public ResponseEntity<Void> deleteWallet(@PathVariable UUID walletId) {
        walletService.delete(walletId);
        return ResponseEntity.ok().body(null);
    }
}
