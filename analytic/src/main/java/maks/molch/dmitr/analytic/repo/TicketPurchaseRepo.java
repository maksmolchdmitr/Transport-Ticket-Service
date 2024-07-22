package maks.molch.dmitr.analytic.repo;

import maks.molch.dmitr.analytic.jooq.tables.records.PurchasedTicketsTableRecord;

public interface TicketPurchaseRepo {
    PurchasedTicketsTableRecord save(PurchasedTicketsTableRecord purchasedTicketsTableRecord);
}
