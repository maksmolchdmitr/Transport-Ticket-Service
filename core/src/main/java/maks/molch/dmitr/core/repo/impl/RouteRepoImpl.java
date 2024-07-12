package maks.molch.dmitr.core.repo.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.jooq.Tables;
import maks.molch.dmitr.core.jooq.tables.records.RouteTableRecord;
import maks.molch.dmitr.core.repo.RouteRepo;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class RouteRepoImpl implements RouteRepo {
    private final DSLContext context;

    @Override
    public List<RouteTableRecord> findAll() {
        return context
                .selectFrom(Tables.ROUTE_TABLE)
                .fetch();
    }

    @Override
    public RouteTableRecord findById(Integer id) {
        return context
                .selectFrom(Tables.ROUTE_TABLE)
                .where(Tables.ROUTE_TABLE.ID.eq(id))
                .fetchOne();
    }

    @Override
    public RouteTableRecord save(RouteTableRecord entity) {
        var router = context.newRecord(Tables.ROUTE_TABLE, entity);
        router.store();
        return router;
    }

    @Override
    public RouteTableRecord update(RouteTableRecord entity) {
        var router = context.newRecord(Tables.ROUTE_TABLE, entity);
        router.update();
        return router;
    }

    @Override
    public void delete(Integer id) {
        context
                .deleteFrom(Tables.ROUTE_TABLE)
                .where(Tables.ROUTE_TABLE.ID.eq(id))
                .execute();
    }

    @Override
    public boolean exist(Integer id) {
        var count = Objects.requireNonNullElse(context
                .selectCount()
                .from(Tables.ROUTE_TABLE)
                .where(Tables.ROUTE_TABLE.ID.eq(id))
                .fetchOne(0, Integer.class), 0);
        return count > 0;
    }
}
