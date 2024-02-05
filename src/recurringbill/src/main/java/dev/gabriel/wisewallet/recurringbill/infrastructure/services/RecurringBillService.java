package dev.gabriel.wisewallet.recurringbill.infrastructure.services;

import dev.gabriel.wisewallet.core.application.CommandBus;
import dev.gabriel.wisewallet.recurringbill.domain.commands.*;
import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBill;
import dev.gabriel.wisewallet.recurringbill.infrastructure.projection.RecurringBillProjection;
import dev.gabriel.wisewallet.recurringbill.infrastructure.projection.RecurringBillProjectionRepository;
import dev.gabriel.wisewallet.recurringbill.presentation.dtos.RecurringBillRequestDto;
import dev.gabriel.wisewallet.recurringbill.presentation.dtos.RecurringBillResponseDto;
import dev.gabriel.wisewallet.recurringbill.presentation.dtos.mappers.RecurringBillDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecurringBillService {
    private final RecurringBillProjectionRepository recurringBillProjectionRepository;
    private final RecurringBillDtoMapper dtoMapper;
    private final CommandBus<RecurringBillCommand> commandBus;

    public RecurringBillResponseDto getRecurringBill(UUID id) {
        return dtoMapper.toResponseDto(recurringBillProjectionRepository.find(id).orElse(null));
    }

    public RecurringBillResponseDto newRecurringBill(RecurringBillRequestDto request) {
        RecurringBill recurringBill = (RecurringBill) commandBus.execute(
                new CreateRecurringBillCommand(
                        request.id(),
                        request.name(),
                        request.description(),
                        request.amount(),
                        request.currencyCode(),
                        request.recurrence(),
                        request.maxPeriods(),
                        request.type(),
                        request.walletId(),
                        request.categoryId()
                )
        );

        return dtoMapper.toResponseDto(recurringBill);
    }

    public RecurringBillResponseDto updateRecurringBillData(RecurringBillRequestDto request) {
        RecurringBill recurringBill = null;
        if(request.name() != null) {
            recurringBill = (RecurringBill) commandBus
                    .execute(new RenameRecurringBillCommand(request.id(), request.name()));
        }
        if(request.description() != null) {
            recurringBill = (RecurringBill) commandBus
                    .execute(new ChangeRecurringBillDescriptionCommand(request.id(), request.description()));
        }
        if(request.amount() != null || request.currencyCode() != null) {
            recurringBill = (RecurringBill) commandBus
                    .execute(new UpdateRecurringBillAmountCommand(request.id(), request.amount(), request.currencyCode()));
        }
        if(request.type() != null) {
            recurringBill = (RecurringBill) commandBus
                    .execute(new ChangeRecurringBillTypeCommand(request.id(), request.type()));
        }
        if(request.recurrence() != null) {
            recurringBill = (RecurringBill) commandBus
                    .execute(new ChangeRecurringBillRecurrenceCommand(request.id(), request.recurrence()));
        }
        if(request.maxPeriods() != null) {
            recurringBill = (RecurringBill) commandBus
                    .execute(new ChangeRecurringBillMaxPeriodsCommand(request.id(), request.maxPeriods()));
        }
        if(request.categoryId() != null) {
            recurringBill = (RecurringBill) commandBus
                    .execute(new ChangeRecurringBillCategoryCommand(request.id(), request.categoryId()));
        }

        return dtoMapper.toResponseDto(recurringBill);
    }

    public void executeRecurringBillPeriod(UUID id, long numberOfTimes) {
        commandBus.execute(new ExecuteRecurringBillPeriodCommand(id, numberOfTimes));
    }

    public void resetRecurringBill(UUID id) {
        commandBus.execute(new ResetRecurringBillCommand(id));
    }

    public void deleteRecurringBill(UUID id) {
        commandBus.execute(new DeleteRecurringBillCommand(id));
    }
}
