package maks.molch.dmitr.core.mapper;

import maks.molch.dmitr.core.dto.TicketDto;
import maks.molch.dmitr.core.dto.TicketPageDto;
import maks.molch.dmitr.core.model.FullTicket;
import maks.molch.dmitr.core.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    FullTicket toFullTicket(TicketDto dto);

    TicketDto toDto(FullTicket fullTicket);

    @Mapping(source = "fullTicketPage.nextToken", target = "nextPageToken")
    @Mapping(source = "fullTicketPage.items", target = "tickets")
    TicketPageDto toPageDto(Page<FullTicket> fullTicketPage);
}
