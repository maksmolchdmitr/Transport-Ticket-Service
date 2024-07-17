package maks.molch.dmitr.core.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.dto.request.RouteCreateRequestDto;
import maks.molch.dmitr.core.dto.response.RoutePageDto;
import maks.molch.dmitr.core.dto.response.RouteResponseDto;
import maks.molch.dmitr.core.mapper.RouteMapper;
import maks.molch.dmitr.core.service.RouteService;
import maks.molch.dmitr.core.service.entity.FullRoute;
import maks.molch.dmitr.core.service.filter.RouteFilter;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/route")
@AllArgsConstructor
public class RouteController {
    private final RouteMapper routeMapper;
    private final RouteService routeService;

    @PostMapping
    public RouteResponseDto route(@Valid @RequestBody RouteCreateRequestDto createRequestDto) {
        var route = routeMapper.toRoute(createRequestDto);
        FullRoute fullRoute = routeService.addRoute(route);
        return routeMapper.toDto(fullRoute);
    }

    @GetMapping
    public RoutePageDto routes(
            @RequestParam(value = "departure_filter", required = false) String departureFilter,
            @RequestParam(value = "arrival_filter", required = false) String arrivalFilter,
            @RequestParam(value = "carrier_name_filter", required = false) String carrierNameFilter,
            @RequestParam(value = "page_number", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "page_size", required = false, defaultValue = "10") Integer pageSize
    ) {
        var filter = new RouteFilter(
                Optional.ofNullable(departureFilter),
                Optional.ofNullable(arrivalFilter),
                Optional.ofNullable(carrierNameFilter)
        );
        var page = routeService.getRoutePage(filter, pageNumber, pageSize);
        return routeMapper.toPageDto(page, pageNumber, pageSize);
    }
}
