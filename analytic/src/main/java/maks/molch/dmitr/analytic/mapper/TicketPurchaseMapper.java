package maks.molch.dmitr.analytic.mapper;

import maks.molch.dmitr.analytic.jooq.tables.records.PurchasedTicketsTableRecord;
import maks.molch.dmitr.analytic.service.entity.TicketPurchase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketPurchaseMapper {
    PurchasedTicketsTableRecord toRecord(TicketPurchase ticketPurchase);
}
