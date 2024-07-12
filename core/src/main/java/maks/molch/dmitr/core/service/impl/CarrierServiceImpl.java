package maks.molch.dmitr.core.service.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.mapper.CarrierMapper;
import maks.molch.dmitr.core.model.Carrier;
import maks.molch.dmitr.core.repo.CarrierRepo;
import maks.molch.dmitr.core.service.CarrierService;
import maks.molch.dmitr.core.service.exception.AlreadyExistException;
import org.jooq.exception.IntegrityConstraintViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarrierServiceImpl implements CarrierService {
    private final CarrierRepo carrierRepo;
    private final CarrierMapper carrierMapper;

    @Override
    public Carrier addCarrier(Carrier carrier) {
        var carrierRecord = carrierMapper.toRecord(carrier);
        try {
            carrierRepo.save(carrierRecord);
        } catch (IntegrityConstraintViolationException e) {
            throw new AlreadyExistException("Carrier with such name already exist");
        }
        return carrier;
    }
}
