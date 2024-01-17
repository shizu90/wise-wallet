package dev.gabriel.wisewallet.user.infrastructure.services;

import dev.gabriel.wisewallet.user.domain.services.CheckUniqueEmail;
import dev.gabriel.wisewallet.user.infrastructure.projection.UserProjectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CheckUniqueEmailService implements CheckUniqueEmail {
    private final UserProjectionRepository userProjectionRepository;

    @Override
    public long exists(String email) {
        return userProjectionRepository.findByEmail(email).size();
    }
}
