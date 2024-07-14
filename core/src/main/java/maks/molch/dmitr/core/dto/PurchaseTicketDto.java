package maks.molch.dmitr.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record PurchaseTicketDto(
        @JsonProperty("id")
        int id,
        @JsonProperty("ticket_id")
        int ticketId,
        @JsonProperty("user_login")
        String userLogin,
        @JsonProperty("purchase_datetime")
        LocalDateTime purchaseDatetime
) {
}
