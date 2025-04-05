-- create enum (before start app)
CREATE TYPE role_enum AS ENUM ('USER', 'ADMIN');

-- insert users
INSERT INTO users (id, username, password, role)
VALUES (1, 'user', '$2b$12$ynz44NT1yoN2noiKBZ9AgegOnzJmqO7szTx51TquB9LrGDu7lKHte', 'USER');

INSERT INTO users (id, username, password, role)
VALUES (2, 'admin', '$2b$12$Y7FpBzqwKJ1qNXERo0FNbuyhN9YpHaOQPOlS5H1Qkw53vKDEjAwuO', 'ADMIN');