create table products
(
    id    bigserial primary key,
    title varchar(255),
    price int
);
insert into products(title, price)
values ('Milk', 80),
       ('Bread', 25),
       ('Cheese', 300);

create table users
(
    id       bigserial primary key,
    username varchar(50) unique not null,
    password varchar(255)       not null
);
insert into users (username, password)
values ('Kirk', '$2a$12$I1xPezUlvzjBF7wdgiFsFOkpTUBLlIZ0BznV3oU2UAk0LRaZQe2MW'),
       ('Pike', '$2a$12$I1xPezUlvzjBF7wdgiFsFOkpTUBLlIZ0BznV3oU2UAk0LRaZQe2MW');

create table roles
(
    id   bigserial primary key,
    name varchar(50)
);
insert into roles(name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

create table users_roles
(
    user_id bigint not null references users (id),
    role_id bigint references roles (id),
    primary key (user_id, role_id)
);
insert into users_roles(user_id, role_id)
values (1, 1),
       (2, 2);


