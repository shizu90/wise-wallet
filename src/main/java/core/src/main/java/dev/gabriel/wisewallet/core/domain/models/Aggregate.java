package dev.gabriel.wisewallet.core.domain.models;

import dev.gabriel.wisewallet.core.domain.commands.Command;
import dev.gabriel.wisewallet.core.domain.events.DomainEvent;
import lombok.Getter;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public abstract class Aggregate extends Entity {
    protected List<DomainEvent> changes;
    protected Long version;
    protected Long baseVersion;

    protected Aggregate(@NonNull Identity id, Long version) {
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
        invoke("apply", event);
    }

    public void process(Command command) {
        invoke("process", command);
    }

    @SneakyThrows(InvocationTargetException.class)
    public void invoke(String methodName, Object object) {
        try {
            for (Method method : this.getClass().getMethods()) {
                if (method.getName().equals(methodName) && method.getParameterTypes()[0].equals(object.getClass())) {
                    if (Modifier.isPrivate(method.getModifiers())) method.setAccessible(true);
                    method.invoke(this, object);
                }
            }
        }catch(IllegalAccessException e) {
            throw new UnsupportedOperationException("Aggregate %s doesn't supports %s(%s).".formatted(
                    this.getClass().getSimpleName(), methodName, object.getClass().getSimpleName()
            ));
        }
    }

    protected Long getNextVersion() {
        return version + 1;
    }
}
