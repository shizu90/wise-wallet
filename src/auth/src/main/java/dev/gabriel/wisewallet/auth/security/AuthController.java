package dev.gabriel.wisewallet.auth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {
    @PostMapping(value = "/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok().body("Logged in");
    }
}
