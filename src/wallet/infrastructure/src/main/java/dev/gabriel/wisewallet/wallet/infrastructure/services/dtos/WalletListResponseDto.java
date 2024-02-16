package dev.gabriel.wisewallet.wallet.infrastructure.services.dtos;

import java.util.List;

public record WalletListResponseDto(
        List<WalletResponseDto> wallets,
        long totalElements,
        long totalPages
) {}
