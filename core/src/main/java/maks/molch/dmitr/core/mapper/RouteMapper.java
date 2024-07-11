package maks.molch.dmitr.core.mapper;

import maks.molch.dmitr.core.dto.RouteDto;
import maks.molch.dmitr.core.model.Route;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    Route toRoute(RouteDto dto);

    RouteDto toDto(Route route);
}
