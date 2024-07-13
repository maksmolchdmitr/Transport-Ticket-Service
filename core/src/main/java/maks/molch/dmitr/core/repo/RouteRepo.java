package maks.molch.dmitr.core.repo;

import maks.molch.dmitr.core.jooq.tables.records.CarrierTableRecord;
import maks.molch.dmitr.core.jooq.tables.records.RouteTableRecord;
import maks.molch.dmitr.core.service.filter.RouteFilter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface RouteRepo extends JooqRepo<RouteTableRecord, Integer> {
    List<Pair<RouteTableRecord, CarrierTableRecord>> findAllSortByPrimaryKeyAndFiltered(RouteFilter routeFilter);
}
