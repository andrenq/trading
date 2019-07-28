-- psql -h PSQL_HOST -p 5432 -U postgres jrvstrading -f schema.sql
-- Drop table

DROP TABLE IF EXISTS public.trader cascade;
DROP TABLE IF EXISTS public.account cascade;
DROP TABLE IF EXISTS public.security_order cascade;
DROP TABLE IF EXISTS public.quote cascade;

-- DO NOT use double quote, e.g. public.trader."trader"
CREATE TABLE public.trader
(
  id         serial  NOT NULL,
  first_name varchar NOT NULL,
  last_name  varchar NOT NULL,
  dob        date    NOT NULL,
  country    varchar NOT NULL,
  email      varchar NOT NULL,
  CONSTRAINT trader_pk PRIMARY KEY (id)
);

CREATE TABLE public.account
(
  id        serial NOT NULL,
  trader_id int4   NOT NULL,
  amount    float8 NOT NULL,
  CONSTRAINT account_pk PRIMARY KEY (id),
  CONSTRAINT account_trader_fk FOREIGN KEY (trader_id) REFERENCES trader (id)
);

CREATE TABLE public.quote
(
  ticker     varchar NOT NULL,
  last_price float8  NOT NULL,
  bid_price  float8  NOT NULL,
  bid_size   int4    NOT NULL,
  ask_price  float8  NOT NULL,
  ask_size   int4    NOT NULL,
  CONSTRAINT quote_pk PRIMARY KEY (ticker)
);

CREATE TABLE public.security_order
(
  id         serial  NOT NULL,
  account_id int4    NOT NULL,
  status     varchar NOT NULL,
  ticker     varchar NOT NULL,
  "size"     int4    NOT NULL,
  price      float8  NULL,
  notes      varchar NULL,
  CONSTRAINT security_order_pk PRIMARY KEY (id),
  CONSTRAINT security_order_account_fk FOREIGN KEY (account_id) REFERENCES account (id),
  CONSTRAINT security_order_quote_fk FOREIGN KEY (ticker) REFERENCES quote (ticker)
);


DROP VIEW IF EXISTS public.position;

CREATE OR replace VIEW public.position
AS
  SELECT account_id,
         ticker,
         position,
         Row_number()
           over (
             ORDER BY subquery.ticker) AS row_id
  FROM  (SELECT security_order.account_id,
                security_order.ticker,
                SUM(security_order.SIZE) AS position
         FROM   security_order
         WHERE  security_order.status :: NUMERIC = 1
         GROUP  BY security_order.account_id,
                   security_order.ticker) AS subquery;

CREATE SEQUENCE hibernate_sequence START 1;