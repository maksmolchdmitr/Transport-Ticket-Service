/*
 * This file is generated by jOOQ.
 */
package maks.molch.dmitr.core.jooq.tables.pojos;


import jakarta.validation.constraints.Size;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.processing.Generated;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.time.LocalDateTime;


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
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class PurchasedTickets implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String userLogin;
    private Integer ticketId;
    private LocalDateTime purchaseDatetime;

    public PurchasedTickets() {
    }

    public PurchasedTickets(PurchasedTickets value) {
        this.id = value.id;
        this.userLogin = value.userLogin;
        this.ticketId = value.ticketId;
        this.purchaseDatetime = value.purchaseDatetime;
    }

    @ConstructorProperties({"id", "userLogin", "ticketId", "purchaseDatetime"})
    public PurchasedTickets(
            @Nullable Integer id,
            @NotNull String userLogin,
            @NotNull Integer ticketId,
            @NotNull LocalDateTime purchaseDatetime
    ) {
        this.id = id;
        this.userLogin = userLogin;
        this.ticketId = ticketId;
        this.purchaseDatetime = purchaseDatetime;
    }

    /**
     * Getter for <code>PURCHASED_TICKETS.ID</code>.
     */
    @Nullable
    public Integer getId() {
        return this.id;
    }

    /**
     * Setter for <code>PURCHASED_TICKETS.ID</code>.
     */
    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    /**
     * Getter for <code>PURCHASED_TICKETS.USER_LOGIN</code>.
     */
    @jakarta.validation.constraints.NotNull
    @Size(max = 32)
    @NotNull
    public String getUserLogin() {
        return this.userLogin;
    }

    /**
     * Setter for <code>PURCHASED_TICKETS.USER_LOGIN</code>.
     */
    public void setUserLogin(@NotNull String userLogin) {
        this.userLogin = userLogin;
    }

    /**
     * Getter for <code>PURCHASED_TICKETS.TICKET_ID</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Integer getTicketId() {
        return this.ticketId;
    }

    /**
     * Setter for <code>PURCHASED_TICKETS.TICKET_ID</code>.
     */
    public void setTicketId(@NotNull Integer ticketId) {
        this.ticketId = ticketId;
    }

    /**
     * Getter for <code>PURCHASED_TICKETS.PURCHASE_DATETIME</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public LocalDateTime getPurchaseDatetime() {
        return this.purchaseDatetime;
    }

    /**
     * Setter for <code>PURCHASED_TICKETS.PURCHASE_DATETIME</code>.
     */
    public void setPurchaseDatetime(@NotNull LocalDateTime purchaseDatetime) {
        this.purchaseDatetime = purchaseDatetime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final PurchasedTickets other = (PurchasedTickets) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        } else if (!this.id.equals(other.id))
            return false;
        if (this.userLogin == null) {
            if (other.userLogin != null)
                return false;
        } else if (!this.userLogin.equals(other.userLogin))
            return false;
        if (this.ticketId == null) {
            if (other.ticketId != null)
                return false;
        } else if (!this.ticketId.equals(other.ticketId))
            return false;
        if (this.purchaseDatetime == null) {
            if (other.purchaseDatetime != null)
                return false;
        } else if (!this.purchaseDatetime.equals(other.purchaseDatetime))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.userLogin == null) ? 0 : this.userLogin.hashCode());
        result = prime * result + ((this.ticketId == null) ? 0 : this.ticketId.hashCode());
        result = prime * result + ((this.purchaseDatetime == null) ? 0 : this.purchaseDatetime.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PurchasedTickets (");

        sb.append(id);
        sb.append(", ").append(userLogin);
        sb.append(", ").append(ticketId);
        sb.append(", ").append(purchaseDatetime);

        sb.append(")");
        return sb.toString();
    }
}
