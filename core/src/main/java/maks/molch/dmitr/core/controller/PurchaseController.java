package maks.molch.dmitr.core.controller;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.dto.PurchaseTicketDto;
import maks.molch.dmitr.core.dto.PurchaseTicketRequestDto;
import maks.molch.dmitr.core.mapper.TicketMapper;
import maks.molch.dmitr.core.service.TicketService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
@AllArgsConstructor
public class PurchaseController {
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @PostMapping
    public PurchaseTicketDto purchaseTicket(@RequestBody PurchaseTicketRequestDto purchaseRequest) {
        var ticketPurchase = ticketService.purchase(purchaseRequest.ticketId(), purchaseRequest.userLogin());
        return ticketMapper.toPurchaseDto(ticketPurchase);
    }
}
