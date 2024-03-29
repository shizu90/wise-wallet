package dev.gabriel.wisewallet.wallet.infrastructure.services;

import dev.gabriel.wisewallet.core.application.CommandBus;
import dev.gabriel.wisewallet.wallet.domain.commands.*;
import dev.gabriel.wisewallet.wallet.domain.models.Wallet;
import dev.gabriel.wisewallet.wallet.domain.models.WalletType;
import dev.gabriel.wisewallet.wallet.infrastructure.projection.WalletProjectionRepository;
import dev.gabriel.wisewallet.wallet.infrastructure.services.dtos.WalletListResponseDto;
import dev.gabriel.wisewallet.wallet.infrastructure.services.dtos.WalletRequestDto;
import dev.gabriel.wisewallet.wallet.infrastructure.services.dtos.WalletResponseDto;
import dev.gabriel.wisewallet.wallet.infrastructure.services.dtos.mappers.WalletDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WalletService {
    private final CommandBus<WalletCommand> commandBus;
    private final WalletProjectionRepository walletProjectionRepository;
    private final WalletDtoMapper dtoMapper;

    public WalletResponseDto getWallet(UUID walletId) {
        return dtoMapper.toResponseDto(walletProjectionRepository.find(walletId).orElse(null));
    }

    public WalletListResponseDto getWallets(UUID userId, String name, WalletType type, int page, int limit) {
        return dtoMapper.toResponseDto(walletProjectionRepository
                                        .find(userId, name, type, PageRequest.of(page, limit)));
    }

    public WalletResponseDto newWallet(WalletRequestDto walletRequestDto) {
        Wallet wallet = (Wallet) commandBus.execute(
                new CreateWalletCommand(
                    UUID.randomUUID(),
                    walletRequestDto.name(),
                    walletRequestDto.description(),
                    walletRequestDto.balance(),
                    walletRequestDto.currencyCode(),
                    walletRequestDto.type(),
                    walletRequestDto.userId()
                ));

        return dtoMapper.toResponseDto(wallet);
    }

    public WalletResponseDto updateWalletData(WalletRequestDto request) {
        Wallet wallet = null;
        if(!(request.name() == null || request.name().isBlank())) {
            wallet = (Wallet) commandBus.execute(
                    new RenameWalletCommand(
                            request.id(),
                            request.name()
                    )
            );
        }
        if(!(request.description() == null || request.description().isBlank())) {
            wallet = (Wallet) commandBus.execute(
                    new ChangeWalletDescriptionCommand(
                            request.id(),
                            request.description()
                    )
            );
        }
        if(!(request.type() == null)) {
            wallet = (Wallet) commandBus.execute(
                    new ChangeWalletTypeCommand(
                            request.id(),
                            request.type()
                    )
            );
        }
        if(!(request.currencyCode() == null || request.currencyCode().isBlank())) {
            wallet = (Wallet) commandBus.execute(
                    new UpdateWalletBalanceCommand(
                            request.id(),
                            null,
                            request.currencyCode()
                    )
            );
        }
        if(!(request.balance() == null)) {
            wallet = (Wallet) commandBus.execute(
                    new UpdateWalletBalanceCommand(
                            request.id(),
                            request.balance(),
                            null
                    )
            );
        }

        return dtoMapper.toResponseDto(wallet);
    }

    public void deleteWallet(UUID id) {
        commandBus.execute(new DeleteWalletCommand(id));
    }
}
