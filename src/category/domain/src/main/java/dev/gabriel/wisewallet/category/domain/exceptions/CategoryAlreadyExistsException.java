package dev.gabriel.wisewallet.category.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class CategoryAlreadyExistsException extends DomainException {
    public CategoryAlreadyExistsException(String msg) {
        super(msg);
    }
}
