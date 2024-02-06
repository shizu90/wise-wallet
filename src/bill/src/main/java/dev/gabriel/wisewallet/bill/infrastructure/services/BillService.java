package dev.gabriel.wisewallet.bill.infrastructure.services;

import dev.gabriel.wisewallet.bill.domain.commands.*;
import dev.gabriel.wisewallet.bill.domain.models.Bill;
import dev.gabriel.wisewallet.bill.domain.models.BillType;
import dev.gabriel.wisewallet.bill.infrastructure.projection.BillProjection;
import dev.gabriel.wisewallet.bill.presentation.dtos.BillListResponseDto;
import dev.gabriel.wisewallet.bill.presentation.dtos.BillRequestDto;
import dev.gabriel.wisewallet.bill.presentation.dtos.BillResponseDto;
import dev.gabriel.wisewallet.bill.presentation.dtos.BillTotalAmountResponseDto;
import dev.gabriel.wisewallet.bill.presentation.dtos.mappers.BillDtoMapper;
import dev.gabriel.wisewallet.bill.infrastructure.projection.BillProjectionRepository;
import dev.gabriel.wisewallet.core.application.CommandBus;
import dev.gabriel.wisewallet.currency.domain.models.Currency;
import dev.gabriel.wisewallet.currency.domain.services.CurrencyConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BillService {
    private final CommandBus<BillCommand> commandBus;
    private final BillProjectionRepository billProjectionRepository;
    private final CurrencyConversion currencyConversion;
    private final BillDtoMapper dtoMapper;

    public BillResponseDto getBill(UUID id) {
        return dtoMapper.toResponseDto(billProjectionRepository.find(id).orElse(null));
    }

    public BillTotalAmountResponseDto getTotalAmount(UUID walletId, String currencyCode) {
        List<BillProjection> bills = billProjectionRepository.findByWalletId(walletId);
        BigDecimal totalIncomes = BigDecimal.ZERO;
        BigDecimal totalExpenses = BigDecimal.ZERO;

        for(BillProjection bill : bills) {
            if(bill.getType().equals(BillType.EXPENSE)) {
                totalExpenses = totalExpenses.add(currencyConversion.convert(
                        Currency.create(bill.getAmount(), bill.getCurrencyCode()),
                        currencyCode
                ).getValue());
            }else {
                totalIncomes = totalIncomes.add(currencyConversion.convert(
                        Currency.create(bill.getAmount(), bill.getCurrencyCode()),
                        currencyCode
                ).getValue());
            }
        }

        return new BillTotalAmountResponseDto(walletId, currencyCode, totalIncomes, totalExpenses);
    }

    public BillListResponseDto getBills(UUID walletId, String name, BillType type, UUID categoryId, int page, int limit) {
        return dtoMapper.toResponseDto(billProjectionRepository
                                        .find(walletId, name, type, categoryId, PageRequest.of(page, limit)));
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
        if(request.amount() != null || request.currencyCode() != null) {
            bill = (Bill) commandBus.execute(new UpdateBillAmountCommand(
                    request.id(),
                    request.amount(),
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
