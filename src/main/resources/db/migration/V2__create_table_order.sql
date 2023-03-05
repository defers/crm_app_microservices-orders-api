CREATE table public.order
(
    id           SERIAL primary key,
    customer_id  varchar(40) not null,
    description  varchar(250),
    sum          numeric default (0),
    deleted      boolean default (false),
    created_date TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_date TIMESTAMP WITH TIME ZONE DEFAULT NOW()
)

