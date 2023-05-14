--liquibase formatted sql
--changeset User:1

create table if not exists users
(
    id bigserial primary key,
    username varchar not null,
    symbol varchar not null,
    price float not null
);

create table if not exists coins
(
    id bigserial primary key,
    symbol varchar not null,
    price float not null
);