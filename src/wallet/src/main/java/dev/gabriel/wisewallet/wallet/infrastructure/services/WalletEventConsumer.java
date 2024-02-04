package dev.gabriel.wisewallet.wallet.infrastructure.services;

import dev.gabriel.wisewallet.bill.domain.events.BillAmountUpdatedEvent;
import dev.gabriel.wisewallet.bill.domain.events.BillCreatedEvent;
import dev.gabriel.wisewallet.bill.domain.events.BillDeletedEvent;
import dev.gabriel.wisewallet.bill.domain.models.Bill;
import dev.gabriel.wisewallet.bill.domain.models.BillType;
import dev.gabriel.wisewallet.bill.domain.repositories.BillRepository;
import dev.gabriel.wisewallet.core.application.CommandBus;
import dev.gabriel.wisewallet.wallet.application.events.WalletAsyncEventHandler;
import dev.gabriel.wisewallet.wallet.domain.commands.AddAmountCommand;
import dev.gabriel.wisewallet.wallet.domain.commands.SubtractAmountCommand;
import dev.gabriel.wisewallet.wallet.domain.commands.WalletCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WalletEventConsumer implements WalletAsyncEventHandler {
    private final CommandBus<WalletCommand> commandBus;
    private final BillRepository billRepository;

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

    @KafkaListener(topics = "BillAmountUpdatedEvent")
    @Override
    public void handle(BillAmountUpdatedEvent event, Acknowledgment ack) {
        Bill bill = billRepository.load(event.getAggregateId(), event.getVersion()-1).orElse(null);

        if(bill != null) {
            if(bill.getType().equals(BillType.EXPENSE)) {
                commandBus.execute(
                        new AddAmountCommand(bill.getWalletId(), bill.getAmount().getValue(), bill.getAmount().getCurrencyCode())
                );
                commandBus.execute(
                        new SubtractAmountCommand(bill.getWalletId(), event.getAmount(), bill.getAmount().getCurrencyCode())
                );
            }else {
                commandBus.execute(
                        new SubtractAmountCommand(bill.getWalletId(), bill.getAmount().getValue(), bill.getAmount().getCurrencyCode())
                );
                commandBus.execute(
                        new AddAmountCommand(bill.getWalletId(), event.getAmount(), bill.getAmount().getCurrencyCode())
                );
            }
        }

        ack.acknowledge();
    }

    @KafkaListener(topics = "BillDeletedEvent")
    @Override
    public void handle(BillDeletedEvent event, Acknowledgment ack) {
        Bill bill = billRepository.load(event.getAggregateId()).orElse(null);

        if(bill != null) {
            if(bill.getType().equals(BillType.EXPENSE)) {
                commandBus.execute(
                        new AddAmountCommand(
                                bill.getWalletId(), bill.getAmount().getValue(), bill.getAmount().getCurrencyCode()
                        )
                );
            }else {
                commandBus.execute(
                        new SubtractAmountCommand(
                                bill.getWalletId(), bill.getAmount().getValue(), bill.getAmount().getCurrencyCode()
                        )
                );
            }
        }

        ack.acknowledge();
    }
}
