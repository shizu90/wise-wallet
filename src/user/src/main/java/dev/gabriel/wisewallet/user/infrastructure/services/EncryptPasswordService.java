package dev.gabriel.wisewallet.user.infrastructure.services;

import dev.gabriel.wisewallet.user.domain.services.EncryptPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EncryptPasswordService implements EncryptPassword {
    @Override
    public String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean validate(String hashPassword, String password) {
        return BCrypt.checkpw(hashPassword, password);
    }
}
