package maks.molch.dmitr.core.repo.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.jooq.Tables;
import maks.molch.dmitr.core.jooq.tables.records.UserTableRecord;
import maks.molch.dmitr.core.repo.UserRepo;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class UserRepoImpl implements UserRepo {
    private final DSLContext context;

    @Override
    public List<UserTableRecord> findAll() {
        return context
                .selectFrom(Tables.USER_TABLE)
                .fetch();
    }

    @Override
    public UserTableRecord findById(String id) {
        return context
                .selectFrom(Tables.USER_TABLE)
                .where(Tables.USER_TABLE.LOGIN.eq(id))
                .fetchOne();
    }

    @Override
    public UserTableRecord save(UserTableRecord entity) {
        UserTableRecord user = context.newRecord(Tables.USER_TABLE, entity);
        user.store();
        return user;
    }

    @Override
    public UserTableRecord update(UserTableRecord entity) {
        UserTableRecord user = context.newRecord(Tables.USER_TABLE, entity);
        user.update();
        return user;
    }

    @Override
    public void delete(String id) {
        context
                .deleteFrom(Tables.USER_TABLE)
                .where(Tables.USER_TABLE.LOGIN.eq(id))
                .execute();
    }

    @Override
    public boolean exist(String id) {
        var count = Objects.requireNonNullElse(context
                .selectCount()
                .from(Tables.USER_TABLE)
                .where(Tables.USER_TABLE.LOGIN.eq(id))
                .fetchOne(0, Integer.class), 0);
        return count > 0;
    }
}
