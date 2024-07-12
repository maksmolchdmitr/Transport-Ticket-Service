package maks.molch.dmitr.core.service.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.mapper.RouteMapper;
import maks.molch.dmitr.core.model.FullRoute;
import maks.molch.dmitr.core.model.Route;
import maks.molch.dmitr.core.repo.CarrierRepo;
import maks.molch.dmitr.core.repo.RouteRepo;
import maks.molch.dmitr.core.service.RouteService;
import maks.molch.dmitr.core.service.exception.AlreadyExistException;
import org.jooq.exception.IntegrityConstraintViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final RouteRepo routeRepo;
    private final RouteMapper routeMapper;
    private final CarrierRepo carrierRepo;

    @Override
    public FullRoute addRoute(Route route) {
        var routeRecord = routeMapper.toRecord(route);
        try {
            routeRepo.save(routeRecord);
        }catch (IntegrityConstraintViolationException e) {
            throw new AlreadyExistException("Route with such id already exist");
        }
        var carrierRecord = carrierRepo.findById(route.carrierName());
        return routeMapper.toRoute(routeRecord, carrierRecord);
    }
}
