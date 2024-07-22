package maks.molch.dmitr.analytic.service.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.analytic.mapper.TicketPurchaseMapper;
import maks.molch.dmitr.analytic.repo.TicketPurchaseRepo;
import maks.molch.dmitr.analytic.service.PurchasedTicketHandler;
import maks.molch.dmitr.analytic.service.entity.TicketPurchase;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PurchasedTicketHandlerImpl implements PurchasedTicketHandler {
    private final TicketPurchaseRepo ticketPurchaseRepo;
    private final TicketPurchaseMapper ticketPurchaseMapper;

    @Override
    public void handle(TicketPurchase ticketPurchase) {
        var record = ticketPurchaseMapper.toRecord(ticketPurchase);
        ticketPurchaseRepo.save(record);
    }
}
