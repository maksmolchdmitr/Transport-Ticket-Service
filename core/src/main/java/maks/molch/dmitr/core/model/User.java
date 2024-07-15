package maks.molch.dmitr.core.model;

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
