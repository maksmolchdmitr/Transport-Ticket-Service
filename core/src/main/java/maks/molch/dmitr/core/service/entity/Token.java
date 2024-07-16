package maks.molch.dmitr.core.service.entity;

public record Token(
        String token,
        String userLogin,
        Type type
) {
    public enum Type {
        ACCESS_TOKEN,
        REFRESH_TOKEN,
    }
}
