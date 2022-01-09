create table if not exists products (id bigserial primary key, title varchar(255), cost int);

insert into products (title, cost)
values
('Milk', 70),
('Chocolate', 150),
('Cheese', 450),
('Nut', 250),
('Orange', 65),
('Apple', 55),
('Bread', 90),
('Coffee', 600),
('Tea', 250),
('Olivia oil', 900),
('Box', 200),
('Notebook', 190000),
('Book', 850),
('Cookies', 300),
('Butter', 190),
('Cake', 500),
('Meat', 700),
('Eggs', 150),
('Pasta', 250),
('Fish', 650);

create table users (
    id         bigserial primary key,
    username   varchar(36) not null,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles (
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles (
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
       ('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);

create table orders (
    id              bigserial primary key,
    user_id         bigint not null references users (id),
    total_cost     int not null,
    address         varchar(255),
    phone           varchar(255)
);

create table order_items (
    id                      bigserial primary key,
    product_id              bigint not null references products (id),
    user_id                 bigint not null references users (id),
    order_id                bigint not null references orders (id),
    quantity                int not null,
    cost_per_product       int not null,
    cost                   int not null
)