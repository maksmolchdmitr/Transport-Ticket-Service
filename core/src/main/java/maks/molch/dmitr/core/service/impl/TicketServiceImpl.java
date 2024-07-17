package maks.molch.dmitr.core.service.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.mapper.RouteMapper;
import maks.molch.dmitr.core.mapper.TicketMapper;
import maks.molch.dmitr.core.repo.*;
import maks.molch.dmitr.core.service.TicketService;
import maks.molch.dmitr.core.service.entity.FullTicket;
import maks.molch.dmitr.core.service.entity.Ticket;
import maks.molch.dmitr.core.service.entity.TicketPurchase;
import maks.molch.dmitr.core.service.exception.AlreadyExistException;
import maks.molch.dmitr.core.service.exception.EntityNotFoundException;
import maks.molch.dmitr.core.service.filter.TicketFilter;
import org.jooq.exception.IntegrityConstraintViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepo ticketRepo;
    private final RouteRepo routeRepo;
    private final CarrierRepo carrierRepo;
    private final TicketMapper ticketMapper;
    private final RouteMapper routeMapper;
    private final UserRepo userRepo;
    private final PurchasedTicketsRepo purchasedTicketsRepo;

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
            var routerRecord = routeRepo.findById(ticket.routeId())
                    .orElseThrow(() -> new EntityNotFoundException("Router with such id not found"));
            var carrierRecord = carrierRepo.findById(routerRecord.getCarrierName())
                    .orElseThrow(() -> new EntityNotFoundException("Carrier with such id not found"));
            var createdTicket = ticketRepo.save(ticketRecord);
            var fullRoute = routeMapper.toRoute(routerRecord, carrierRecord);
            return ticketMapper.toFullTicket(createdTicket, fullRoute);
        } catch (IntegrityConstraintViolationException e) {
            throw new AlreadyExistException("Such ticket already exist");
        }
    }

    @Override
    public TicketPurchase purchase(int ticketId, String userLogin) {
        try {
            var ticketRecord = ticketRepo.findById(ticketId)
                    .orElseThrow(() -> new EntityNotFoundException("Ticket with such id not found"));
            userRepo.findById(userLogin)
                    .orElseThrow(() -> new EntityNotFoundException("User with such id not found"));
            ticketRepo.setPurchasedById(ticketId, userLogin);
            var routeRecord = routeRepo.findById(ticketRecord.getRouteId())
                    .orElseThrow(() -> new EntityNotFoundException("Route with such id not found"));
            var carrierRecord = carrierRepo.findById(routeRecord.getCarrierName())
                    .orElseThrow(() -> new EntityNotFoundException("Carrier with such id not found"));
            var purchaseRecord = purchasedTicketsRepo.save(ticketMapper.toPurchaseRecord(ticketId, userLogin));
            var fullRoute = routeMapper.toRoute(routeRecord, carrierRecord);
            var fullTicket = ticketMapper.toFullTicket(ticketRecord, fullRoute);
            return ticketMapper.toPurchase(purchaseRecord, fullTicket, userLogin);
        } catch (IntegrityConstraintViolationException e) {
            throw new AlreadyExistException("Such ticket already purchased!");
        }
    }

    @Override
    public List<TicketPurchase> getUserTicketPurchases(String userLogin) {
        userRepo.findById(userLogin)
                .orElseThrow(() -> new EntityNotFoundException("User with such id not found"));
        var ticketPurchases = purchasedTicketsRepo.findAllByUserId(userLogin);
        return ticketMapper.toPurchase(ticketPurchases);
    }

    @Override
    public TicketPurchase getTicketPurchase(Integer ticketPurchaseId) {
        var purchase = purchasedTicketsRepo.findById(ticketPurchaseId)
                .orElseThrow(() -> new EntityNotFoundException("Purchase with such id not found"));
        var tickerRecord = ticketRepo.findById(purchase.getTicketId())
                .orElseThrow(() -> new EntityNotFoundException("Ticket with such id not found"));
        var routeRecord = routeRepo.findById(tickerRecord.getRouteId())
                .orElseThrow(() -> new EntityNotFoundException("Route with such id not found"));
        var carrierRecord = carrierRepo.findById(routeRecord.getCarrierName())
                .orElseThrow(() -> new EntityNotFoundException("Carrier with such id not found"));
        var fullRout = routeMapper.toRoute(routeRecord, carrierRecord);
        var fullTicket = ticketMapper.toFullTicket(tickerRecord, fullRout);
        return ticketMapper.toPurchase(purchase, fullTicket, purchase.getUserLogin());
    }
}
