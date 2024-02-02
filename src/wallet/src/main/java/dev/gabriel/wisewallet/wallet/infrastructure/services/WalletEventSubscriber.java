package dev.gabriel.wisewallet.wallet.infrastructure.services;

import dev.gabriel.wisewallet.bill.domain.events.BillCreatedEvent;
import dev.gabriel.wisewallet.bill.domain.models.BillType;
import dev.gabriel.wisewallet.core.application.CommandBus;
import dev.gabriel.wisewallet.wallet.application.events.WalletAsyncEventHandler;
import dev.gabriel.wisewallet.wallet.domain.commands.AddAmountCommand;
import dev.gabriel.wisewallet.wallet.domain.commands.SubtractAmountCommand;
import dev.gabriel.wisewallet.wallet.domain.commands.WalletCommand;
import dev.gabriel.wisewallet.wallet.infrastructure.projection.WalletProjectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WalletEventSubscriber implements WalletAsyncEventHandler {
    private final CommandBus<WalletCommand> commandBus;
    private final WalletProjectionRepository walletProjectionRepository;

    @KafkaListener(topics = "BillCreatedEvent")
    @Override
    public void handle(BillCreatedEvent event, Acknowledgment ack) {
        if(event.getType().equals(BillType.EXPENSE)) {
            commandBus.execute(new SubtractAmountCommand(event.getWalletId(), event.getAmount(), event.getCurrencyCode()));
        }else {
            commandBus.execute(new AddAmountCommand(event.getWalletId(), event.getAmount(), event.getCurrencyCode()));
        }
        ack.acknowledge();
    }
}
