package maks.molch.dmitr.analytic.repo.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.analytic.jooq.Tables;
import maks.molch.dmitr.analytic.jooq.tables.records.PurchasedTicketsTableRecord;
import maks.molch.dmitr.analytic.repo.TicketPurchaseRepo;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class TicketPurchaseRepoImpl implements TicketPurchaseRepo {
    private final DSLContext context;

    @Override
    public PurchasedTicketsTableRecord save(PurchasedTicketsTableRecord purchasedTicketsTableRecord) {
        var record = context.newRecord(Tables.PURCHASED_TICKETS_TABLE, purchasedTicketsTableRecord);
        record.store();
        return record;
    }
}
