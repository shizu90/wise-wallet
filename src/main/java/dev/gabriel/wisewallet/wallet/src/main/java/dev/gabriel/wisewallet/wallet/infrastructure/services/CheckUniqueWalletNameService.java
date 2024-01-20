package dev.gabriel.wisewallet.wallet.infrastructure.services;

import dev.gabriel.wisewallet.wallet.domain.services.CheckUniqueWalletName;
import dev.gabriel.wisewallet.wallet.infrastructure.projection.WalletProjectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CheckUniqueWalletNameService implements CheckUniqueWalletName {
    private final WalletProjectionRepository walletProjectionRepository;

    @Override
    public long exists(UUID userId, String name) {
        return walletProjectionRepository.findByUserIdAndName(userId, name).size();
    }
}
