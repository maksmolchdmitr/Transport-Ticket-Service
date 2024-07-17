package maks.molch.dmitr.core.service;

import maks.molch.dmitr.core.service.entity.Carrier;

public interface CarrierService {
    Carrier addCarrier(Carrier carrier);

    Carrier getCarrier(String id);

    Carrier update(Carrier carrier);

    void delete(String id);
}
