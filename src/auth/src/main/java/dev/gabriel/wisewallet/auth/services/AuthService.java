package dev.gabriel.wisewallet.auth.services;

import dev.gabriel.wisewallet.auth.dtos.AuthRequestDto;
import dev.gabriel.wisewallet.auth.dtos.AuthResponseDto;
import dev.gabriel.wisewallet.token.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthService {
    private final JwtUtil jwtUtil;
    private final RestTemplate restTemplate;

    public record UserResponse(
           UUID id,
           String name,
           String email
    ) {};

    public AuthResponseDto login(AuthRequestDto request) {
        Boolean response = restTemplate
                .getForObject("http://localhost:8082/api/users/validate", Boolean.class, request);

        if(response) {
            String token = jwtUtil.generateToken(request.email());

            return new AuthResponseDto(request.email(), token);
        }else throw new AuthenticationFailedException("User password don't match.");
    }
}
