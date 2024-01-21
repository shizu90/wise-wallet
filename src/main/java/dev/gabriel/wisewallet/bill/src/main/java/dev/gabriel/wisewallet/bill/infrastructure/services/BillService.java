package dev.gabriel.wisewallet.bill.infrastructure.services;

import dev.gabriel.wisewallet.bill.domain.commands.*;
import dev.gabriel.wisewallet.bill.domain.models.Bill;
import dev.gabriel.wisewallet.bill.domain.models.BillType;
import dev.gabriel.wisewallet.bill.presentation.dtos.BillListResponseDto;
import dev.gabriel.wisewallet.bill.presentation.dtos.BillRequestDto;
import dev.gabriel.wisewallet.bill.presentation.dtos.BillResponseDto;
import dev.gabriel.wisewallet.bill.presentation.dtos.mappers.BillDtoMapper;
import dev.gabriel.wisewallet.bill.infrastructure.projection.BillProjectionRepository;
import dev.gabriel.wisewallet.core.application.CommandBus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BillService {
    private final CommandBus<BillCommand> commandBus;
    private final BillProjectionRepository billProjectionRepository;
    private final BillDtoMapper dtoMapper;

    public BillResponseDto getBill(UUID id) {
        return dtoMapper.toResponseDto(billProjectionRepository.find(id).orElse(null));
    }

    public BillListResponseDto getBills(UUID walletId, String name, BillType type, int page, int limit) {
        if(name == null && type == null)
            return dtoMapper.toResponseDto(billProjectionRepository.findByWalletId(walletId, PageRequest.of(page, limit)));
        if(name != null && type != null)
            return dtoMapper.toResponseDto(billProjectionRepository.findByWalletIdAndNameOrType(walletId, name, type, PageRequest.of(page, limit)));

        return dtoMapper.toResponseDto(billProjectionRepository
                                        .findByWalletIdAndNameAndType(
                                                walletId,
                                                name == null ? "'" : name,
                                                type,
                                                PageRequest.of(page, limit)));
    }

    public BillResponseDto newBill(BillRequestDto request) {
        Bill bill = (Bill) commandBus.execute(new CreateBillCommand(
                UUID.randomUUID(),
                request.name(),
                request.description(),
                request.amount(),
                request.currencyCode(),
                request.type(),
                request.walletId(),
                request.categoryId()
        ));

        return dtoMapper.toResponseDto(bill);
    }

    public BillResponseDto updateBillData(BillRequestDto request) {
        Bill bill = null;
        if(request.name() != null) {
            bill = (Bill) commandBus.execute(new RenameBillCommand(
                    request.id(),
                    request.name()
            ));
        }
        if(request.description() != null) {
            bill = (Bill) commandBus.execute(new ChangeBillDescriptionCommand(
                    request.id(),
                    request.description()
            ));
        }
        if(request.amount() != null) {
            bill = (Bill) commandBus.execute(new UpdateBillAmountCommand(
                    request.id(),
                    request.amount(),
                    null
            ));
        }
        if(request.currencyCode() != null) {
            bill = (Bill) commandBus.execute(new UpdateBillAmountCommand(
                    request.id(),
                    null,
                    request.currencyCode()
            ));
        }
        if(request.type() != null) {
            bill = (Bill) commandBus.execute(new ChangeBillTypeCommand(
                    request.id(),
                    request.type()
            ));
        }
        if(request.categoryId() != null) {
            bill = (Bill) commandBus.execute(new ChangeBillCategoryCommand(
                    request.id(),
                    request.categoryId()
            ));
        }
        if(request.walletId() != null) {
            bill = (Bill) commandBus.execute(new ChangeBillWalletCommand(
                    request.id(),
                    request.walletId()
            ));
        }

        return dtoMapper.toResponseDto(bill);
    }

    public void deleteBill(UUID id) {
        commandBus.execute(new DeleteBillCommand(id));
    }
}
