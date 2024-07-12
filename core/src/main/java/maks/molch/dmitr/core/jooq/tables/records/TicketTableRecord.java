/*
 * This file is generated by jOOQ.
 */
package maks.molch.dmitr.core.jooq.tables.records;


import java.beans.ConstructorProperties;
import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.annotation.processing.Generated;

import maks.molch.dmitr.core.jooq.tables.TicketTable;

import org.jetbrains.annotations.NotNull;
import org.jooq.Record3;
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
public class TicketTableRecord extends UpdatableRecordImpl<TicketTableRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>TICKET_TABLE.ROUTE_ID</code>.
     */
    public void setRouteId(@NotNull Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>TICKET_TABLE.ROUTE_ID</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Integer getRouteId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>TICKET_TABLE.DATE_AND_TIME</code>.
     */
    public void setDateAndTime(@NotNull LocalDateTime value) {
        set(1, value);
    }

    /**
     * Getter for <code>TICKET_TABLE.DATE_AND_TIME</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public LocalDateTime getDateAndTime() {
        return (LocalDateTime) get(1);
    }

    /**
     * Setter for <code>TICKET_TABLE.SEAT_NUMBER</code>.
     */
    public void setSeatNumber(@NotNull Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>TICKET_TABLE.SEAT_NUMBER</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Integer getSeatNumber() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>TICKET_TABLE.PRICE</code>.
     */
    public void setPrice(@NotNull BigInteger value) {
        set(3, value);
    }

    /**
     * Getter for <code>TICKET_TABLE.PRICE</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public BigInteger getPrice() {
        return (BigInteger) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Record3<Integer, LocalDateTime, Integer> key() {
        return (Record3) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TicketTableRecord
     */
    public TicketTableRecord() {
        super(TicketTable.TICKET_TABLE);
    }

    /**
     * Create a detached, initialised TicketTableRecord
     */
    @ConstructorProperties({ "routeId", "dateAndTime", "seatNumber", "price" })
    public TicketTableRecord(@NotNull Integer routeId, @NotNull LocalDateTime dateAndTime, @NotNull Integer seatNumber, @NotNull BigInteger price) {
        super(TicketTable.TICKET_TABLE);

        setRouteId(routeId);
        setDateAndTime(dateAndTime);
        setSeatNumber(seatNumber);
        setPrice(price);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised TicketTableRecord
     */
    public TicketTableRecord(maks.molch.dmitr.core.jooq.tables.pojos.TicketTable value) {
        super(TicketTable.TICKET_TABLE);

        if (value != null) {
            setRouteId(value.getRouteId());
            setDateAndTime(value.getDateAndTime());
            setSeatNumber(value.getSeatNumber());
            setPrice(value.getPrice());
            resetChangedOnNotNull();
        }
    }
}
