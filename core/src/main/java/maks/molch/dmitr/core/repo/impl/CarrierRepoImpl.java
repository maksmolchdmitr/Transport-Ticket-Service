package maks.molch.dmitr.core.repo.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.jooq.Tables;
import maks.molch.dmitr.core.jooq.tables.records.CarrierTableRecord;
import maks.molch.dmitr.core.repo.CarrierRepo;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class CarrierRepoImpl implements CarrierRepo {
    private final DSLContext context;

    @Override
    public List<CarrierTableRecord> findAll() {
        return context
                .selectFrom(Tables.CARRIER_TABLE)
                .fetch();
    }

    @Override
    public CarrierTableRecord findById(String id) {
        return context
                .selectFrom(Tables.CARRIER_TABLE)
                .where(Tables.CARRIER_TABLE.NAME.eq(id))
                .fetchOne();
    }

    @Override
    public CarrierTableRecord save(CarrierTableRecord entity) {
        var carrier = context.newRecord(Tables.CARRIER_TABLE, entity);
        carrier.store();
        return carrier;
    }

    @Override
    public CarrierTableRecord update(CarrierTableRecord entity) {
        var carrier = context.newRecord(Tables.CARRIER_TABLE, entity);
        carrier.update();
        return carrier;
    }

    @Override
    public void delete(String id) {
        context
                .deleteFrom(Tables.CARRIER_TABLE)
                .where(Tables.CARRIER_TABLE.NAME.eq(id))
                .execute();
    }

    @Override
    public boolean exist(String id) {
        var count = Objects.requireNonNullElse(context
                .selectCount()
                .from(Tables.CARRIER_TABLE)
                .where(Tables.CARRIER_TABLE.NAME.eq(id))
                .fetchOne(0, Integer.class), 0);
        return count > 0;
    }
}
