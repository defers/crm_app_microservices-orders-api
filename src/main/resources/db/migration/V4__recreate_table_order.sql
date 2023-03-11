DROP TABLE public."order";

CREATE table public.orders
(
    id           SERIAL primary key,
    customer_id  varchar(40) not null,
    description  varchar(250),
    sum          numeric default (0),
    deleted      boolean default (false),
    created_date timestamp with time zone default now() not null,
    updated_date timestamp with time zone default now() not null
)