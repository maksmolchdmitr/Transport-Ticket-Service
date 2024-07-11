create table ticket_table
(
    route_id      int         not null,
    date_and_time timestamp   not null,
    seat_number   int         not null,
    price         decimal     not null,
    user_login    varchar(32) not null,
    foreign key (route_id) references route_table (id),
    foreign key (user_login) references user_table (login),
    constraint one_user_one_seat unique (user_login, seat_number),
    constraint unique_ticket unique (route_id, date_and_time, seat_number)
);