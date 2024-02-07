package dev.gabriel.wisewallet.user.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class UserDefaultCurrencyCodeChangedEvent extends UserEvent {
    private final String currencyCode;

    public UserDefaultCurrencyCodeChangedEvent(UUID id, Long version, String currencyCode) {
        super(id, version);
        this.currencyCode = currencyCode;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.USER_DEFAULT_CURRENCY_CODE_CHANGED.toString();
    }
}
