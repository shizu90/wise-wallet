package dev.gabriel.wisewallet.wallet.presentation.dtos;

import java.util.List;

public record WalletListResponseDto(
        List<WalletResponseDto> wallets,
        long totalElements,
        long totalPages
) {}
