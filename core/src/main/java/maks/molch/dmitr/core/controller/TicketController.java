package maks.molch.dmitr.core.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.dto.request.TicketCreateRequestDto;
import maks.molch.dmitr.core.dto.response.TicketPageDto;
import maks.molch.dmitr.core.dto.response.TicketResponseDto;
import maks.molch.dmitr.core.mapper.TicketMapper;
import maks.molch.dmitr.core.service.TicketService;
import maks.molch.dmitr.core.service.filter.TicketFilter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @GetMapping
    public TicketPageDto getTickets(
            @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
            @RequestParam(value = "start_date_and_time_filter", required = false) LocalDateTime startDateAndTimeFilter,
            @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
            @RequestParam(value = "end_date_and_time_filter", required = false) LocalDateTime endDateAndTimeFilter,
            @RequestParam(value = "departure_filter", required = false) String departureFilter,
            @RequestParam(value = "arrival_filter", required = false) String arrivalFilter,
            @RequestParam(value = "carrier_name_filter", required = false) String carrierNameFilter,
            @RequestParam(value = "page_number", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "page_size", required = false, defaultValue = "10") Integer pageSize
    ) {
        var filter = new TicketFilter(
                Optional.ofNullable(startDateAndTimeFilter),
                Optional.ofNullable(endDateAndTimeFilter),
                Optional.ofNullable(departureFilter),
                Optional.ofNullable(arrivalFilter),
                Optional.ofNullable(carrierNameFilter)
        );
        var page = ticketService.getTicketsPage(pageNumber, pageSize, filter);
        return ticketMapper.toPageDto(page, pageNumber, pageSize);
    }

    @PostMapping
    public TicketResponseDto addTicket(@Valid @RequestBody TicketCreateRequestDto createRequestDto) {
        var ticket = ticketMapper.toTicket(createRequestDto);
        var fullCreatedTicket = ticketService.addTicket(ticket);
        return ticketMapper.toDto(fullCreatedTicket);
    }
}
