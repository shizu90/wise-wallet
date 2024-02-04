package dev.gabriel.wisewallet.bill.infrastructure.services;

import dev.gabriel.wisewallet.bill.application.events.BillAsyncEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BillEventConsumer implements BillAsyncEventHandler {

}