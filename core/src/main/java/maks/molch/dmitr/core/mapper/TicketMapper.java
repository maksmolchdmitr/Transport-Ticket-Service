package maks.molch.dmitr.core.mapper;

import maks.molch.dmitr.core.dto.request.TicketCreateRequestDto;
import maks.molch.dmitr.core.dto.response.PurchaseTicketResponseDto;
import maks.molch.dmitr.core.dto.response.TicketPageDto;
import maks.molch.dmitr.core.dto.response.TicketResponseDto;
import maks.molch.dmitr.core.jooq.tables.records.CarrierTableRecord;
import maks.molch.dmitr.core.jooq.tables.records.PurchasedTicketsTableRecord;
import maks.molch.dmitr.core.jooq.tables.records.RouteTableRecord;
import maks.molch.dmitr.core.jooq.tables.records.TicketTableRecord;
import maks.molch.dmitr.core.service.entity.*;
import org.apache.commons.lang3.tuple.Triple;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    FullTicket toFullTicket(TicketResponseDto dto);

    @Mapping(source = "fullTicket.fullRoute", target = "route")
    TicketResponseDto toDto(FullTicket fullTicket);

    @Mapping(source = "fullTicketPage", target = "tickets")
    TicketPageDto toPageDto(List<FullTicket> fullTicketPage, int pageNumber, int pageSize);

    Ticket toTicket(TicketCreateRequestDto createRequestDto);

    TicketTableRecord toRecord(Ticket ticket);

    @Mapping(source = "ticketRecord.id", target = "id")
    FullTicket toFullTicket(TicketTableRecord ticketRecord, FullRoute fullRoute);

    Ticket toTicket(TicketTableRecord ticketTableRecord);

    default FullTicket toTicket(Triple<TicketTableRecord, RouteTableRecord, CarrierTableRecord> triple) {
        var ticket = triple.getLeft();
        var route = triple.getMiddle();
        var carrier = triple.getRight();
        return new FullTicket(
                Objects.requireNonNull(ticket.getId()),
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
                ticket.getDateAndTime(),
                ticket.getSeatNumber(),
                new BigDecimal(ticket.getPrice())
        );
    }

    List<FullTicket> toTickets(List<Triple<TicketTableRecord, RouteTableRecord, CarrierTableRecord>> triples);

    PurchaseTicketResponseDto toPurchaseDto(TicketPurchase ticketPurchase);

    @Mapping(target = "purchaseDatetime", expression = "java(LocalDateTime.now())")
    PurchasedTicketsTableRecord toPurchaseRecord(int ticketId, String userLogin);

    @Mapping(source = "fullTicket", target = "ticket")
    @Mapping(source = "purchaseRecord.id", target = "id")
    TicketPurchase toPurchase(PurchasedTicketsTableRecord purchaseRecord, FullTicket fullTicket, String userLogin);

    List<TicketPurchase> toPurchase(List<PurchasedTicketsTableRecord> ticketPurchases);

    List<PurchaseTicketResponseDto> toPurchaseDto(List<TicketPurchase> ticketPurchases);

    TicketPurchase toPurchase(PurchasedTicketsTableRecord purchase);
}
