use flash_sale;

CREATE TABLE goods
(
id LONG,
name varchar (64),
quantity int ,
`description` VARCHAR(255),
price DECIMAL,
flash_sale_price DECIMAL
);

CREATE TABLE order_info
(
id LONG,
customer_id LONG,
goods_id LONG,
quantity int ,
total_price DECIMAL
);

