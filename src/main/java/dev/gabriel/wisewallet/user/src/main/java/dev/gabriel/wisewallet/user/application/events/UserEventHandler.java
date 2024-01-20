package dev.gabriel.wisewallet.user.application.events;

import dev.gabriel.wisewallet.user.infrastructure.projection.UserProjection;
import dev.gabriel.wisewallet.user.infrastructure.projection.UserProjectionRepository;
import dev.gabriel.wisewallet.core.application.SyncEventHandler;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.user.domain.models.AggregateType;
import dev.gabriel.wisewallet.user.domain.models.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserEventHandler implements SyncEventHandler {
    private final UserProjectionRepository projectionRepository;

    @Override
    public void handleEvents(Aggregate aggregate) {
        User user = (User) aggregate;
        UserProjection userProjection = UserProjection.create(
                user.getId().getValue(),
                user.getName().getValue(),
                user.getEmail().getValue(),
                user.getPassword().getValue(),
                user.getConfiguration(),
                user.getIsDeleted()
        );
        projectionRepository.save(userProjection);
    }

    @Override
    @NonNull
    public String getAggregateType() {
        return AggregateType.USER.toString();
    }
}
