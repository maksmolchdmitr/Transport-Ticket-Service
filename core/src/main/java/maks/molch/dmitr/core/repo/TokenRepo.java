package maks.molch.dmitr.core.repo;

import maks.molch.dmitr.core.jooq.tables.records.TokenTableRecord;

public interface TokenRepo extends JooqRepo<TokenTableRecord, Integer> {
    void setAllRevokedByUserIdWithoutRefreshToken(String userId);

    void setRevoked(String token);

    boolean isAliveRefreshToken(String token);

    boolean isAliveAccessToken(String token);
}
