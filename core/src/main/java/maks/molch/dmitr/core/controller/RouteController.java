package maks.molch.dmitr.core.controller;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.dto.RouteDto;
import maks.molch.dmitr.core.dto.RouterCreateRequestDto;
import maks.molch.dmitr.core.mapper.RouteMapper;
import maks.molch.dmitr.core.model.FullRoute;
import maks.molch.dmitr.core.service.RouteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/route")
@AllArgsConstructor
public class RouteController {
    private final RouteMapper routeMapper;
    private final RouteService routeService;

    @PostMapping
    public RouteDto route(@RequestBody RouterCreateRequestDto createRequestDto) {
        var route = routeMapper.toRoute(createRequestDto);
        FullRoute fullRoute = routeService.addRoute(route);
        return routeMapper.toDto(fullRoute);
    }
}
