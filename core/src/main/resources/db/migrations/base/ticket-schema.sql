create table ticket_table
(
    route_id      int         not null,
    date_and_time timestamp   not null,
    seat_number   int         not null,
    price         decimal     not null,
    foreign key (route_id) references route_table (id),
    constraint unique_ticket unique (route_id, date_and_time, seat_number)
);