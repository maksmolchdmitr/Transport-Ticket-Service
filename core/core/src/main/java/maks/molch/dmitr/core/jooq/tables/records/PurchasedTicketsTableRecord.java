/*
 * This file is generated by jOOQ.
 */
package maks.molch.dmitr.core.jooq.tables.records;


import jakarta.validation.constraints.Size;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

import javax.annotation.processing.Generated;

import maks.molch.dmitr.core.jooq.tables.PurchasedTicketsTable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
public class PurchasedTicketsTableRecord extends UpdatableRecordImpl<PurchasedTicketsTableRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>PURCHASED_TICKETS_TABLE.ID</code>.
     */
    public void setId(@Nullable Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>PURCHASED_TICKETS_TABLE.ID</code>.
     */
    @Nullable
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>PURCHASED_TICKETS_TABLE.USER_LOGIN</code>.
     */
    public void setUserLogin(@NotNull String value) {
        set(1, value);
    }

    /**
     * Getter for <code>PURCHASED_TICKETS_TABLE.USER_LOGIN</code>.
     */
    @jakarta.validation.constraints.NotNull
    @Size(max = 32)
    @NotNull
    public String getUserLogin() {
        return (String) get(1);
    }

    /**
     * Setter for <code>PURCHASED_TICKETS_TABLE.TICKET_ID</code>.
     */
    public void setTicketId(@NotNull Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>PURCHASED_TICKETS_TABLE.TICKET_ID</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Integer getTicketId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>PURCHASED_TICKETS_TABLE.PURCHASE_DATETIME</code>.
     */
    public void setPurchaseDatetime(@NotNull LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>PURCHASED_TICKETS_TABLE.PURCHASE_DATETIME</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public LocalDateTime getPurchaseDatetime() {
        return (LocalDateTime) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PurchasedTicketsTableRecord
     */
    public PurchasedTicketsTableRecord() {
        super(PurchasedTicketsTable.PURCHASED_TICKETS_TABLE);
    }

    /**
     * Create a detached, initialised PurchasedTicketsTableRecord
     */
    @ConstructorProperties({ "id", "userLogin", "ticketId", "purchaseDatetime" })
    public PurchasedTicketsTableRecord(@Nullable Integer id, @NotNull String userLogin, @NotNull Integer ticketId, @NotNull LocalDateTime purchaseDatetime) {
        super(PurchasedTicketsTable.PURCHASED_TICKETS_TABLE);

        setId(id);
        setUserLogin(userLogin);
        setTicketId(ticketId);
        setPurchaseDatetime(purchaseDatetime);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised PurchasedTicketsTableRecord
     */
    public PurchasedTicketsTableRecord(maks.molch.dmitr.core.jooq.tables.pojos.PurchasedTicketsTable value) {
        super(PurchasedTicketsTable.PURCHASED_TICKETS_TABLE);

        if (value != null) {
            setId(value.getId());
            setUserLogin(value.getUserLogin());
            setTicketId(value.getTicketId());
            setPurchaseDatetime(value.getPurchaseDatetime());
            resetChangedOnNotNull();
        }
    }
}