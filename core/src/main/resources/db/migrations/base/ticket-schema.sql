create table ticket_table
(
    route_id      int         not null,
    date_and_time timestamp   not null,
    seat_number   int         not null,
    price         decimal     not null,
    user_login    varchar(32) not null,
    foreign key (route_id) references route_table (id),
    foreign key (user_login) references user_table (login)
);