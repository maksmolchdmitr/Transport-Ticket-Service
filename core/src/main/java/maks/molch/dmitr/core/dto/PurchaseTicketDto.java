package maks.molch.dmitr.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record PurchaseTicketDto(
        @JsonProperty("id")
        int id,
        @JsonProperty("ticket")
        TicketDto ticket,
        @JsonProperty("user_login")
        String userLogin,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
        @JsonProperty("purchase_datetime")
        LocalDateTime purchaseDatetime
) {
}
