create table products
(
    id         bigserial primary key,
    title      varchar(255),
    price      numeric(8, 2) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into products (title, price)
values ('Milk', 100.20),
       ('Bread', 80.50),
       ('Cheese', 90.99),
       ('Ice cream', 100.80),
       ('Olivia oil', 500.99);

create table orders
(
        id           bigserial primary key,
        username     varchar(255)  not null,
        name         varchar(255),
        surname      varchar(255),
        total_price  numeric(8, 2) not null,
        order_status varchar(255)  not null,
        address      varchar(255),
        street       varchar(255),
        house        varchar(255),
        flat         varchar(255),
        city         varchar(255),
        postal_code  varchar(255),
        phone        varchar(255),
        country_code varchar(255),
        district  varchar(255),
        created_at   timestamp default current_timestamp,
        updated_at   timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint not null references products (id),
    order_id          bigint not null references orders (id),
    quantity          int    not null,
    price_per_product numeric(8, 2)    not null,
    price             numeric(8, 2)    not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);

insert into orders (username, total_price,order_status, address,phone,surname,name,street,house,flat,city,postal_code,country_code,district)
values ('bob', 200.00,1,'Адрес_1', '12345','Иванов','Иван','Ленина','5','25','Екатеринбург','555015','RUS', 'Свердловская область'),
('bob', 200.00,2,'Адрес_2', '12345','Иванов','Иван','Ленина','5','25','Екатеринбург','555015','RUS', 'Свердловская область');


insert into order_items (product_id, order_id, quantity, price_per_product, price)
values (1, 1, 2, 100.20, 200.40),
(1, 2, 2, 100.20, 200.40);











