create table categories
(
    id         bigserial primary key,
    title      varchar(50),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into categories(title)
values ('food'),
       ('guitars');

create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       int,
    category_id bigint references categories (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);
insert into products(title, price, category_id)
values ('Milk', 80, 1),
       ('Bread', 25, 1),
       ('Cheese', 300, 1);

create table users
(
    id         bigserial primary key,
    username   varchar(50) unique not null,
    password   varchar(255)       not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);
insert into users (username, password)
values ('Kirk', '$2a$12$I1xPezUlvzjBF7wdgiFsFOkpTUBLlIZ0BznV3oU2UAk0LRaZQe2MW'),
       ('Pike', '$2a$12$I1xPezUlvzjBF7wdgiFsFOkpTUBLlIZ0BznV3oU2UAk0LRaZQe2MW');

create table roles
(
    id         bigserial primary key,
    name       varchar(50),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);
insert into roles(name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

create table users_roles
(
    user_id    bigint not null references users (id),
    role_id    bigint references roles (id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (user_id, role_id)
);
insert into users_roles(user_id, role_id)
values (1, 1),
       (2, 2);

create table orders
(
    id      bigserial primary key,
    user_id bigint references users (id)
);

create table order_items
(
    product_id bigint references products (id),
    amount     int,
    order_id   bigint references orders (id)
);



