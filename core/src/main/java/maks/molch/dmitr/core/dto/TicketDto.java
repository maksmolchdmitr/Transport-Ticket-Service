package maks.molch.dmitr.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketDto(
        @JsonProperty("route")
        RouteDto route,
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
        @JsonProperty("date_and_time")
        LocalDateTime dateAndTime,
        @JsonProperty("seat_number")
        int seatNumber,
        @JsonProperty("price")
        BigDecimal price
) {
}
