package dev.gabriel.wisewallet.user.infrastructure.services;

import dev.gabriel.wisewallet.user.application.events.UserSyncEventHandler;
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

@Transactional
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserSyncEventHandlerImpl implements UserSyncEventHandler {
    private final UserProjectionRepository projectionRepository;

    @Override
    public void handleEvents(Aggregate aggregate) {
        User user = (User) aggregate;
        UserProjection userProjection = UserProjection.create(
                user.getId(),
                user.getName().getValue(),
                user.getEmail().getValue(),
                user.getPassword().getValue(),
                user.getConfiguration(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
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
