-- Очищаем таблицу перед загрузкой
DELETE
FROM orders;

-- Загружаем тестовые данные
INSERT INTO orders (customer_name, total_amount, status, created_at)
VALUES ('Alice', 100.00, 'NEW', NOW()),
       ('Bob', 250.00, 'PROCESSING', NOW()),
       ('Charlie', 300.00, 'SHIPPED', NOW());