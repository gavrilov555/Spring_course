DROP TABLE products IF EXISTS;
CREATE TABLE IF NOT EXISTS products (id bigserial, cost int, title VARCHAR(255), PRIMARY KEY (id));
INSERT INTO products (title, cost) VALUES ('Milk', 45), ('Chocolate', 190), ('Orange', 50);

DROP TABLE IF EXISTS clients CASCADE;
CREATE TABLE clients (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO clients (name) VALUES
('Max'),
('Tom');

DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders (id bigserial PRIMARY KEY, title VARCHAR(255), clients_id bigint REFERENCES clients (id));
INSERT INTO products (title, clients_id) VALUES
('Milk', 1),
('Chocolate', 2),
('Orange', 1);

