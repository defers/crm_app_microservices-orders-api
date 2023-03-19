CREATE TABLE currency
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) not null,
    code VARCHAR(5)
)