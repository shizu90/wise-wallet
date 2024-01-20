package dev.gabriel.wisewallet.category.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class CategoryNotFoundException extends DomainException {
    public CategoryNotFoundException(String msg) {
        super(msg);
    }
}
