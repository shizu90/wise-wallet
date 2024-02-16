package dev.gabriel.wisewallet.recurringbill.infrastructure.services.dtos.mappers;

import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBill;
import dev.gabriel.wisewallet.recurringbill.infrastructure.projection.RecurringBillProjection;
import dev.gabriel.wisewallet.recurringbill.infrastructure.services.dtos.RecurringBillListResponseDto;
import dev.gabriel.wisewallet.recurringbill.infrastructure.services.dtos.RecurringBillResponseDto;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class RecurringBillDtoMapper {
    public RecurringBillResponseDto toResponseDto(@Nullable RecurringBill recurringBill) {
        if(recurringBill == null) return null;
        return new RecurringBillResponseDto(
                recurringBill.getId(),
                recurringBill.getName().getValue(),
                recurringBill.getDescription().getValue(),
                recurringBill.getAmount().getValue(),
                recurringBill.getAmount().getCurrencyCode(),
                recurringBill.getType(),
                recurringBill.getRecurrence().getValue(),
                recurringBill.getMaxPeriods().getValue(),
                recurringBill.getCurrentPeriods().getValue(),
                recurringBill.getLastPeriod(),
                recurringBill.getWalletId(),
                recurringBill.getCategoryId(),
                recurringBill.getCreatedAt(),
                recurringBill.getUpdatedAt()
        );
    }

    public RecurringBillResponseDto toResponseDto(@Nullable RecurringBillProjection recurringBillProjection) {
        if(recurringBillProjection == null) return null;
        return new RecurringBillResponseDto(
                recurringBillProjection.getId(),
                recurringBillProjection.getName(),
                recurringBillProjection.getDescription(),
                recurringBillProjection.getAmount(),
                recurringBillProjection.getCurrencyCode(),
                recurringBillProjection.getType(),
                recurringBillProjection.getRecurrence(),
                recurringBillProjection.getMaxPeriods(),
                recurringBillProjection.getCurrentPeriods(),
                recurringBillProjection.getLastPeriod(),
                recurringBillProjection.getWalletId(),
                recurringBillProjection.getCategoryId(),
                recurringBillProjection.getCreatedAt(),
                recurringBillProjection.getUpdatedAt()
        );
    }

    public RecurringBillListResponseDto toResponseDto(@Nullable Page<RecurringBillProjection> recurringBillProjectionList) {
        if(recurringBillProjectionList == null) return null;
        return new RecurringBillListResponseDto(
                recurringBillProjectionList.getContent().stream().map(this::toResponseDto).toList(),
                recurringBillProjectionList.getTotalElements(),
                recurringBillProjectionList.getTotalPages()
        );
    }
}
