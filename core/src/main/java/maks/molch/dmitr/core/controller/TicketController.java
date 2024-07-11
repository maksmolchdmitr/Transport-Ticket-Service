package maks.molch.dmitr.core.controller;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.dto.TicketPageDto;
import maks.molch.dmitr.core.mapper.TicketMapper;
import maks.molch.dmitr.core.service.TicketService;
import maks.molch.dmitr.core.service.filter.TicketFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @GetMapping
    public TicketPageDto getTickets(
            @RequestParam(value = "date_and_time_filter", required = false) Timestamp dateAndTimeFilter,
            @RequestParam(value = "departure_filter", required = false) String departureFilter,
            @RequestParam(value = "arrival_filter", required = false) String arrivalFilter,
            @RequestParam(value = "carrier_name_filter", required = false) String carrierNameFilter,
            @RequestParam(value = "page_token", required = false, defaultValue = "") String pageToken,
            @RequestParam(value = "page_size", required = false, defaultValue = "10") Long pageSize
    ) {
        var filter = new TicketFilter(
                Optional.ofNullable(dateAndTimeFilter),
                Optional.ofNullable(departureFilter),
                Optional.ofNullable(arrivalFilter),
                Optional.ofNullable(carrierNameFilter)
        );
        var page = ticketService.getTicketsPage(pageToken, pageSize, filter);
        return ticketMapper.toPageDto(page);
    }
}
