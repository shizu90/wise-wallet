package dev.gabriel.wisewallet.user.presentation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.gabriel.wisewallet.user.domain.models.UserConfiguration;

import java.util.UUID;

public record UserResponseDto(
        @JsonProperty("userId") UUID id,
        @JsonProperty("username") String name,
        @JsonProperty("email") String email,
        @JsonProperty("configuration") UserConfiguration configuration
) {}
