package dev.gabriel.wisewallet.user.infrastructure.services;

import dev.gabriel.wisewallet.user.domain.services.EncryptPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EncryptPasswordService implements EncryptPassword {
    private final BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public String encrypt(String password) {
        return bcryptPasswordEncoder.encode(password);
    }

    @Override
    public boolean validate(String hashPassword, String password) {
        return bcryptPasswordEncoder.matches(hashPassword, password);
    }
}
