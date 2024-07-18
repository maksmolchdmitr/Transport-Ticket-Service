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
import maks.molch.dmitr.core.dto.request.PurchaseTicketRequestDto;
import maks.molch.dmitr.core.dto.response.PurchaseTicketResponseDto;
import maks.molch.dmitr.core.mapper.TicketMapper;
import maks.molch.dmitr.core.service.TicketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Ticket Purchase Controller")
@RestController
@RequestMapping("/purchase")
@AllArgsConstructor
public class PurchaseController {
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @Operation(summary = "Create new ticket purchase")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ticket purchase was successfully created!",
                    content = @Content(schema = @Schema(implementation = PurchaseTicketRequestDto.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Such ticket purchase already exist! Combination of user login and  should be unique!",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))
            )
    })
    @PostMapping
    public PurchaseTicketResponseDto purchaseTicket(@Valid @RequestBody PurchaseTicketRequestDto purchaseRequest) {
        var ticketPurchase = ticketService.purchase(purchaseRequest.ticketId(), purchaseRequest.userLogin());
        return ticketMapper.toPurchaseDto(ticketPurchase);
    }

    @GetMapping("/all/{user_login}")
    public List<PurchaseTicketResponseDto> purchaseTickets(@PathVariable("user_login") String userLogin) {
        var ticketPurchases = ticketService.getUserTicketPurchases(userLogin);
        return ticketMapper.toPurchaseDto(ticketPurchases);
    }

    @GetMapping("/{id}")
    public PurchaseTicketResponseDto getPurchaseTicket(@PathVariable("id") Integer ticketPurchaseId) {
        var ticketPurchase = ticketService.getTicketPurchase(ticketPurchaseId);
        return ticketMapper.toPurchaseDto(ticketPurchase);
    }
}
