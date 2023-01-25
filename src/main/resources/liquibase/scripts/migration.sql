--liquibase formatted sql
--changeset eradomskaya:1

CREATE TABLE socks
(
    id   SERIAL PRIMARY KEY ,
    colour varchar(255) ,
    cotton_part int check ( cotton_part > 0 and cotton_part <= 100 ) ,
    quantity int,
    date timestamp,
    type varchar(48)
);


