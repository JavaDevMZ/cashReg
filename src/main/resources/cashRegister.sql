CREATE TABLE if not exists role
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(256)
);
INSERT INTO role(name)
VALUES('Cashier');
VALUES('SeniorCashier');
VALUES('CommodityExpert');


CREATE TABLE if not exists user
(
    id       SERIAL primary key,
    role_id  INT REFERENCES role(id),
    userame  VARCHAR(256) primary key,
    password VARCHAR(256)
);

CREATE TABLE if not exists product
(
    id          SERIAL primary key,
    name        VARCHAR(256),
    price       FLOAT,
    quantity    BIGINT
);

CREATE TABLE if not exists order
(
    id          SERIAL PRIMARY KEY,
    customer_id BIGINT,
    amount      FLOAT
);

CREATE TABLE if not exists order_item
(
    id SERIAL PRIMARY KEY,
    order_id BIGINT references order(id),
    product_id BIGINT references product(id),
    quantity BIGINT
);



