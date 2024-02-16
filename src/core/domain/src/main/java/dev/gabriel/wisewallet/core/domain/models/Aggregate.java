package dev.gabriel.wisewallet.core.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.gabriel.wisewallet.core.domain.events.DomainEvent;
import lombok.Getter;
import lombok.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public abstract class Aggregate extends Entity {
    @JsonIgnore
    protected List<DomainEvent> changes;
    protected Long version;
    @JsonIgnore
    protected Long baseVersion;

    protected Aggregate(@NonNull UUID id, Long version) {
        super(id);
        this.baseVersion = this.version = version;
        this.changes = new ArrayList<>();
    }

    public abstract String getAggregateType();

    public void loadFromHistory(List<DomainEvent> eventStream) {
        if(!changes.isEmpty())
            throw new IllegalStateException("Aggregate has non-empty new events.");

        eventStream.forEach(event -> {
            if(event.getVersion() <= version)
                throw new IllegalStateException("Event version %s <= aggregate base version %s.".formatted(
                        event.getVersion(), getNextVersion()
                ));
            apply(event);
            baseVersion = version = event.getVersion();
        });
    }

    protected void applyChange(DomainEvent event) {
        if(!event.getVersion().equals(getNextVersion()))
            throw new IllegalStateException("Event version %s doesn't match expected version %s.".formatted(
                    event.getVersion(), getNextVersion()
            ));

        apply(event);
        changes.add(event);
        version = event.getVersion();
    }

    private void apply(DomainEvent event) {
        try {
            Method method = this.getClass().getDeclaredMethod("apply", event.getClass());
            if(Modifier.isPrivate(method.getModifiers())) method.setAccessible(true);
            method.invoke(this, event);
        }catch(IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new UnsupportedOperationException("Aggregate %s doesn't supports apply(%s).".formatted(
                    this.getClass().getSimpleName(), event.getClass().getSimpleName()
            ));
        }
    }

    protected Long getNextVersion() {
        return version + 1;
    }
}
