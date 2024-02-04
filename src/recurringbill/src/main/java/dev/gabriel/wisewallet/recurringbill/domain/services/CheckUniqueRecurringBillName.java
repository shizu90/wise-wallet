package dev.gabriel.wisewallet.recurringbill.domain.services;

import java.util.UUID;

public interface CheckUniqueRecurringBillName {
    boolean exists(String name, UUID walletId);
}
