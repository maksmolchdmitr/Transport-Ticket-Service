package maks.molch.dmitr.core.mapper;

import maks.molch.dmitr.core.dto.RouteDto;
import maks.molch.dmitr.core.dto.RoutePageDto;
import maks.molch.dmitr.core.dto.RouterCreateRequestDto;
import maks.molch.dmitr.core.jooq.tables.records.CarrierTableRecord;
import maks.molch.dmitr.core.jooq.tables.records.RouteTableRecord;
import maks.molch.dmitr.core.model.Carrier;
import maks.molch.dmitr.core.model.FullRoute;
import maks.molch.dmitr.core.model.Route;
import org.apache.commons.lang3.tuple.Pair;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    FullRoute toRoute(RouteDto dto);

    RouteDto toDto(FullRoute fullRoute);

    Route toRoute(RouterCreateRequestDto createRequestDto);

    @Mapping(source = "carrierRecord", target = "carrier")
    FullRoute toRoute(RouteTableRecord routeRecord, CarrierTableRecord carrierRecord);

    RouteTableRecord toRecord(Route route);

    @Mapping(source = "page", target = "routes")
    RoutePageDto toPageDto(List<FullRoute> page, Integer pageNumber, Integer pageSize);

    List<FullRoute> toRoute(List<Pair<RouteTableRecord, CarrierTableRecord>> fullRouteRecords);

    default FullRoute toRoute(Pair<RouteTableRecord, CarrierTableRecord> record) {
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
}
