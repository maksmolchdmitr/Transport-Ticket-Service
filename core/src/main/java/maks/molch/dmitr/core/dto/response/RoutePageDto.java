package maks.molch.dmitr.core.dto.response;

import java.util.List;

public record RoutePageDto(
        List<RouteResponseDto> routes,
        int pageNumber,
        int pageSize
) {
}
