/*
 * This file is generated by jOOQ.
 */
package maks.molch.dmitr.core.jooq.tables;


import maks.molch.dmitr.core.jooq.DefaultSchema;
import maks.molch.dmitr.core.jooq.Keys;
import maks.molch.dmitr.core.jooq.tables.PurchasedTicketsTable.PurchasedTicketsTablePath;
import maks.molch.dmitr.core.jooq.tables.RouteTable.RouteTablePath;
import maks.molch.dmitr.core.jooq.tables.UserTable.UserTablePath;
import maks.molch.dmitr.core.jooq.tables.records.TicketTableRecord;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import javax.annotation.processing.Generated;
import java.math.BigInteger;
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
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class TicketTable extends TableImpl<TicketTableRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>TICKET_TABLE</code>
     */
    public static final TicketTable TICKET_TABLE = new TicketTable();

    /**
     * The class holding records for this type
     */
    @Override
    @NotNull
    public Class<TicketTableRecord> getRecordType() {
        return TicketTableRecord.class;
    }

    /**
     * The column <code>TICKET_TABLE.ID</code>.
     */
    public final TableField<TicketTableRecord, Integer> ID = createField(DSL.name("ID"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>TICKET_TABLE.ROUTE_ID</code>.
     */
    public final TableField<TicketTableRecord, Integer> ROUTE_ID = createField(DSL.name("ROUTE_ID"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>TICKET_TABLE.DATE_AND_TIME</code>.
     */
    public final TableField<TicketTableRecord, LocalDateTime> DATE_AND_TIME = createField(DSL.name("DATE_AND_TIME"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    /**
     * The column <code>TICKET_TABLE.SEAT_NUMBER</code>.
     */
    public final TableField<TicketTableRecord, Integer> SEAT_NUMBER = createField(DSL.name("SEAT_NUMBER"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>TICKET_TABLE.PRICE</code>.
     */
    public final TableField<TicketTableRecord, BigInteger> PRICE = createField(DSL.name("PRICE"), SQLDataType.DECIMAL_INTEGER(100000).nullable(false), this, "");

    /**
     * The column <code>TICKET_TABLE.PURCHASED_BY</code>.
     */
    public final TableField<TicketTableRecord, String> PURCHASED_BY = createField(DSL.name("PURCHASED_BY"), SQLDataType.VARCHAR(32), this, "");

    private TicketTable(Name alias, Table<TicketTableRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private TicketTable(Name alias, Table<TicketTableRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>TICKET_TABLE</code> table reference
     */
    public TicketTable(String alias) {
        this(DSL.name(alias), TICKET_TABLE);
    }

    /**
     * Create an aliased <code>TICKET_TABLE</code> table reference
     */
    public TicketTable(Name alias) {
        this(alias, TICKET_TABLE);
    }

    /**
     * Create a <code>TICKET_TABLE</code> table reference
     */
    public TicketTable() {
        this(DSL.name("TICKET_TABLE"), null);
    }

    public <O extends Record> TicketTable(Table<O> path, ForeignKey<O, TicketTableRecord> childPath, InverseForeignKey<O, TicketTableRecord> parentPath) {
        super(path, childPath, parentPath, TICKET_TABLE);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class TicketTablePath extends TicketTable implements Path<TicketTableRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> TicketTablePath(Table<O> path, ForeignKey<O, TicketTableRecord> childPath, InverseForeignKey<O, TicketTableRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private TicketTablePath(Name alias, Table<TicketTableRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public TicketTablePath as(String alias) {
            return new TicketTablePath(DSL.name(alias), this);
        }

        @Override
        public TicketTablePath as(Name alias) {
            return new TicketTablePath(alias, this);
        }

        @Override
        public TicketTablePath as(Table<?> alias) {
            return new TicketTablePath(alias.getQualifiedName(), this);
        }
    }

    @Override
    @Nullable
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    @NotNull
    public Identity<TicketTableRecord, Integer> getIdentity() {
        return (Identity<TicketTableRecord, Integer>) super.getIdentity();
    }

    @Override
    @NotNull
    public UniqueKey<TicketTableRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_5;
    }

    @Override
    @NotNull
    public List<UniqueKey<TicketTableRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.CONSTRAINT_5CD);
    }

    @Override
    @NotNull
    public List<ForeignKey<TicketTableRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CONSTRAINT_5C, Keys.FK_TICKET_TABLE_PURCHASED_BY);
    }

    private transient RouteTablePath _routeTable;

    /**
     * Get the implicit join path to the <code>PUBLIC.ROUTE_TABLE</code> table.
     */
    public RouteTablePath routeTable() {
        if (_routeTable == null)
            _routeTable = new RouteTablePath(this, Keys.CONSTRAINT_5C, null);

        return _routeTable;
    }

    private transient UserTablePath _userTable;

    /**
     * Get the implicit join path to the <code>PUBLIC.USER_TABLE</code> table.
     */
    public UserTablePath userTable() {
        if (_userTable == null)
            _userTable = new UserTablePath(this, Keys.FK_TICKET_TABLE_PURCHASED_BY, null);

        return _userTable;
    }

    private transient PurchasedTicketsTablePath _purchasedTicketsTable;

    /**
     * Get the implicit to-many join path to the
     * <code>PUBLIC.PURCHASED_TICKETS_TABLE</code> table
     */
    public PurchasedTicketsTablePath purchasedTicketsTable() {
        if (_purchasedTicketsTable == null)
            _purchasedTicketsTable = new PurchasedTicketsTablePath(this, null, Keys.CONSTRAINT_BE0.getInverseKey());

        return _purchasedTicketsTable;
    }

    @Override
    @NotNull
    public TicketTable as(String alias) {
        return new TicketTable(DSL.name(alias), this);
    }

    @Override
    @NotNull
    public TicketTable as(Name alias) {
        return new TicketTable(alias, this);
    }

    @Override
    @NotNull
    public TicketTable as(Table<?> alias) {
        return new TicketTable(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public TicketTable rename(String name) {
        return new TicketTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public TicketTable rename(Name name) {
        return new TicketTable(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public TicketTable rename(Table<?> name) {
        return new TicketTable(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    public TicketTable where(Condition condition) {
        return new TicketTable(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    public TicketTable where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    public TicketTable where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    public TicketTable where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    @PlainSQL
    public TicketTable where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    @PlainSQL
    public TicketTable where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    @PlainSQL
    public TicketTable where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    @PlainSQL
    public TicketTable where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    public TicketTable whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    public TicketTable whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
