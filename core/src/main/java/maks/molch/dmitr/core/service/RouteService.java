package maks.molch.dmitr.core.service;

import maks.molch.dmitr.core.service.entity.FullRoute;
import maks.molch.dmitr.core.service.entity.Route;
import maks.molch.dmitr.core.service.filter.RouteFilter;

import java.util.List;

public interface RouteService {
    FullRoute addRoute(Route route);

    List<FullRoute> getRoutePage(RouteFilter filter, Integer pageNumber, Integer pageSize);

    FullRoute getFullRoute(Integer id);

    FullRoute updateRoute(int id, Route route);

    void deleteRoute(Integer id);
}
