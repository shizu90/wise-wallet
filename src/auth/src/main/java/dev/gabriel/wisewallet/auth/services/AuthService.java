package dev.gabriel.wisewallet.auth.services;

import dev.gabriel.wisewallet.auth.dtos.AuthRequestDto;
import dev.gabriel.wisewallet.auth.dtos.AuthResponseDto;
import dev.gabriel.wisewallet.token.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthService {
    private final JwtUtil jwtUtil;

    public AuthResponseDto login(AuthRequestDto request) {
        return null;
    }
}
