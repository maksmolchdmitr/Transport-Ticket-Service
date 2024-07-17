package maks.molch.dmitr.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PurchaseTicketRequestDto(
        @JsonProperty("ticket_id")
        int ticketId,
        @JsonProperty("user_login")
        String userLogin
) {
}
