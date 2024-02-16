package dev.gabriel.wisewallet.bill.domain.commands;

import dev.gabriel.wisewallet.bill.domain.models.BillType;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class CreateBillCommand extends BillCommand {
    private final String name;
    private final String description;
    private final BigDecimal amount;
    private final String currencyCode;
    private final BillType type;
    private final UUID walletId;
    private final UUID categoryId;

    public CreateBillCommand(UUID id,
                             String name,
                             String description,
                             BigDecimal amount,
                             String currencyCode,
                             BillType type,
                             UUID walletId,
                             UUID categoryId
    ) {
        super(id);
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.type = type;
        this.walletId = walletId;
        this.categoryId = categoryId;
    }
}
