package maks.molch.dmitr.core.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.controller.exception.ErrorMessage;
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

@Tag(name = "Ticket Controller")
@RestController
@AllArgsConstructor
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @Operation(summary = "Create new ticket")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ticket was successful created",
                    content = @Content(schema = @Schema(implementation = TicketResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Such ticket already exist! Each route, timestamp, and seat number should form a unique set!)",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))
            )
    })
    @PostMapping
    public TicketResponseDto addTicket(@Valid @RequestBody TicketCreateRequestDto createRequestDto) {
        var ticket = ticketMapper.toTicket(createRequestDto);
        var fullCreatedTicket = ticketService.addTicket(ticket);
        return ticketMapper.toDto(fullCreatedTicket);
    }

    @Operation(summary = "Get ticket by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ticket by id",
                    content = @Content(schema = @Schema(implementation = TicketResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ticket with such id was not found!",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))
            )
    })
    @GetMapping("/{id}")
    public TicketResponseDto getTicket(@PathVariable Integer id) {
        var fullTicket = ticketService.getFullTicket(id);
        return ticketMapper.toDto(fullTicket);
    }

    @Operation(summary = "Update ticket")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ticket was successfully updated!",
                    content = @Content(schema = @Schema(implementation = TicketResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Such ticket already exist! Each route, timestamp, and seat number should form a unique set!)",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))
            )
    })
    @PutMapping
    public TicketResponseDto update(@Valid @RequestBody TicketUpdateRequestDto updateRequestDto) {
        var ticket = ticketMapper.toTicket(updateRequestDto);
        var fullUpdatedTicket = ticketService.updateTicket(updateRequestDto.id(), ticket);
        return ticketMapper.toDto(fullUpdatedTicket);
    }

    @Operation(summary = "Delete ticket by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ticket was successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User with such login not found!",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User with such login not found!",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))
            )
    })
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Integer id) {
        ticketService.deleteTicket(id);
    }

    @Operation(summary = "Get tickets page by filters")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tickets page",
                    content = @Content(schema = @Schema(implementation = TicketPageDto.class))
            )
    })
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
