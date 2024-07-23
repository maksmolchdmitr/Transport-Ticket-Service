package maks.molch.dmitr.core.service.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.mapper.RouteMapper;
import maks.molch.dmitr.core.mapper.TicketMapper;
import maks.molch.dmitr.core.repo.CarrierRepo;
import maks.molch.dmitr.core.repo.PurchasedTicketsRepo;
import maks.molch.dmitr.core.repo.RouteRepo;
import maks.molch.dmitr.core.repo.TicketRepo;
import maks.molch.dmitr.core.repo.UserRepo;
import maks.molch.dmitr.core.service.TicketService;
import maks.molch.dmitr.core.service.entity.FullTicket;
import maks.molch.dmitr.core.service.entity.Ticket;
import maks.molch.dmitr.core.service.entity.TicketPurchase;
import maks.molch.dmitr.core.service.exception.AlreadyExistException;
import maks.molch.dmitr.core.service.exception.EntityNotFoundException;
import maks.molch.dmitr.core.service.filter.TicketFilter;
import maks.molch.dmitr.core.service.kafka.KafkaAnalyticProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.exception.IntegrityConstraintViolationException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
    private static final Logger logger = LogManager.getLogger(TicketServiceImpl.class);

    private final TicketRepo ticketRepo;
    private final RouteRepo routeRepo;
    private final CarrierRepo carrierRepo;
    private final TicketMapper ticketMapper;
    private final RouteMapper routeMapper;
    private final UserRepo userRepo;
    private final PurchasedTicketsRepo purchasedTicketsRepo;
    private final KafkaAnalyticProducer kafkaAnalyticProducer;

    @Override
    public List<FullTicket> getTicketsPage(int pageNumber, int pageSize, TicketFilter ticketFilter) {
        logger.info("Get tickets page {} of {} with filters {}", pageNumber, pageSize, ticketFilter);
        var filteredTickets = ticketRepo.findAllSortByPrimaryKeyAndFiltered(ticketFilter);
        var fromIndex = Math.min(pageNumber * pageSize, filteredTickets.size());
        var toIndex = Math.min(pageNumber * pageSize + pageSize, filteredTickets.size());
        return ticketMapper.toFullTicketList(filteredTickets.subList(fromIndex, toIndex));
    }

    @Override
    public FullTicket addTicket(Ticket ticket) {
        logger.info("Add ticket {}", ticket);
        try {
            var ticketRecord = ticketMapper.toRecord(ticket);
            var createdTicketRecord = ticketRepo.save(ticketRecord);
            return getFullTicket(createdTicketRecord.getId());
        } catch (IntegrityConstraintViolationException e) {
            throw new AlreadyExistException("Such ticket already exist");
        }
    }

    @CacheEvict(value = "ticket_purchase_cache", key = "#userLogin")
    @Transactional
    @Override
    public TicketPurchase purchase(int ticketId, String userLogin) {
        logger.info("Purchase ticket id:{}", ticketId);
        try {
            userRepo.findById(userLogin)
                    .orElseThrow(() -> new EntityNotFoundException("User with such id not found"));
            var ticketRecord = ticketRepo.findById(ticketId)
                    .orElseThrow(() -> new EntityNotFoundException("Ticket with such id not found"));
            if (ticketRecord.getPurchasedBy() != null) {
                throw new AlreadyExistException("Ticket was already purchased");
            }
            var routeRecord = routeRepo.findById(ticketRecord.getRouteId())
                    .orElseThrow(() -> new EntityNotFoundException("Route with such id not found"));
            var carrierRecord = carrierRepo.findById(routeRecord.getCarrierName())
                    .orElseThrow(() -> new EntityNotFoundException("Carrier with such id not found"));
            var fullRoute = routeMapper.toFullRoute(routeRecord, carrierRecord);
            var fullTicket = ticketMapper.toFullTicket(ticketRecord, fullRoute);
            ticketRepo.setPurchasedById(ticketId, userLogin);
            var purchaseRecord = purchasedTicketsRepo.save(ticketMapper.toPurchaseRecord(ticketId, userLogin));
            var ticketPurchase = ticketMapper.toPurchase(purchaseRecord, fullTicket, userLogin);
            sendAnalyticToKafka(ticketPurchase, fullTicket);
            return ticketPurchase;
        } catch (IntegrityConstraintViolationException e) {
            throw new AlreadyExistException("Such ticket already purchased!");
        }
    }

    private void sendAnalyticToKafka(TicketPurchase ticketPurchase, FullTicket fullTicket) {
        var kafkaTicketPurchase = ticketMapper.toKafkaPurchase(ticketPurchase, fullTicket);
        kafkaAnalyticProducer.sendTicketPurchase(kafkaTicketPurchase);
    }

    @Cacheable(value = "ticket_purchase_cache", key = "#userLogin")
    @Override
    public List<TicketPurchase> getUserTicketPurchases(String userLogin) {
        logger.info("Get user ticket purchase list");
        userRepo.findById(userLogin)
                .orElseThrow(() -> new EntityNotFoundException("User with such id not found"));
        var ticketPurchases = purchasedTicketsRepo.findAllByUserId(userLogin);
        return ticketMapper.toPurchaseList(ticketPurchases);
    }

    @Override
    public TicketPurchase getTicketPurchase(Integer ticketPurchaseId) {
        logger.info("Get ticket purchase by id:{}", ticketPurchaseId);
        var purchase = purchasedTicketsRepo.findById(ticketPurchaseId)
                .orElseThrow(() -> new EntityNotFoundException("Purchase with such id not found"));
        var fullTicket = getFullTicket(purchase.getTicketId());
        return ticketMapper.toPurchase(purchase, fullTicket, purchase.getUserLogin());
    }

    @Override
    public FullTicket getFullTicket(Integer id) {
        logger.info("Get full ticket by id:{}", id);
        var ticketRecord = ticketRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket with such id not found"));
        var routeRecord = routeRepo.findById(ticketRecord.getRouteId())
                .orElseThrow(() -> new EntityNotFoundException("Route with such id not found"));
        var carrierRecord = carrierRepo.findById(routeRecord.getCarrierName())
                .orElseThrow(() -> new EntityNotFoundException("Carrier with such id not found"));
        var fullRoute = routeMapper.toFullRoute(routeRecord, carrierRecord);
        return ticketMapper.toFullTicket(ticketRecord, fullRoute);
    }

    @Override
    public FullTicket updateTicket(int id, Ticket ticket) {
        logger.info("Update ticket with id:{}", id);
        try {
            var ticketRecord = ticketMapper.toRecord(id, ticket);
            ticketRepo.update(ticketRecord);
            return getFullTicket(id);
        } catch (IntegrityConstraintViolationException e) {
            throw new AlreadyExistException("Such ticket already exist!");
        }
    }

    @Override
    public void deleteTicket(Integer id) {
        logger.info("Delete ticket with id:{}", id);
        ticketRepo.delete(id);
    }
}
