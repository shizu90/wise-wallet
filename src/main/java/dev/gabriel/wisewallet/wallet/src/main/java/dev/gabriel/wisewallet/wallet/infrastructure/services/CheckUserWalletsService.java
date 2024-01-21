package dev.gabriel.wisewallet.wallet.infrastructure.services;

import dev.gabriel.wisewallet.wallet.domain.services.CheckUserWallets;
import dev.gabriel.wisewallet.wallet.infrastructure.projection.WalletProjectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CheckUserWalletsService implements CheckUserWallets {
    private final WalletProjectionRepository walletProjectionRepository;

    @Override
    public int getSize(UUID userId) {
        return walletProjectionRepository.findByUserId(userId).size();
    }
}
