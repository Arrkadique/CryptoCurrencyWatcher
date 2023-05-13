--liquibase formatted sql
--changeset User:1

create table users
(
    id bigserial primary key,
    username varchar not null,
    registration_price decimal not null
);

create table coins
(
    id bigserial primary key,
    symbol varchar,
    price decimal not null
);