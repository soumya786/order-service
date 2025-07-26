CREATE TABLE t_orders
(
    id BIGSERIAL PRIMARY KEY,
    order_number TEXT DEFAULT NULL,
    sku_code TEXT,
    price DECIMAL(19,2),
    quantity INT
);
