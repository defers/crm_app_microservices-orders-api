DROP TABLE currency;

CREATE TABLE currency
(
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(50) not null,
    code VARCHAR(5)
);