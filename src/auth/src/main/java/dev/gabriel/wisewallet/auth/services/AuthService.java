package dev.gabriel.wisewallet.auth.services;

import dev.gabriel.wisewallet.auth.dtos.AuthRequestDto;
import dev.gabriel.wisewallet.auth.dtos.AuthResponseDto;
import dev.gabriel.wisewallet.token.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthService {
    private final JwtUtil jwtUtil;
    private final RestTemplate restTemplate;

    public AuthResponseDto login(AuthRequestDto request) {
        Boolean response = restTemplate
                .getForObject("http://localhost:8082/api/users/validate", Boolean.class, request);

        if(response != null && response) {
            String token = jwtUtil.generateToken(request.email());

            return new AuthResponseDto(request.email(), token);
        }else throw new AuthenticationFailedException("User password don't match.");
    }
}
