package dev.gabriel.wisewallet.budget.infrastructure.services;

import dev.gabriel.wisewallet.budget.application.events.BudgetAsyncEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BudgetEventConsumer implements BudgetAsyncEventHandler {

}
