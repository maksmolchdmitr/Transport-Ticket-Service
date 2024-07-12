package maks.molch.dmitr.core.mapper;

import maks.molch.dmitr.core.dto.RouteDto;
import maks.molch.dmitr.core.dto.RouterCreateRequestDto;
import maks.molch.dmitr.core.jooq.tables.records.CarrierTableRecord;
import maks.molch.dmitr.core.jooq.tables.records.RouteTableRecord;
import maks.molch.dmitr.core.model.FullRoute;
import maks.molch.dmitr.core.model.Route;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    FullRoute toRoute(RouteDto dto);

    RouteDto toDto(FullRoute fullRoute);

    Route toRoute(RouterCreateRequestDto createRequestDto);

    @Mapping(source = "carrierRecord", target = "carrier")
    FullRoute toRoute(RouteTableRecord routeRecord, CarrierTableRecord carrierRecord);

    RouteTableRecord toRecord(Route route);
}
