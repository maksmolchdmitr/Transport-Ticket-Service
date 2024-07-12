package maks.molch.dmitr.core.mapper;

import maks.molch.dmitr.core.dto.TicketCreateRequestDto;
import maks.molch.dmitr.core.dto.TicketDto;
import maks.molch.dmitr.core.dto.TicketPageDto;
import maks.molch.dmitr.core.jooq.tables.records.CarrierTableRecord;
import maks.molch.dmitr.core.jooq.tables.records.RouteTableRecord;
import maks.molch.dmitr.core.jooq.tables.records.TicketTableRecord;
import maks.molch.dmitr.core.model.FullRoute;
import maks.molch.dmitr.core.model.FullTicket;
import maks.molch.dmitr.core.model.Ticket;
import maks.molch.dmitr.core.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    FullTicket toFullTicket(TicketDto dto);

    TicketDto toDto(FullTicket fullTicket);

    @Mapping(source = "fullTicketPage.nextToken", target = "nextPageToken")
    @Mapping(source = "fullTicketPage.items", target = "tickets")
    TicketPageDto toPageDto(Page<FullTicket> fullTicketPage);

    Ticket toTicket(TicketCreateRequestDto createRequestDto);

    TicketTableRecord toRecord(Ticket ticket);

    FullTicket toFullTicket(TicketTableRecord ticketRecord, FullRoute fullRoute);

    default Timestamp map(LocalDateTime value) {
        return Timestamp.valueOf(value);
    }
}
