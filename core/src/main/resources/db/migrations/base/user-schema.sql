create table user_table
(
    login     varchar(32) not null primary key,
    password  varchar(32) not null,
    full_name varchar(64) not null
);