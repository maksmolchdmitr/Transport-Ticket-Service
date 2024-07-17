package maks.molch.dmitr.core.repo;

import maks.molch.dmitr.core.jooq.tables.records.CarrierTableRecord;
import maks.molch.dmitr.core.jooq.tables.records.RouteTableRecord;
import maks.molch.dmitr.core.jooq.tables.records.TicketTableRecord;
import maks.molch.dmitr.core.service.filter.TicketFilter;
import org.apache.commons.lang3.tuple.Triple;

import java.util.List;
import java.util.Optional;

public interface TicketRepo extends JooqRepo<TicketTableRecord, Integer> {
    List<Triple<TicketTableRecord, RouteTableRecord, CarrierTableRecord>> findAllSortByPrimaryKeyAndFiltered(TicketFilter ticketFilter);

    Optional<TicketTableRecord> findById(TicketUniqueId primaryKey);

    void delete(TicketUniqueId primaryKey);

    boolean exist(TicketUniqueId primaryKey);
}
