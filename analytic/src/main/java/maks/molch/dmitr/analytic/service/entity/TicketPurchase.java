package maks.molch.dmitr.analytic.service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketPurchase(
        String userLogin,
        LocalDateTime purchaseDatetime,
        LocalDateTime ticketDatetime,
        int ticketSeatNumber,
        BigDecimal ticketPrice,
        String routeDeparture,
        String routeArrival,
        String carrierName,
        int routeDurationInMinutes,
        int routeSeatCount
) {
}
