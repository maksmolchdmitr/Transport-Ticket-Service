package maks.molch.dmitr.core.service.impl;

import maks.molch.dmitr.core.model.Carrier;
import maks.molch.dmitr.core.model.FullTicket;
import maks.molch.dmitr.core.model.Route;
import maks.molch.dmitr.core.pagination.Page;
import maks.molch.dmitr.core.service.TicketService;
import maks.molch.dmitr.core.service.filter.TicketFilter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Override
    public Page<FullTicket> getTicketsPage(String pageToken, long pageSize, TicketFilter ticketFilter) {
        //TODO
        return new Page<>(
                "",
                List.of(
                        new FullTicket(
                                new Route(
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
}
