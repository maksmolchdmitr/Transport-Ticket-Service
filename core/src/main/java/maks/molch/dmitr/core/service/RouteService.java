package maks.molch.dmitr.core.service;

import maks.molch.dmitr.core.model.FullRoute;
import maks.molch.dmitr.core.model.Route;
import maks.molch.dmitr.core.service.filter.RouteFilter;

import java.util.List;

public interface RouteService {
    FullRoute addRoute(Route route);

    List<FullRoute> getRoutePage(RouteFilter filter, Integer pageNumber, Integer pageSize);
}
