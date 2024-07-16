create table token_table
(
    id         serial primary key,
    token      varchar     not null unique,
    revoked    boolean     not null default false,
    type       varchar(20) not null,
    user_login varchar(32) not null,
    foreign key (user_login) references user_table (login)
);