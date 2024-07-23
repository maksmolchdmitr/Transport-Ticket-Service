package maks.molch.dmitr.core.mapper;

import maks.molch.dmitr.core.dto.TicketUpdateRequestDto;
import maks.molch.dmitr.core.dto.request.TicketCreateRequestDto;
import maks.molch.dmitr.core.dto.response.PurchaseTicketResponseDto;
import maks.molch.dmitr.core.dto.response.TicketPageDto;
import maks.molch.dmitr.core.dto.response.TicketResponseDto;
import maks.molch.dmitr.core.jooq.tables.records.CarrierTableRecord;
import maks.molch.dmitr.core.jooq.tables.records.PurchasedTicketsTableRecord;
import maks.molch.dmitr.core.jooq.tables.records.RouteTableRecord;
import maks.molch.dmitr.core.jooq.tables.records.TicketTableRecord;
import maks.molch.dmitr.core.service.entity.Carrier;
import maks.molch.dmitr.core.service.entity.FullRoute;
import maks.molch.dmitr.core.service.entity.FullTicket;
import maks.molch.dmitr.core.service.entity.Ticket;
import maks.molch.dmitr.core.service.entity.TicketPurchase;
import org.apache.commons.lang3.tuple.Triple;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    Ticket toTicket(TicketUpdateRequestDto updateRequestDto);

    Ticket toTicket(TicketCreateRequestDto createRequestDto);

    Ticket toTicket(TicketTableRecord ticketTableRecord);

    FullTicket toFullTicket(TicketResponseDto dto);

    @Mapping(source = "ticketRecord.id", target = "id")
    FullTicket toFullTicket(TicketTableRecord ticketRecord, FullRoute fullRoute);

    default FullTicket toFullTicket(Triple<TicketTableRecord, RouteTableRecord, CarrierTableRecord> triple) {
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

    List<FullTicket> toFullTicketList(List<Triple<TicketTableRecord, RouteTableRecord, CarrierTableRecord>> triples);

    TicketTableRecord toRecord(int id, Ticket ticket);

    @Mapping(source = "fullTicket.fullRoute", target = "route")
    TicketResponseDto toDto(FullTicket fullTicket);

    @Mapping(source = "fullTicketPage", target = "tickets")
    TicketPageDto toPageDto(List<FullTicket> fullTicketPage, int pageNumber, int pageSize);

    TicketTableRecord toRecord(Ticket ticket);

    PurchaseTicketResponseDto toPurchaseDto(TicketPurchase ticketPurchase);

    @Mapping(target = "purchaseDatetime", expression = "java(LocalDateTime.now())")
    PurchasedTicketsTableRecord toPurchaseRecord(int ticketId, String userLogin);

    @Mapping(source = "fullTicket", target = "ticket")
    @Mapping(source = "purchaseRecord.id", target = "id")
    TicketPurchase toPurchase(PurchasedTicketsTableRecord purchaseRecord, FullTicket fullTicket, String userLogin);

    List<TicketPurchase> toPurchaseList(List<PurchasedTicketsTableRecord> ticketPurchases);

    List<PurchaseTicketResponseDto> toPurchaseDtoList(List<TicketPurchase> ticketPurchases);

    TicketPurchase toPurchase(PurchasedTicketsTableRecord purchase);

    default maks.molch.dmitr.core.service.kafka.entity.TicketPurchase toKafkaPurchase(
            TicketPurchase ticketPurchase,
            FullTicket ticket
    ) {
        var route = ticket.fullRoute();
        var carrier = ticket.fullRoute().carrier();
        return new maks.molch.dmitr.core.service.kafka.entity.TicketPurchase(
                ticketPurchase.userLogin(),
                ticketPurchase.purchaseDatetime(),
                ticket.dateAndTime(),
                ticket.seatNumber(),
                ticket.price(),
                route.departure(),
                route.arrival(),
                carrier.name(),
                route.durationInMinutes(),
                route.seatCount()
        );
    }
}
