package dev.gabriel.wisewallet.recurringbill.presentation.controllers;

import dev.gabriel.wisewallet.recurringbill.infrastructure.services.RecurringBillService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping(value = "/api/recurringbills")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "Recurring bill endpoints")
public class RecurringBillController {
    private final RecurringBillService service;
}
