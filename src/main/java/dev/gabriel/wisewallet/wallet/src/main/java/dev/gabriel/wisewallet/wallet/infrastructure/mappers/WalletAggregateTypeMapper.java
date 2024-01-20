package dev.gabriel.wisewallet.wallet.infrastructure.mappers;

import dev.gabriel.wisewallet.core.infrastructure.eventstore.mappers.AggregateTypeMapper;
import dev.gabriel.wisewallet.wallet.domain.models.AggregateType;
import dev.gabriel.wisewallet.wallet.domain.models.Wallet;
import org.springframework.stereotype.Component;

@Component
public class WalletAggregateTypeMapper implements AggregateTypeMapper {
    @Override
    public Class<Wallet> getClassByAggregateType(String aggregateType) {
        return AggregateType.valueOf(aggregateType).getType();
    }
}
