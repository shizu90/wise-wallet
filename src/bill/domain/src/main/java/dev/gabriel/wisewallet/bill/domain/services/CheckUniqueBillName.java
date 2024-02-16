package dev.gabriel.wisewallet.bill.domain.services;

import java.util.UUID;

public interface CheckUniqueBillName {
    boolean exists(String name, UUID walletId);
}
