/*
 * This file is generated by jOOQ.
 */
package maks.molch.dmitr.core.jooq.tables;


import maks.molch.dmitr.core.jooq.DefaultSchema;
import maks.molch.dmitr.core.jooq.Keys;
import maks.molch.dmitr.core.jooq.tables.TicketTable.TicketTablePath;
import maks.molch.dmitr.core.jooq.tables.UserTable.UserTablePath;
import maks.molch.dmitr.core.jooq.tables.records.PurchasedTicketsRecord;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


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
public class PurchasedTickets extends TableImpl<PurchasedTicketsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>PURCHASED_TICKETS</code>
     */
    public static final PurchasedTickets PURCHASED_TICKETS = new PurchasedTickets();

    /**
     * The class holding records for this type
     */
    @Override
    @NotNull
    public Class<PurchasedTicketsRecord> getRecordType() {
        return PurchasedTicketsRecord.class;
    }

    /**
     * The column <code>PURCHASED_TICKETS.USER_LOGIN</code>.
     */
    public final TableField<PurchasedTicketsRecord, String> USER_LOGIN = createField(DSL.name("USER_LOGIN"), SQLDataType.VARCHAR(32).nullable(false), this, "");

    /**
     * The column <code>PURCHASED_TICKETS.TICKET_ID</code>.
     */
    public final TableField<PurchasedTicketsRecord, Integer> TICKET_ID = createField(DSL.name("TICKET_ID"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>PURCHASED_TICKETS.PURCHASE_DATETIME</code>.
     */
    public final TableField<PurchasedTicketsRecord, LocalDateTime> PURCHASE_DATETIME = createField(DSL.name("PURCHASE_DATETIME"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    private PurchasedTickets(Name alias, Table<PurchasedTicketsRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private PurchasedTickets(Name alias, Table<PurchasedTicketsRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>PURCHASED_TICKETS</code> table reference
     */
    public PurchasedTickets(String alias) {
        this(DSL.name(alias), PURCHASED_TICKETS);
    }

    /**
     * Create an aliased <code>PURCHASED_TICKETS</code> table reference
     */
    public PurchasedTickets(Name alias) {
        this(alias, PURCHASED_TICKETS);
    }

    /**
     * Create a <code>PURCHASED_TICKETS</code> table reference
     */
    public PurchasedTickets() {
        this(DSL.name("PURCHASED_TICKETS"), null);
    }

    public <O extends Record> PurchasedTickets(Table<O> path, ForeignKey<O, PurchasedTicketsRecord> childPath, InverseForeignKey<O, PurchasedTicketsRecord> parentPath) {
        super(path, childPath, parentPath, PURCHASED_TICKETS);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class PurchasedTicketsPath extends PurchasedTickets implements Path<PurchasedTicketsRecord> {

        private static final long serialVersionUID = 1L;

        public <O extends Record> PurchasedTicketsPath(Table<O> path, ForeignKey<O, PurchasedTicketsRecord> childPath, InverseForeignKey<O, PurchasedTicketsRecord> parentPath) {
            super(path, childPath, parentPath);
        }

        private PurchasedTicketsPath(Name alias, Table<PurchasedTicketsRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public PurchasedTicketsPath as(String alias) {
            return new PurchasedTicketsPath(DSL.name(alias), this);
        }

        @Override
        public PurchasedTicketsPath as(Name alias) {
            return new PurchasedTicketsPath(alias, this);
        }

        @Override
        public PurchasedTicketsPath as(Table<?> alias) {
            return new PurchasedTicketsPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    @Nullable
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    @NotNull
    public List<ForeignKey<PurchasedTicketsRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CONSTRAINT_F, Keys.CONSTRAINT_F1);
    }

    private transient UserTablePath _userTable;

    /**
     * Get the implicit join path to the <code>PUBLIC.USER_TABLE</code> table.
     */
    public UserTablePath userTable() {
        if (_userTable == null)
            _userTable = new UserTablePath(this, Keys.CONSTRAINT_F, null);

        return _userTable;
    }

    private transient TicketTablePath _ticketTable;

    /**
     * Get the implicit join path to the <code>PUBLIC.TICKET_TABLE</code> table.
     */
    public TicketTablePath ticketTable() {
        if (_ticketTable == null)
            _ticketTable = new TicketTablePath(this, Keys.CONSTRAINT_F1, null);

        return _ticketTable;
    }

    @Override
    @NotNull
    public PurchasedTickets as(String alias) {
        return new PurchasedTickets(DSL.name(alias), this);
    }

    @Override
    @NotNull
    public PurchasedTickets as(Name alias) {
        return new PurchasedTickets(alias, this);
    }

    @Override
    @NotNull
    public PurchasedTickets as(Table<?> alias) {
        return new PurchasedTickets(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public PurchasedTickets rename(String name) {
        return new PurchasedTickets(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public PurchasedTickets rename(Name name) {
        return new PurchasedTickets(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public PurchasedTickets rename(Table<?> name) {
        return new PurchasedTickets(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    public PurchasedTickets where(Condition condition) {
        return new PurchasedTickets(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    public PurchasedTickets where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    public PurchasedTickets where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    public PurchasedTickets where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    @PlainSQL
    public PurchasedTickets where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    @PlainSQL
    public PurchasedTickets where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    @PlainSQL
    public PurchasedTickets where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    @PlainSQL
    public PurchasedTickets where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    public PurchasedTickets whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    public PurchasedTickets whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
