package maks.molch.dmitr.core.service.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

public record User(
        String login,
        String password,
        String fullName,
        Role role
) {
    public User(
            String login,
            String password,
            String fullName,
            Role role,
            PasswordEncoder passwordEncoder
    ) {
        this(login, passwordEncoder.encode(password), fullName, role);
    }

    public enum Role {
        USER,
        ADMIN,
    }
}
