package maks.molch.dmitr.core.mapper;

import maks.molch.dmitr.core.dto.RouteUpdateRequestDto;
import maks.molch.dmitr.core.dto.request.RouteCreateRequestDto;
import maks.molch.dmitr.core.dto.response.RoutePageDto;
import maks.molch.dmitr.core.dto.response.RouteResponseDto;
import maks.molch.dmitr.core.jooq.tables.records.CarrierTableRecord;
import maks.molch.dmitr.core.jooq.tables.records.RouteTableRecord;
import maks.molch.dmitr.core.service.entity.Carrier;
import maks.molch.dmitr.core.service.entity.FullRoute;
import maks.molch.dmitr.core.service.entity.Route;
import org.apache.commons.lang3.tuple.Pair;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    Route toRoute(RouteCreateRequestDto createRequestDto);

    Route toRoute(RouteUpdateRequestDto updateRequestDto);

    @Mapping(source = "carrierRecord", target = "carrier")
    FullRoute toFullRoute(RouteTableRecord routeRecord, CarrierTableRecord carrierRecord);

    default FullRoute toFullRoute(Pair<RouteTableRecord, CarrierTableRecord> record) {
        var route = record.getLeft();
        var carrier = record.getRight();
        return new FullRoute(
                Objects.requireNonNull(route.getId()),
                route.getDeparture(),
                route.getArrival(),
                new Carrier(
                        carrier.getName(),
                        carrier.getPhone()
                ),
                route.getDurationInMinutes(),
                route.getSeatCount()
        );
    }

    List<FullRoute> toFullRouteList(List<Pair<RouteTableRecord, CarrierTableRecord>> fullRouteRecords);

    RouteResponseDto toDto(FullRoute fullRoute);

    @Mapping(source = "page", target = "routes")
    RoutePageDto toPageDto(List<FullRoute> page, Integer pageNumber, Integer pageSize);

    RouteTableRecord toRecord(Route route);
}
