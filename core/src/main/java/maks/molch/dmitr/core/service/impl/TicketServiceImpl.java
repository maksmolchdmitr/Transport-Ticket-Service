package maks.molch.dmitr.core.service.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.mapper.RouteMapper;
import maks.molch.dmitr.core.mapper.TicketMapper;
import maks.molch.dmitr.core.model.FullTicket;
import maks.molch.dmitr.core.model.Ticket;
import maks.molch.dmitr.core.repo.CarrierRepo;
import maks.molch.dmitr.core.repo.RouteRepo;
import maks.molch.dmitr.core.repo.TicketRepo;
import maks.molch.dmitr.core.service.TicketService;
import maks.molch.dmitr.core.service.exception.AlreadyExistException;
import maks.molch.dmitr.core.service.exception.EntityNotFoundException;
import maks.molch.dmitr.core.service.filter.TicketFilter;
import org.jooq.exception.IntegrityConstraintViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepo ticketRepo;
    private final RouteRepo routeRepo;
    private final CarrierRepo carrierRepo;
    private final TicketMapper ticketMapper;
    private final RouteMapper routeMapper;

    @Override
    public List<FullTicket> getTicketsPage(int pageNumber, int pageSize, TicketFilter ticketFilter) {
        var filteredTickets = ticketRepo.findAllSortByPrimaryKeyAndFiltered(ticketFilter);
        var fromIndex = Math.min(pageNumber * pageSize, filteredTickets.size());
        var toIndex = Math.min(pageNumber * pageSize + pageSize, filteredTickets.size());
        return ticketMapper.toTickets(filteredTickets.subList(fromIndex, toIndex));
    }

    @Override
    public FullTicket addTicket(Ticket ticket) {
        try {
            var ticketRecord = ticketMapper.toRecord(ticket);
            var routerRecord = routeRepo.findById(ticket.routeId());
            if (Objects.isNull(routerRecord)) {
                throw new EntityNotFoundException("Router with such id not found");
            }
            var carrierRecord = carrierRepo.findById(routerRecord.getCarrierName());
            if (Objects.isNull(carrierRecord)) {
                throw new EntityNotFoundException("Carrier with such id not found");
            }
            var createdTicket = ticketRepo.save(ticketRecord);
            var fullRoute = routeMapper.toRoute(routerRecord, carrierRecord);
            return ticketMapper.toFullTicket(createdTicket, fullRoute);
        } catch (IntegrityConstraintViolationException e) {
            throw new AlreadyExistException("Such ticket already exist");
        }
    }
}
