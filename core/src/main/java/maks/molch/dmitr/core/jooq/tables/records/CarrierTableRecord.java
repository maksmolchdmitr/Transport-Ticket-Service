/*
 * This file is generated by jOOQ.
 */
package maks.molch.dmitr.core.jooq.tables.records;


import jakarta.validation.constraints.Size;

import java.beans.ConstructorProperties;

import javax.annotation.processing.Generated;

import maks.molch.dmitr.core.jooq.tables.CarrierTable;

import org.jetbrains.annotations.NotNull;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.19.10"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class CarrierTableRecord extends UpdatableRecordImpl<CarrierTableRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>CARRIER_TABLE.NAME</code>.
     */
    public void setName(@NotNull String value) {
        set(0, value);
    }

    /**
     * Getter for <code>CARRIER_TABLE.NAME</code>.
     */
    @jakarta.validation.constraints.NotNull
    @Size(max = 32)
    @NotNull
    public String getName() {
        return (String) get(0);
    }

    /**
     * Setter for <code>CARRIER_TABLE.PHONE</code>.
     */
    public void setPhone(@NotNull String value) {
        set(1, value);
    }

    /**
     * Getter for <code>CARRIER_TABLE.PHONE</code>.
     */
    @jakarta.validation.constraints.NotNull
    @Size(max = 20)
    @NotNull
    public String getPhone() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CarrierTableRecord
     */
    public CarrierTableRecord() {
        super(CarrierTable.CARRIER_TABLE);
    }

    /**
     * Create a detached, initialised CarrierTableRecord
     */
    @ConstructorProperties({ "name", "phone" })
    public CarrierTableRecord(@NotNull String name, @NotNull String phone) {
        super(CarrierTable.CARRIER_TABLE);

        setName(name);
        setPhone(phone);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised CarrierTableRecord
     */
    public CarrierTableRecord(maks.molch.dmitr.core.jooq.tables.pojos.CarrierTable value) {
        super(CarrierTable.CARRIER_TABLE);

        if (value != null) {
            setName(value.getName());
            setPhone(value.getPhone());
            resetChangedOnNotNull();
        }
    }
}
