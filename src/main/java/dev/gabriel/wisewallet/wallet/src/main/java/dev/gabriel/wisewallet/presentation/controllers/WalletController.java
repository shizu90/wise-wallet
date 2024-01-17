package dev.gabriel.wisewallet.presentation.controllers;

import dev.gabriel.wisewallet.infrastructure.services.WalletService;
import dev.gabriel.wisewallet.presentation.dtos.WalletRequestDto;
import dev.gabriel.wisewallet.presentation.dtos.WalletResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<WalletResponseDto> getById(@PathVariable String walletId) {
        WalletResponseDto response = walletService.getById(UUID.fromString(walletId));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/all/{userId}/{pageNumber}/{limit}")
    public ResponseEntity<Page<WalletResponseDto>> getByUserId(@PathVariable String userId, @PathVariable int pageNumber, @PathVariable int limit) {
        Page<WalletResponseDto> response = walletService.getByUserId(UUID.fromString(userId), pageNumber, limit);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<WalletResponseDto> createWallet(@RequestBody WalletRequestDto request) {
        WalletResponseDto response = walletService.create(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/{walletId}")
    public ResponseEntity<WalletResponseDto> updateWallet(@PathVariable String walletId, @RequestBody WalletRequestDto request) {
        request = new WalletRequestDto(
                UUID.fromString(walletId),
                request.name(),
                request.description(),
                request.balance(),
                request.currencyCode(),
                request.main(),
                request.type(),
                request.userId()
        );
        WalletResponseDto response = walletService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{walletId}")
    public ResponseEntity<Void> deleteWallet(@PathVariable String walletId) {
        walletService.delete(UUID.fromString(walletId));
        return ResponseEntity.ok().body(null);
    }
}
