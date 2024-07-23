package maks.molch.dmitr.core.mapper;

import maks.molch.dmitr.core.dto.CarrierDto;
import maks.molch.dmitr.core.jooq.tables.records.CarrierTableRecord;
import maks.molch.dmitr.core.service.entity.Carrier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarrierMapper {
    Carrier toCarrier(CarrierDto dto);

    Carrier toCarrier(CarrierTableRecord carrierRecord);

    CarrierDto toDto(Carrier carrier);

    CarrierTableRecord toRecord(Carrier carrier);
}
