package maks.molch.dmitr.core.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PurchaseTicketResponseDto(
        @JsonProperty("id")
        int id,
        @JsonProperty("ticket")
        TicketResponseDto ticket,
        @JsonProperty("user_login")
        String userLogin,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
        @JsonProperty("purchase_datetime")
        LocalDateTime purchaseDatetime
) {
}
