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
