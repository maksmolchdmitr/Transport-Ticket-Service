package maks.molch.dmitr.core.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TicketPageDto(
        @JsonProperty("tickets")
        List<TicketResponseDto> tickets,
        @JsonProperty("page_number")
        int pageNumber,
        @JsonProperty("page_size")
        int pageSize
) {
}
