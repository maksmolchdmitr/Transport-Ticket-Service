create table purchased_tickets_table
(
    id serial primary key,
    user_login                varchar(32) not null,
    purchase_datetime         timestamp   not null,
    ticket_datetime           timestamp   not null,
    ticket_seat_number        int         not null,
    ticket_price              decimal     not null,
    route_departure           varchar(32) not null,
    route_arrival             varchar(32) not null,
    carrier_name              varchar(32) not null,
    route_duration_in_minutes int         not null,
    route_seat_count          int         not null
);