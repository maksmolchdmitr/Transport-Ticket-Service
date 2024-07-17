package maks.molch.dmitr.core.service;

import maks.molch.dmitr.core.service.entity.FullTicket;
import maks.molch.dmitr.core.service.entity.Ticket;
import maks.molch.dmitr.core.service.entity.TicketPurchase;
import maks.molch.dmitr.core.service.filter.TicketFilter;

import java.util.List;

public interface TicketService {
    List<FullTicket> getTicketsPage(int pageNumber, int pageSize, TicketFilter ticketFilter);

    FullTicket addTicket(Ticket ticket);

    TicketPurchase purchase(int ticketId, String userLogin);

    List<TicketPurchase> getUserTicketPurchases(String userLogin);

    TicketPurchase getTicketPurchase(Integer ticketPurchaseId);

    FullTicket getFullTicket(Integer id);

    FullTicket updateTicket(int id, Ticket ticket);

    void deleteTicket(Integer id);
}
