package maks.molch.dmitr.core.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PurchaseTicketRequestDto(
        @NotNull
        @JsonProperty("ticket_id")
        int ticketId,
        @NotBlank
        @JsonProperty("user_login")
        String userLogin
) {
}
