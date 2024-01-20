package dev.gabriel.wisewallet.category.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class CategoryValidationException extends DomainException {
    public CategoryValidationException(String msg) {
        super(msg);
    }
}
