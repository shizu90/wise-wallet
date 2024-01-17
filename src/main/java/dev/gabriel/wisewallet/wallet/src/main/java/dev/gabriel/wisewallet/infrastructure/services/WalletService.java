package dev.gabriel.wisewallet.infrastructure.services;

import dev.gabriel.wisewallet.core.application.CommandBus;
import dev.gabriel.wisewallet.domain.commands.*;
import dev.gabriel.wisewallet.domain.models.Wallet;
import dev.gabriel.wisewallet.infrastructure.projection.WalletProjection;
import dev.gabriel.wisewallet.infrastructure.projection.WalletProjectionRepository;
import dev.gabriel.wisewallet.presentation.dtos.DtoMapper;
import dev.gabriel.wisewallet.presentation.dtos.WalletRequestDto;
import dev.gabriel.wisewallet.presentation.dtos.WalletResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WalletService {
    private final CommandBus<WalletCommand> commandBus;
    private final WalletProjectionRepository walletProjectionRepository;
    private final DtoMapper dtoMapper;

    public WalletResponseDto getById(UUID walletId) {
        return dtoMapper.toResponseDto(walletProjectionRepository.findById(walletId).orElse(null));
    }

    public Page<WalletResponseDto> getByUserId(UUID userId, int pageNumber, int limit) {
        Page<WalletProjection> page = walletProjectionRepository.findByUserId(userId, PageRequest.of(pageNumber, limit));
        List<WalletResponseDto> content = page.stream().map(dtoMapper::toResponseDto).toList();
        return new PageImpl<WalletResponseDto>(content);
    }

    public WalletResponseDto create(WalletRequestDto walletRequestDto) {
        Wallet wallet = (Wallet) commandBus.execute(
                new CreateWalletCommand(
                    UUID.randomUUID(),
                    walletRequestDto.name(),
                    walletRequestDto.description(),
                    walletRequestDto.balance(),
                    walletRequestDto.currencyCode(),
                    walletRequestDto.main(),
                    walletRequestDto.type(),
                    walletRequestDto.userId()
                ));

        return dtoMapper.toResponseDto(wallet);
    }

    public WalletResponseDto update(WalletRequestDto request) {
        Wallet wallet = null;
        if(!(request.name() == null || request.name().isEmpty() || request.name().isBlank())) {
            wallet = (Wallet) commandBus.execute(
                    new RenameWalletCommand(
                            request.id(),
                            request.name()
                    )
            );
        }
        if(!(request.description() == null || request.description().isEmpty() || request.description().isBlank())) {
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
        if(!(request.main() == null)) {
            wallet = (Wallet) commandBus.execute(
                    new ToggleWalletMainCommand(
                            request.id(),
                            request.main()
                    )
            );
        }
        if(!(request.currencyCode() == null || request.currencyCode().isEmpty() || request.currencyCode().isBlank())) {
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

    public void delete(UUID id) {
        commandBus.execute(new DeleteWalletCommand(id));
    }
}
