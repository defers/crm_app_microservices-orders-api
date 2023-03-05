ALTER TABLE public.order drop column created_date;
ALTER TABLE public.order add column created_date timestamp with time zone default now() not null ;

ALTER TABLE public.order drop column updated_date;
ALTER TABLE public.order add column updated_date timestamp with time zone default now() not null ;
