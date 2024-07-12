package maks.molch.dmitr.core.service.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.mapper.RouteMapper;
import maks.molch.dmitr.core.mapper.TicketMapper;
import maks.molch.dmitr.core.model.Carrier;
import maks.molch.dmitr.core.model.FullTicket;
import maks.molch.dmitr.core.model.FullRoute;
import maks.molch.dmitr.core.model.Ticket;
import maks.molch.dmitr.core.pagination.Page;
import maks.molch.dmitr.core.repo.CarrierRepo;
import maks.molch.dmitr.core.repo.RouteRepo;
import maks.molch.dmitr.core.repo.TicketRepo;
import maks.molch.dmitr.core.service.TicketService;
import maks.molch.dmitr.core.service.exception.AlreadyExistException;
import maks.molch.dmitr.core.service.exception.EntityNotFoundException;
import maks.molch.dmitr.core.service.filter.TicketFilter;
import org.jooq.exception.IntegrityConstraintViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    public Page<FullTicket> getTicketsPage(String pageToken, long pageSize, TicketFilter ticketFilter) {
        //TODO
        return new Page<>(
                "",
                List.of(
                        new FullTicket(
                                new FullRoute(
                                        0,
                                        "Moscow",
                                        "Nizhniy Novgorod",
                                        new Carrier(
                                                "RZD",
                                                "+79506138215"
                                        ),
                                        240,
                                        210
                                ),
                                Timestamp.valueOf(LocalDateTime.now()),
                                1,
                                BigDecimal.valueOf(1982.20d)
                        )
                )
        );
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
