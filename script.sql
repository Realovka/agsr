-- create enum
CREATE TYPE type_enum AS ENUM ('PRESSURE', 'VOLTAGE', 'TEMPERATURE', 'HUMIDITY');
CREATE TYPE unit_enum AS ENUM ('BAR', 'VOLTAGE', 'CELSIUS_DEGREES', 'PERCENT');
CREATE TYPE role_enum AS ENUM ('USER', 'ADMIN');

-- insert users
INSERT INTO users (id, username, password, role)
VALUES (1, 'user', '$2b$12$ynz44NT1yoN2noiKBZ9AgegOnzJmqO7szTx51TquB9LrGDu7lKHte', 'USER');

INSERT INTO users (id, username, password, role)
VALUES (2, 'admin', '$2b$12$Y7FpBzqwKJ1qNXERo0FNbuyhN9YpHaOQPOlS5H1Qkw53vKDEjAwuO', 'ADMIN');