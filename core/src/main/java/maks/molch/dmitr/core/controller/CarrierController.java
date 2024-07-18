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
import maks.molch.dmitr.core.dto.CarrierDto;
import maks.molch.dmitr.core.dto.response.TicketResponseDto;
import maks.molch.dmitr.core.mapper.CarrierMapper;
import maks.molch.dmitr.core.service.CarrierService;
import maks.molch.dmitr.core.service.entity.Carrier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Carrier Controller")
@RestController
@RequestMapping("/carrier")
@AllArgsConstructor
public class CarrierController {
    private final CarrierService carrierService;
    private final CarrierMapper carrierMapper;

    @Operation(summary = "Create new carrier")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Carrier was successful created",
                    content = @Content(schema = @Schema(implementation = TicketResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Carrier with such name already exist!",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))
            )
    })
    @PostMapping
    public CarrierDto addCarrier(@Valid @RequestBody CarrierDto dto) {
        var carrier = carrierMapper.toCarrier(dto);
        Carrier createdCarrier = carrierService.addCarrier(carrier);
        return carrierMapper.toDto(createdCarrier);
    }

    @Operation(summary = "Get carrier by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Carrier by id",
                    content = @Content(schema = @Schema(implementation = CarrierDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Carrier with such name was not found!",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))
            )
    })
    @GetMapping("/{id}")
    public CarrierDto getCarrier(@PathVariable("id") String id) {
        var carrier = carrierService.getCarrier(id);
        return carrierMapper.toDto(carrier);
    }

    @Operation(summary = "Update carrier")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Carrier was successfully updated!",
                    content = @Content(schema = @Schema(implementation = TicketResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Carrier with such name already exist!",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))
            )
    })
    @PutMapping
    public CarrierDto updateCarrier(@Valid @RequestBody CarrierDto dto) {
        var carrier = carrierMapper.toCarrier(dto);
        var updatedCarrier = carrierService.update(carrier);
        return carrierMapper.toDto(updatedCarrier);
    }

    @Operation(summary = "Delete carrier by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Carrier was successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Carrier with such name was not found!",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))
            )
    })
    @DeleteMapping("/{id}")
    public void deleteCarrier(@PathVariable("id") String id) {
        carrierService.delete(id);
    }
}
