package dev.gabriel.wisewallet.auth.infrastructure;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.UUID;

@Document(collection = "AuthCredentials")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class AuthCredentials implements UserDetails {
    @Id
    private UUID id;
    private String email;
    private String password;
    private Instant lastLogin;

    public static AuthCredentials create(UUID id, String email, String password, Instant lastLogin) {
        return new AuthCredentials(id, email, password, lastLogin);
    }

    public void login() {
        this.lastLogin = Instant.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}