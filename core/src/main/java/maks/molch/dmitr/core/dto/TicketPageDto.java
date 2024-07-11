package maks.molch.dmitr.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TicketPageDto(
        @JsonProperty("tickets")
        List<TicketDto> tickets,
        @JsonProperty("next_page_token")
        String nextPageToken
) {
}
