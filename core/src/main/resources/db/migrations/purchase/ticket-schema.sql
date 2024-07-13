alter table ticket_table
    add column purchased_by varchar(32);

alter table ticket_table
    add constraint fk_ticket_table_purchased_by foreign key (purchased_by) references user_table (login);