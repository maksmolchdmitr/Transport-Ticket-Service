package maks.molch.dmitr.core.repo.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.jooq.Tables;
import maks.molch.dmitr.core.jooq.tables.records.TicketTableRecord;
import maks.molch.dmitr.core.repo.TicketPrimaryKey;
import maks.molch.dmitr.core.repo.TicketRepo;
import org.jetbrains.annotations.NotNull;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class TicketRepoImpl implements TicketRepo {
    private final DSLContext context;

    @Override
    public List<TicketTableRecord> findAll() {
        return context
                .selectFrom(Tables.TICKET_TABLE)
                .fetch();
    }

    @Override
    public TicketTableRecord findById(TicketPrimaryKey primaryKey) {
        return context
                .selectFrom(Tables.TICKET_TABLE)
                .where(conditionByPrimaryKey(primaryKey))
                .fetchOne();
    }

    @Override
    public TicketTableRecord save(TicketTableRecord entity) {
        var ticket = context.newRecord(Tables.TICKET_TABLE, entity);
        ticket.store();
        return ticket;
    }

    @Override
    public TicketTableRecord update(TicketTableRecord entity) {
        var ticket = context.newRecord(Tables.TICKET_TABLE, entity);
        ticket.update();
        return ticket;
    }

    @Override
    public void delete(TicketPrimaryKey primaryKey) {
        context
                .deleteFrom(Tables.TICKET_TABLE)
                .where(conditionByPrimaryKey(primaryKey))
                .execute();
    }

    @Override
    public boolean exist(TicketPrimaryKey primaryKey) {
        var count = Objects.requireNonNullElse(context
                .selectCount()
                .from(Tables.TICKET_TABLE)
                .where(conditionByPrimaryKey(primaryKey))
                .fetchOne(0, Integer.class), 0);
        return count > 0;
    }

    private static @NotNull Condition conditionByPrimaryKey(TicketPrimaryKey primaryKey) {
        return Tables.TICKET_TABLE.ROUTE_ID.eq(primaryKey.routeId())
                .and(Tables.TICKET_TABLE.DATE_AND_TIME.eq(primaryKey.dateAndTime().toLocalDateTime()))
                .and(Tables.TICKET_TABLE.SEAT_NUMBER.eq(primaryKey.seatNumber()));
    }
}
