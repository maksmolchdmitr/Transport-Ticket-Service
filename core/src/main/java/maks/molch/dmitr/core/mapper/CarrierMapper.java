package maks.molch.dmitr.core.mapper;

import maks.molch.dmitr.core.dto.CarrierDto;
import maks.molch.dmitr.core.model.Carrier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarrierMapper {
    Carrier toCarrier(CarrierDto dto);

    CarrierDto toDto(Carrier carrier);
}