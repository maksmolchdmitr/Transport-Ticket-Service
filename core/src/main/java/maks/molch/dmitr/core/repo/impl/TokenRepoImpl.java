package maks.molch.dmitr.core.repo.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.jooq.tables.records.TokenTableRecord;
import maks.molch.dmitr.core.repo.TokenRepo;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static maks.molch.dmitr.core.jooq.Tables.TOKEN_TABLE;

@Repository
@AllArgsConstructor
public class TokenRepoImpl implements TokenRepo {
    private static final String ACCESS_TOKEN_TYPE = "ACCESS_TOKEN";
    private static final String REFRESH_TOKEN_TYPE = "REFRESH_TOKEN";

    private final DSLContext context;

    @Override
    public void setAllRevokedByUserIdWithoutRefreshToken(String userId) {
        context
                .update(TOKEN_TABLE)
                .set(TOKEN_TABLE.REVOKED, true)
                .where(TOKEN_TABLE.USER_LOGIN.eq(userId)
                        .and(TOKEN_TABLE.TYPE.notEqual(REFRESH_TOKEN_TYPE)))
                .execute();
    }

    @Override
    public void setRevoked(String token) {
        context
                .update(TOKEN_TABLE)
                .set(TOKEN_TABLE.REVOKED, true)
                .where(TOKEN_TABLE.TOKEN.eq(token))
                .execute();
    }

    @Override
    public boolean isAlive(String token) {
        Optional<TokenTableRecord> tokenTableRecord = context
                .selectFrom(TOKEN_TABLE)
                .where(TOKEN_TABLE.TOKEN.eq(token))
                .fetchOptional();
        return tokenTableRecord
                .map(tokenRecord -> FALSE.equals(tokenRecord.getRevoked()))
                .orElse(false);
    }

    @Override
    public List<TokenTableRecord> findAll() {
        return context
                .selectFrom(TOKEN_TABLE)
                .fetch();
    }

    @Override
    public Optional<TokenTableRecord> findById(Integer id) {
        return context
                .selectFrom(TOKEN_TABLE)
                .where(TOKEN_TABLE.ID.eq(id))
                .fetchOptional();
    }

    @Override
    public TokenTableRecord save(TokenTableRecord entity) {
        var tokenRecord = context.newRecord(TOKEN_TABLE, entity);
        tokenRecord.store();
        return tokenRecord;
    }

    @Override
    public TokenTableRecord update(TokenTableRecord entity) {
        var tokenRecord = context.newRecord(TOKEN_TABLE, entity);
        tokenRecord.update();
        return tokenRecord;
    }

    @Override
    public void delete(Integer id) {
        context
                .delete(TOKEN_TABLE)
                .where(TOKEN_TABLE.ID.eq(id))
                .execute();
    }

    @Override
    public boolean exist(Integer id) {
        var count = Objects.requireNonNullElse(context
                .selectCount()
                .from(TOKEN_TABLE)
                .where(TOKEN_TABLE.ID.eq(id))
                .fetchOne(0, Integer.class), 0);
        return count > 0;
    }
}
