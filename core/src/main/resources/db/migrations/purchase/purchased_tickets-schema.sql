create table purchased_tickets
(
    user_login        varchar(32) not null,
    ticket_id         int         not null,
    purchase_datetime timestamp   not null,
    foreign key (user_login) references user_table (login),
    foreign key (ticket_id) references ticket_table (id)
);