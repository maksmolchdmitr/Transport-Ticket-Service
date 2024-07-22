package maks.molch.dmitr.analytic.service;

import maks.molch.dmitr.analytic.service.entity.TicketPurchase;

public interface PurchasedTicketHandler {
    void handle(TicketPurchase ticketPurchase);
}
