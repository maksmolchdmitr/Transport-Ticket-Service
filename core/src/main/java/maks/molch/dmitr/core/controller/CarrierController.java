package maks.molch.dmitr.core.controller;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.dto.CarrierDto;
import maks.molch.dmitr.core.mapper.CarrierMapper;
import maks.molch.dmitr.core.service.CarrierService;
import maks.molch.dmitr.core.service.entity.Carrier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carrier")
@AllArgsConstructor
public class CarrierController {
    private final CarrierService carrierService;
    private final CarrierMapper carrierMapper;

    @PostMapping
    public CarrierDto addCarrier(@RequestBody CarrierDto dto) {
        var carrier = carrierMapper.toCarrier(dto);
        Carrier createdCarrier = carrierService.addCarrier(carrier);
        return carrierMapper.toDto(createdCarrier);
    }
}
