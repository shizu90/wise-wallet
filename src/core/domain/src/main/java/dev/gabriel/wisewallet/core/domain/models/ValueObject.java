package dev.gabriel.wisewallet.core.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public abstract class ValueObject {
    @JsonIgnore
    public abstract List<Object> getAtomicValues();

    private boolean valuesAreEqual(ValueObject valueObject) {
        return getAtomicValues().equals(valueObject.getAtomicValues());
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof ValueObject && valuesAreEqual((ValueObject) object);
    }

    @Override
    public int hashCode() {
        return getAtomicValues().hashCode();
    }
}
