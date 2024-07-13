package maks.molch.dmitr.core.repo.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.jooq.tables.records.CarrierTableRecord;
import maks.molch.dmitr.core.jooq.tables.records.RouteTableRecord;
import maks.molch.dmitr.core.repo.RouteRepo;
import maks.molch.dmitr.core.service.filter.RouteFilter;
import org.apache.commons.lang3.tuple.Pair;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static maks.molch.dmitr.core.jooq.Tables.CARRIER_TABLE;
import static maks.molch.dmitr.core.jooq.Tables.ROUTE_TABLE;

@Repository
@AllArgsConstructor
public class RouteRepoImpl implements RouteRepo {
    private final DSLContext context;

    @Override
    public List<RouteTableRecord> findAll() {
        return context
                .selectFrom(ROUTE_TABLE)
                .fetch();
    }

    @Override
    public RouteTableRecord findById(Integer id) {
        return context
                .selectFrom(ROUTE_TABLE)
                .where(ROUTE_TABLE.ID.eq(id))
                .fetchOne();
    }

    @Override
    public RouteTableRecord save(RouteTableRecord entity) {
        var router = context.newRecord(ROUTE_TABLE, entity);
        router.store();
        return router;
    }

    @Override
    public RouteTableRecord update(RouteTableRecord entity) {
        var router = context.newRecord(ROUTE_TABLE, entity);
        router.update();
        return router;
    }

    @Override
    public void delete(Integer id) {
        context
                .deleteFrom(ROUTE_TABLE)
                .where(ROUTE_TABLE.ID.eq(id))
                .execute();
    }

    @Override
    public boolean exist(Integer id) {
        var count = Objects.requireNonNullElse(context
                .selectCount()
                .from(ROUTE_TABLE)
                .where(ROUTE_TABLE.ID.eq(id))
                .fetchOne(0, Integer.class), 0);
        return count > 0;
    }

    @Override
    public List<Pair<RouteTableRecord, CarrierTableRecord>> findAllSortByPrimaryKeyAndFiltered(RouteFilter routeFilter) {
        return context
                .select(
                        ROUTE_TABLE.ID, ROUTE_TABLE.DEPARTURE, ROUTE_TABLE.ARRIVAL, ROUTE_TABLE.CARRIER_NAME, ROUTE_TABLE.DURATION_IN_MINUTES, ROUTE_TABLE.SEAT_COUNT,
                        CARRIER_TABLE.NAME, CARRIER_TABLE.PHONE
                )
                .from(ROUTE_TABLE)
                .join(CARRIER_TABLE).on(ROUTE_TABLE.CARRIER_NAME.eq(CARRIER_TABLE.NAME))
                .where(toConditions(routeFilter))
                .fetch()
                .map(record -> Pair.of(
                        new RouteTableRecord(record.value1(), record.value2(), record.value3(), record.value4(), record.value5(), record.value6()),
                        new CarrierTableRecord(record.value7(), record.value8())
                ));
    }

    private List<Condition> toConditions(RouteFilter routeFilter) {
        var conditions = new ArrayList<Condition>();
        routeFilter.carrierName().ifPresent(carrierName -> conditions.add(
                CARRIER_TABLE.NAME.likeIgnoreCase('%' + carrierName + '%')
        ));
        routeFilter.arrival().ifPresent(arrival -> conditions.add(
                ROUTE_TABLE.ARRIVAL.likeIgnoreCase('%' + arrival + '%')
        ));
        routeFilter.departure().ifPresent(departure -> conditions.add(
                ROUTE_TABLE.DEPARTURE.likeIgnoreCase('%' + departure + '%')
        ));
        return conditions;
    }
}
