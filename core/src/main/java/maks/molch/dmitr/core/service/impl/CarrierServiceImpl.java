package maks.molch.dmitr.core.service.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.mapper.CarrierMapper;
import maks.molch.dmitr.core.repo.CarrierRepo;
import maks.molch.dmitr.core.service.CarrierService;
import maks.molch.dmitr.core.service.entity.Carrier;
import maks.molch.dmitr.core.service.exception.AlreadyExistException;
import maks.molch.dmitr.core.service.exception.EntityNotFoundException;
import org.jooq.exception.IntegrityConstraintViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarrierServiceImpl implements CarrierService {
    private final CarrierRepo carrierRepo;
    private final CarrierMapper carrierMapper;

    @Override
    public Carrier addCarrier(Carrier carrier) {
        try {
            var carrierRecord = carrierMapper.toRecord(carrier);
            var createdCarrier = carrierRepo.save(carrierRecord);
            return carrierMapper.toCarrier(createdCarrier);
        } catch (IntegrityConstraintViolationException e) {
            throw new AlreadyExistException("Carrier with such name already exist");
        }
    }

    @Override
    public Carrier getCarrier(String id) {
        var carrierRecord = carrierRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carrier with such id not found"));
        return carrierMapper.toCarrier(carrierRecord);
    }

    @Override
    public Carrier update(Carrier carrier) {
        var carrierRecord = carrierMapper.toRecord(carrier);
        var updatedCarrierRecord = carrierRepo.update(carrierRecord);
        return carrierMapper.toCarrier(updatedCarrierRecord);
    }

    @Override
    public void delete(String id) {
        carrierRepo.delete(id);
    }
}
