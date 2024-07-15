package maks.molch.dmitr.core.service;

import maks.molch.dmitr.core.service.entity.FullTicket;
import maks.molch.dmitr.core.service.entity.Ticket;
import maks.molch.dmitr.core.service.filter.TicketFilter;

import java.util.List;

public interface TicketService {
    List<FullTicket> getTicketsPage(int pageNumber, int pageSize, TicketFilter ticketFilter);

    FullTicket addTicket(Ticket ticket);
}
