package maks.molch.dmitr.core.dto;

import java.util.List;

public record RoutePageDto(
        List<RouteDto> routes,
        int pageNumber,
        int pageSize
) {
}
