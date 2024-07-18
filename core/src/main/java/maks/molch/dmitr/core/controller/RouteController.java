package maks.molch.dmitr.core.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.controller.exception.ErrorMessage;
import maks.molch.dmitr.core.dto.RouteUpdateRequestDto;
import maks.molch.dmitr.core.dto.request.RouteCreateRequestDto;
import maks.molch.dmitr.core.dto.response.RoutePageDto;
import maks.molch.dmitr.core.dto.response.RouteResponseDto;
import maks.molch.dmitr.core.mapper.RouteMapper;
import maks.molch.dmitr.core.service.RouteService;
import maks.molch.dmitr.core.service.entity.FullRoute;
import maks.molch.dmitr.core.service.filter.RouteFilter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "Route Controller")
@RestController
@RequestMapping("/route")
@AllArgsConstructor
public class RouteController {
    private final RouteMapper routeMapper;
    private final RouteService routeService;

    @Operation(summary = "Create new route")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Route was successfully created!",
                    content = @Content(schema = @Schema(implementation = RouteResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Such route already exist! Combination of departure, arrival and carrier name should be unique!",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))
            )
    })
    @PostMapping
    public RouteResponseDto route(@Valid @RequestBody RouteCreateRequestDto createRequestDto) {
        var route = routeMapper.toRoute(createRequestDto);
        FullRoute fullRoute = routeService.addRoute(route);
        return routeMapper.toDto(fullRoute);
    }

    @Operation(summary = "Get route by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Route was successfully found",
                    content = @Content(schema = @Schema(implementation = RouteResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Route with such id was not found!",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))
            )
    })
    @GetMapping("/{id}")
    public RouteResponseDto route(@PathVariable Integer id) {
        var fullRoute = routeService.getFullRoute(id);
        return routeMapper.toDto(fullRoute);
    }

    @Operation(summary = "Update route")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Route was successfully updated!",
                    content = @Content(schema = @Schema(implementation = RouteResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Such route already exist! Combination of departure, arrival and carrier name should be unique!",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))
            )
    })
    @PutMapping
    public RouteResponseDto updateRoute(@Valid @RequestBody RouteUpdateRequestDto updateRequestDto) {
        var route = routeMapper.toRoute(updateRequestDto);
        var fullUpdatedRoute = routeService.updateRoute(updateRequestDto.id(), route);
        return routeMapper.toDto(fullUpdatedRoute);
    }

    @Operation(summary = "Delete route by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Route was successfully deleted!"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Route with such id was not found!",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))
            )
    })
    @DeleteMapping
    public void deleteRoute(@RequestParam Integer id) {
        routeService.deleteRoute(id);
    }

    @Operation(summary = "Get route page by filters")
    @GetMapping("/page")
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
