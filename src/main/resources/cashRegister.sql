CREATE TABLE if not exists role
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(256)
);

CREATE TABLE if not exists  worker
(
    id SERIAL primary key,
    role_id INT REFERENCES role(id),
    firstName VARCHAR(256),
    lastName VARCHAR(256),
    password VARCHAR(256)
);

CREATE TABLE if not exists product
(
    id SERIAL primary key,
    name VARCHAR(256),
    price FLOAT,
    quantity BIGINT
);

CREATE TABLE if not exists order
(
    id          SERIAL PRIMARY KEY,
    customer_id BIGINT REFERENCES customer(id),
    amount      FLOAT
);

CREATE TABLE if not exists customer
(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(256),
    last_name VARCHAR(256),
    email VARCHAR(256)
)


