package maks.molch.dmitr.core.mapper;

import maks.molch.dmitr.core.dto.TicketCreateRequestDto;
import maks.molch.dmitr.core.dto.TicketDto;
import maks.molch.dmitr.core.dto.TicketPageDto;
import maks.molch.dmitr.core.jooq.tables.records.CarrierTableRecord;
import maks.molch.dmitr.core.jooq.tables.records.RouteTableRecord;
import maks.molch.dmitr.core.jooq.tables.records.TicketTableRecord;
import maks.molch.dmitr.core.model.Carrier;
import maks.molch.dmitr.core.model.FullRoute;
import maks.molch.dmitr.core.model.FullTicket;
import maks.molch.dmitr.core.model.Ticket;
import org.apache.commons.lang3.tuple.Triple;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    FullTicket toFullTicket(TicketDto dto);

    @Mapping(source = "fullTicket.fullRoute", target = "route")
    TicketDto toDto(FullTicket fullTicket);

    @Mapping(source = "fullTicketPage", target = "tickets")
    TicketPageDto toPageDto(List<FullTicket> fullTicketPage, int pageNumber, int pageSize);

    Ticket toTicket(TicketCreateRequestDto createRequestDto);

    TicketTableRecord toRecord(Ticket ticket);

    FullTicket toFullTicket(TicketTableRecord ticketRecord, FullRoute fullRoute);

    default Timestamp map(LocalDateTime value) {
        return Timestamp.valueOf(value);
    }

    Ticket toTicket(TicketTableRecord ticketTableRecord);

    default FullTicket toTicket(Triple<TicketTableRecord, RouteTableRecord, CarrierTableRecord> triple) {
        var ticket = triple.getLeft();
        var route = triple.getMiddle();
        var carrier = triple.getRight();
        return new FullTicket(
                new FullRoute(
                        Objects.requireNonNull(route.getId()),
                        route.getDeparture(),
                        route.getArrival(),
                        new Carrier(
                                carrier.getName(),
                                carrier.getPhone()
                        ),
                        route.getDurationInMinutes(),
                        route.getSeatCount()
                ),
                Timestamp.valueOf(ticket.getDateAndTime()),
                ticket.getSeatNumber(),
                new BigDecimal(ticket.getPrice())
        );
    }

    List<FullTicket> toTickets(List<Triple<TicketTableRecord, RouteTableRecord, CarrierTableRecord>> triples);

}
