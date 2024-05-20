CREATE TABLE if not exists "role"
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(256)
);



CREATE TABLE if not exists "user"
(
    id       BIGSERIAL,
    role_id  BIGINT REFERENCES role(id),
    username  VARCHAR(256) primary key,
    password VARCHAR(256)
);

CREATE TABLE if not exists "product"
(
    id          BIGSERIAL primary key,
    name        VARCHAR(256),
    price       FLOAT,
    quantity    BIGINT
);

CREATE TABLE if not exists "order"
(
    id          BIGSERIAL PRIMARY KEY,
    customer_id BIGINT,
    amount      FLOAT
);

CREATE TABLE if not exists "order"
(
    id             bigserial
        primary key,
    customer_id    bigint,
    amount         FLOAT,
    customer_email text,
    cashier_id     integer,
    date           timestamp
);


CREATE TABLE if not exists "order_item"
(
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT references "order"(id),
    product_id BIGINT references product(id),
    quantity BIGINT
);


insert into role(name) values ('Cashier');
insert into role(name) values ('SeniorCashier');
insert into role(name) values ('CommodityExpert')
