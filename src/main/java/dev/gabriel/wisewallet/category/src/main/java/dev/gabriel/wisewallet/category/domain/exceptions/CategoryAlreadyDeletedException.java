package dev.gabriel.wisewallet.category.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class CategoryAlreadyDeletedException extends DomainException {
    public CategoryAlreadyDeletedException(String msg) {
        super(msg);
    }
}
