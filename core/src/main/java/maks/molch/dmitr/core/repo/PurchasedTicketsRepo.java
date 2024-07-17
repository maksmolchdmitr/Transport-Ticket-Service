package maks.molch.dmitr.core.repo;

import maks.molch.dmitr.core.jooq.tables.records.PurchasedTicketsTableRecord;

import java.util.List;
import java.util.Optional;

public interface PurchasedTicketsRepo extends JooqRepo<PurchasedTicketsTableRecord, Integer> {
    List<PurchasedTicketsTableRecord> findAllByUserId(String userLogin);

    void delete(PurchasedTicketsUniqueId id);

    boolean exist(PurchasedTicketsUniqueId id);

    Optional<PurchasedTicketsTableRecord> findById(PurchasedTicketsUniqueId id);
}
