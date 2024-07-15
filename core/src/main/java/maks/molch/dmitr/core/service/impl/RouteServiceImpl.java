package maks.molch.dmitr.core.service.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.mapper.RouteMapper;
import maks.molch.dmitr.core.model.FullRoute;
import maks.molch.dmitr.core.model.Route;
import maks.molch.dmitr.core.repo.CarrierRepo;
import maks.molch.dmitr.core.repo.RouteRepo;
import maks.molch.dmitr.core.service.RouteService;
import maks.molch.dmitr.core.service.exception.AlreadyExistException;
import maks.molch.dmitr.core.service.exception.EntityNotFoundException;
import maks.molch.dmitr.core.service.filter.RouteFilter;
import org.jooq.exception.IntegrityConstraintViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final RouteRepo routeRepo;
    private final RouteMapper routeMapper;
    private final CarrierRepo carrierRepo;

    @Override
    public FullRoute addRoute(Route route) {
        try {
            var routeRecord = routeMapper.toRecord(route);
            var carrierRecord = carrierRepo.findById(route.carrierName())
                    .orElseThrow(() -> new EntityNotFoundException("Carrier with such id not found"));
            var createdRoute = routeRepo.save(routeRecord);
            return routeMapper.toRoute(createdRoute, carrierRecord);
        } catch (IntegrityConstraintViolationException e) {
            throw new AlreadyExistException("Such route already exist");
        }
    }

    @Override
    public List<FullRoute> getRoutePage(RouteFilter filter, Integer pageNumber, Integer pageSize) {
        var fullRouteRecords = routeRepo.findAllSortByPrimaryKeyAndFiltered(filter);
        var fromIndex = Math.min(pageNumber * pageSize, fullRouteRecords.size());
        var toIndex = Math.min(pageNumber * pageSize + pageSize, fullRouteRecords.size());
        return routeMapper.toRoute(fullRouteRecords.subList(fromIndex, toIndex));
    }
}
