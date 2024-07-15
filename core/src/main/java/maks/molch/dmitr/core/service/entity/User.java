package maks.molch.dmitr.core.service.entity;

public record User(
        String login,
        String password,
        String fullName,
        Role role
) {
    public enum Role {
        USER,
        ADMIN,
    }
}
