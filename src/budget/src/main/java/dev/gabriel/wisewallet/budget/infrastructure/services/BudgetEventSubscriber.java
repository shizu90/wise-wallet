package dev.gabriel.wisewallet.budget.infrastructure.services;

import dev.gabriel.wisewallet.budget.application.events.BudgetAsyncEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BudgetEventSubscriber implements BudgetAsyncEventHandler {

}
