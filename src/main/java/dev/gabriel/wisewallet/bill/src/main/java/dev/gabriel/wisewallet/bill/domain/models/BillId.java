package dev.gabriel.wisewallet.bill.domain.models;

import dev.gabriel.wisewallet.core.domain.models.Identity;

import java.util.UUID;

public class BillId extends Identity {
    private BillId(UUID value) {
        super(value);
    }

    public static BillId create(UUID value) {
        return new BillId(value);
    }
}
