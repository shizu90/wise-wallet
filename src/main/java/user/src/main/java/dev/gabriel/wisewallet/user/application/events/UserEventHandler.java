package dev.gabriel.wisewallet.user.application.events;

import dev.gabriel.wisewallet.core.application.EventHandler;
import dev.gabriel.wisewallet.core.domain.events.DomainEvent;
import dev.gabriel.wisewallet.core.domain.events.DomainEventWithId;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.user.domain.models.AggregateType;
import dev.gabriel.wisewallet.user.domain.models.User;
import dev.gabriel.wisewallet.user.infrastructure.projection.UserProjection;
import dev.gabriel.wisewallet.user.infrastructure.projection.UserProjectionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserEventHandler implements EventHandler {
    private final UserProjectionRepository projectionRepository;

    @Override
    public void handleEvents(List<DomainEventWithId<DomainEvent>> events, Aggregate aggregate) {
        User user = (User) aggregate;
        UserProjection userProjection = UserProjection.create(
                user.getId().getValue(),
                user.getName().getValue(),
                user.getEmail().getValue(),
                user.getPassword().getValue(),
                user.getConfiguration()
        );
        projectionRepository.save(userProjection);
    }

    @Override
    @NonNull
    public String getAggregateType() {
        return AggregateType.USER.toString();
    }
}
