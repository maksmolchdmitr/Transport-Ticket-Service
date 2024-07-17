package maks.molch.dmitr.core.repo.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.jooq.tables.records.PurchasedTicketsTableRecord;
import maks.molch.dmitr.core.repo.PurchasedTicketsRepo;
import maks.molch.dmitr.core.repo.PurchasedTicketsUniqueId;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static maks.molch.dmitr.core.jooq.Tables.PURCHASED_TICKETS_TABLE;

@Repository
@AllArgsConstructor
public class PurchasedTicketsRepoImpl implements PurchasedTicketsRepo {
    private final DSLContext context;

    @Override
    public List<PurchasedTicketsTableRecord> findAll() {
        return context
                .selectFrom(PURCHASED_TICKETS_TABLE)
                .fetch();
    }

    @Override
    public Optional<PurchasedTicketsTableRecord> findById(PurchasedTicketsUniqueId id) {
        return Optional.ofNullable(context
                .selectFrom(PURCHASED_TICKETS_TABLE)
                .where(conditionByUniqueId(id))
                .fetchOne());
    }

    @Override
    public PurchasedTicketsTableRecord save(PurchasedTicketsTableRecord entity) {
        return context
                .insertInto(PURCHASED_TICKETS_TABLE)
                .set(entity)
                .returning()
                .fetchOne();
    }

    @Override
    public PurchasedTicketsTableRecord update(PurchasedTicketsTableRecord entity) {
        var purchasedTicketsRecord = context.newRecord(PURCHASED_TICKETS_TABLE, entity);
        purchasedTicketsRecord.update();
        return purchasedTicketsRecord;
    }

    @Override
    public void delete(PurchasedTicketsUniqueId id) {
        context
                .deleteFrom(PURCHASED_TICKETS_TABLE)
                .where(conditionByUniqueId(id))
                .execute();
    }

    @Override
    public boolean exist(PurchasedTicketsUniqueId id) {
        var count = Objects.requireNonNullElse(context
                .selectCount()
                .from(PURCHASED_TICKETS_TABLE)
                .where(conditionByUniqueId(id))
                .fetchOne(0, Integer.class), 0);
        return count > 0;
    }

    private Condition conditionByUniqueId(PurchasedTicketsUniqueId id) {
        return PURCHASED_TICKETS_TABLE.TICKET_ID.eq(id.ticketId())
                .and(PURCHASED_TICKETS_TABLE.USER_LOGIN.eq(id.userLogin()));
    }
}
