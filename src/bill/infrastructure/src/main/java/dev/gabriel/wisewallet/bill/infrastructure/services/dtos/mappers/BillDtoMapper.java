package dev.gabriel.wisewallet.bill.infrastructure.services.dtos.mappers;

import dev.gabriel.wisewallet.bill.domain.models.Bill;
import dev.gabriel.wisewallet.bill.infrastructure.services.dtos.BillListResponseDto;
import dev.gabriel.wisewallet.bill.infrastructure.services.dtos.BillResponseDto;
import dev.gabriel.wisewallet.bill.infrastructure.projection.BillProjection;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class BillDtoMapper {
    public BillResponseDto toResponseDto(@Nullable Bill bill) {
        if(bill == null) return null;
        return new BillResponseDto(
                bill.getId(),
                bill.getName().getValue(),
                bill.getDescription().getValue(),
                bill.getAmount().getValue(),
                bill.getAmount().getCurrencyCode(),
                bill.getType(),
                bill.getWalletId(),
                bill.getCategoryId(),
                bill.getCreatedAt(),
                bill.getUpdatedAt()
        );
    }

    public BillResponseDto toResponseDto(@Nullable BillProjection billProjection) {
        if(billProjection == null) return null;
        return new BillResponseDto(
                billProjection.getId(),
                billProjection.getName(),
                billProjection.getDescription(),
                billProjection.getAmount(),
                billProjection.getCurrencyCode(),
                billProjection.getType(),
                billProjection.getWalletId(),
                billProjection.getCategoryId(),
                billProjection.getCreatedAt(),
                billProjection.getUpdatedAt()
        );
    }

    public BillListResponseDto toResponseDto(Page<BillProjection> billProjectionList) {
        if(billProjectionList == null) return null;
        return new BillListResponseDto(
               billProjectionList.stream().map(this::toResponseDto).toList(),
               billProjectionList.getTotalElements(),
               billProjectionList.getTotalPages()
        );
    }
}
