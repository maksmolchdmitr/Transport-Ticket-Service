package maks.molch.dmitr.core.service;

import maks.molch.dmitr.core.model.FullTicket;
import maks.molch.dmitr.core.pagination.Page;
import maks.molch.dmitr.core.service.filter.TicketFilter;

public interface TicketService {
    Page<FullTicket> getTicketsPage(String pageToken, long pageSize, TicketFilter ticketFilter);
}
