package maks.molch.dmitr.core.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.dto.TicketUpdateRequestDto;
import maks.molch.dmitr.core.dto.request.TicketCreateRequestDto;
import maks.molch.dmitr.core.dto.response.TicketPageDto;
import maks.molch.dmitr.core.dto.response.TicketResponseDto;
import maks.molch.dmitr.core.mapper.TicketMapper;
import maks.molch.dmitr.core.service.TicketService;
import maks.molch.dmitr.core.service.filter.TicketFilter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @PostMapping
    public TicketResponseDto addTicket(@Valid @RequestBody TicketCreateRequestDto createRequestDto) {
        var ticket = ticketMapper.toTicket(createRequestDto);
        var fullCreatedTicket = ticketService.addTicket(ticket);
        return ticketMapper.toDto(fullCreatedTicket);
    }

    @GetMapping("/{id}")
    public TicketResponseDto getTicket(@PathVariable Integer id) {
        var fullTicket = ticketService.getFullTicket(id);
        return ticketMapper.toDto(fullTicket);
    }

    @PutMapping
    public TicketResponseDto update(@Valid @RequestBody TicketUpdateRequestDto updateRequestDto) {
        var ticket = ticketMapper.toTicket(updateRequestDto);
        var fullUpdatedTicket = ticketService.updateTicket(updateRequestDto.id(), ticket);
        return ticketMapper.toDto(fullUpdatedTicket);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Integer id) {
        ticketService.deleteTicket(id);
    }

    @GetMapping("/page")
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
                Optional.ofNullable(carrierNameFilter),
                true
        );
        var page = ticketService.getTicketsPage(pageNumber, pageSize, filter);
        return ticketMapper.toPageDto(page, pageNumber, pageSize);
    }
}
