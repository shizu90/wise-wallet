package dev.gabriel.wisewallet.bill.infrastructure.services;

import dev.gabriel.wisewallet.bill.application.events.BillAsyncEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BillEventSubscriber implements BillAsyncEventHandler {

}