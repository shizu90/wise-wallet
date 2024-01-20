package dev.gabriel.wisewallet.category.infrastructure.services;

import dev.gabriel.wisewallet.category.domain.services.CheckUniqueCategoryName;
import dev.gabriel.wisewallet.category.infrastructure.projection.CategoryProjectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CheckUniqueCategoryNameService implements CheckUniqueCategoryName {
    private final CategoryProjectionRepository categoryProjectionRepository;

    @Override
    public boolean exists(String name, UUID userId) {
        return !categoryProjectionRepository.findByUserIdAndName(userId, name).isEmpty();
    }
}
