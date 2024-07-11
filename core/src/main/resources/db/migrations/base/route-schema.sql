create table route_table
(
    id                  serial primary key,
    departure           varchar(32) not null,
    arrival             varchar(32) not null,
    carrier_name        varchar(32) not null,
    duration_in_minutes int         not null,
    seat_count          int         not null,
    foreign key (carrier_name) references carrier_table (name)
);