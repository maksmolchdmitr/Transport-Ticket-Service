package maks.molch.dmitr.core.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PurchaseTicketRequestDto(
        @NotNull
        @JsonProperty("ticket_id")
        int ticketId,
        @Schema(name = "user_login", example = "admin")
        @NotBlank
        @JsonProperty("user_login")
        String userLogin
) {
}
