package maks.molch.dmitr.core.controller;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.dto.PurchaseTicketDto;
import maks.molch.dmitr.core.dto.PurchaseTicketRequestDto;
import maks.molch.dmitr.core.mapper.TicketMapper;
import maks.molch.dmitr.core.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all/{user_login}")
    public List<PurchaseTicketDto> purchaseTickets(@PathVariable("user_login") String userLogin) {
        var ticketPurchases = ticketService.getUserTicketPurchases(userLogin);
        return ticketMapper.toPurchaseDto(ticketPurchases);
    }

    @GetMapping("/{id}")
    public PurchaseTicketDto getPurchaseTicket(@PathVariable("id") Integer ticketPurchaseId) {
        var ticketPurchase = ticketService.getTicketPurchase(ticketPurchaseId);
        return ticketMapper.toPurchaseDto(ticketPurchase);
    }
}
