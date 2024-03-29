package dev.gabriel.wisewallet.wallet.domain.events;

import dev.gabriel.wisewallet.wallet.domain.models.WalletType;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class WalletCreatedEvent extends WalletEvent {
    private final String name;
    private final String description;
    private final BigDecimal balance;
    private final String currencyCode;
    private final WalletType type;
    private final UUID userId;

    public WalletCreatedEvent(UUID id,
                              Long version,
                              String name,
                              String description,
                              BigDecimal balance,
                              String currencyCode,
                              WalletType type,
                              UUID userId
    ) {
        super(id, version);
        this.name = name;
        this.description = description;
        this.balance = balance;
        this.currencyCode = currencyCode;
        this.type = type;
        this.userId = userId;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.WALLET_CREATED.toString();
    }
}
