package maks.molch.dmitr.core.repo;

import org.jooq.UpdatableRecord;

import java.util.List;
import java.util.Optional;

public interface JooqRepo<R extends UpdatableRecord<R>, P> {
    List<R> findAll();

    Optional<R> findById(P id);

    R save(R entity);

    R update(R entity);

    void delete(P id);

    boolean exist(P id);
}
