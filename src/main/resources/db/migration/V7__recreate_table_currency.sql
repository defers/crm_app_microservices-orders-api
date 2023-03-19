DROP TABLE currency;

CREATE TABLE currency
(
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(50) not null,
    code VARCHAR(5),
    deleted      boolean default (false),
    created_date timestamp with time zone default now() not null,
    updated_date timestamp with time zone default now() not null
);