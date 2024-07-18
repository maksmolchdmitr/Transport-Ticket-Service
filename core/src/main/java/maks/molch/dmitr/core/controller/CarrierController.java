package maks.molch.dmitr.core.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.dto.CarrierDto;
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

    @PostMapping
    public CarrierDto addCarrier(@Valid @RequestBody CarrierDto dto) {
        var carrier = carrierMapper.toCarrier(dto);
        Carrier createdCarrier = carrierService.addCarrier(carrier);
        return carrierMapper.toDto(createdCarrier);
    }

    @GetMapping("/{id}")
    public CarrierDto getCarrier(@PathVariable("id") String id) {
        var carrier = carrierService.getCarrier(id);
        return carrierMapper.toDto(carrier);
    }

    @PutMapping
    public CarrierDto updateCarrier(@Valid @RequestBody CarrierDto dto) {
        var carrier = carrierMapper.toCarrier(dto);
        var updatedCarrier = carrierService.update(carrier);
        return carrierMapper.toDto(updatedCarrier);
    }

    @DeleteMapping("/{id}")
    public void deleteCarrier(@PathVariable("id") String id) {
        carrierService.delete(id);
    }
}
