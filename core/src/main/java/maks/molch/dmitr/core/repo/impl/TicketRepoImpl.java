package maks.molch.dmitr.core.repo.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.jooq.tables.records.CarrierTableRecord;
import maks.molch.dmitr.core.jooq.tables.records.RouteTableRecord;
import maks.molch.dmitr.core.jooq.tables.records.TicketTableRecord;
import maks.molch.dmitr.core.repo.TicketPrimaryKey;
import maks.molch.dmitr.core.repo.TicketRepo;
import maks.molch.dmitr.core.service.filter.TicketFilter;
import org.apache.commons.lang3.tuple.Triple;
import org.jetbrains.annotations.NotNull;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static maks.molch.dmitr.core.jooq.Tables.*;

@Repository
@AllArgsConstructor
public class TicketRepoImpl implements TicketRepo {
    private final DSLContext context;

    @Override
    public List<TicketTableRecord> findAll() {
        return context
                .selectFrom(TICKET_TABLE)
                .fetch();
    }

    @Override
    public TicketTableRecord findById(TicketPrimaryKey primaryKey) {
        return context
                .selectFrom(TICKET_TABLE)
                .where(conditionByPrimaryKey(primaryKey))
                .fetchOne();
    }

    @Override
    public TicketTableRecord save(TicketTableRecord entity) {
        var ticket = context.newRecord(TICKET_TABLE, entity);
        ticket.store();
        return ticket;
    }

    @Override
    public TicketTableRecord update(TicketTableRecord entity) {
        var ticket = context.newRecord(TICKET_TABLE, entity);
        ticket.update();
        return ticket;
    }

    @Override
    public void delete(TicketPrimaryKey primaryKey) {
        context
                .deleteFrom(TICKET_TABLE)
                .where(conditionByPrimaryKey(primaryKey))
                .execute();
    }

    @Override
    public boolean exist(TicketPrimaryKey primaryKey) {
        var count = Objects.requireNonNullElse(context
                .selectCount()
                .from(TICKET_TABLE)
                .where(conditionByPrimaryKey(primaryKey))
                .fetchOne(0, Integer.class), 0);
        return count > 0;
    }

    private static @NotNull Condition conditionByPrimaryKey(TicketPrimaryKey primaryKey) {
        return TICKET_TABLE.ROUTE_ID.eq(primaryKey.routeId())
                .and(TICKET_TABLE.DATE_AND_TIME.eq(primaryKey.dateAndTime()))
                .and(TICKET_TABLE.SEAT_NUMBER.eq(primaryKey.seatNumber()));
    }

    @Override
    public List<Triple<TicketTableRecord, RouteTableRecord, CarrierTableRecord>> findAllSortByPrimaryKeyAndFiltered(TicketFilter ticketFilter) {
        return context
                .select(
                        ROUTE_TABLE.ID, TICKET_TABLE.DATE_AND_TIME, TICKET_TABLE.SEAT_NUMBER, TICKET_TABLE.PRICE,
                        ROUTE_TABLE.ID, ROUTE_TABLE.DEPARTURE, ROUTE_TABLE.ARRIVAL, ROUTE_TABLE.CARRIER_NAME, ROUTE_TABLE.DURATION_IN_MINUTES, ROUTE_TABLE.SEAT_COUNT,
                        CARRIER_TABLE.NAME, CARRIER_TABLE.PHONE
                )
                .from(TICKET_TABLE)
                .join(ROUTE_TABLE).on(TICKET_TABLE.ROUTE_ID.eq(ROUTE_TABLE.ID))
                .join(CARRIER_TABLE).on(ROUTE_TABLE.CARRIER_NAME.eq(CARRIER_TABLE.NAME))
                .where(toConditions(ticketFilter))
                .orderBy(
                        TICKET_TABLE.ROUTE_ID.asc(),
                        TICKET_TABLE.DATE_AND_TIME.asc(),
                        TICKET_TABLE.SEAT_NUMBER.asc()
                )
                .fetch()
                .map(record -> Triple.of(
                        new TicketTableRecord(record.value1(), record.value2(), record.value3(), record.value4()),
                        new RouteTableRecord(record.value5(), record.value6(), record.value7(), record.value8(), record.value9(), record.value10()),
                        new CarrierTableRecord(record.value11(), record.value12())
                ));
    }

    private List<Condition> toConditions(TicketFilter ticketFilter) {
        List<Condition> conditions = new ArrayList<>();
        ticketFilter.startDateAndTime().ifPresent(startDateAndTime -> conditions.add(
                TICKET_TABLE.DATE_AND_TIME.greaterOrEqual(startDateAndTime)
        ));
        ticketFilter.endDateAndTime().ifPresent(endDateAndTime -> conditions.add(
                TICKET_TABLE.DATE_AND_TIME.lessOrEqual(endDateAndTime)
        ));
        ticketFilter.departure().ifPresent(departure -> conditions.add(
                ROUTE_TABLE.DEPARTURE.likeIgnoreCase('%' + departure + '%')
        ));
        ticketFilter.arrival().ifPresent(arrival -> conditions.add(
                ROUTE_TABLE.ARRIVAL.likeIgnoreCase('%' + arrival + '%')
        ));
        ticketFilter.carrierName().ifPresent(carrierName -> conditions.add(
                ROUTE_TABLE.CARRIER_NAME.likeIgnoreCase('%' + carrierName + '%')
        ));
        return conditions;
    }
}
