package maks.molch.dmitr.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TicketPageDto(
        @JsonProperty("tickets")
        List<TicketDto> tickets,
        @JsonProperty("page_number")
        int pageNumber,
        @JsonProperty("page_size")
        int pageSize
) {
}
