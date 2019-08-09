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
  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
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

INSERT INTO public.trader (first_name,last_name,dob,country,email) VALUES
('Walt','Disney','1901-12-01','USA',' walt@disney.com')
,('Hugh','Hefner','1926-04-09','USA','hh@playboy.com')
,('Stringer','Strong','1981-01-09','CA','string@stringo.com')
;
INSERT INTO public.account (trader_id,amount) VALUES
(1,9999941792)
,(2,9999979628)
,(3,9999915578);

INSERT INTO public.quote (ticker,last_price,bid_price,bid_size,ask_price,ask_size,created_at) VALUES
('GOLD',18.075,18.08,200,18.15,100,'2019-08-08 13:17:39.529')
,('RBC',74.375,0,0,74.42,100,'2019-08-08 13:17:39.529')
,('BMO',72.67,0,0,72.52,100,'2019-08-08 13:17:39.529')
,('BNS',52.625,0,0,0,0,'2019-08-08 13:17:39.529')
,('VALE',11.945,11.93,300,11.95,900,'2019-08-08 13:17:39.529')
,('FB',189.215,173,100,189.22,100,'2019-08-08 13:37:58.111')
,('MSFT',138.1,135.5,100,138.49,100,'2019-08-08 13:38:15.621')
,('PBR',14.53,14.52,200,14.54,300,'2019-08-08 13:38:15.621')
;

INSERT INTO public.security_order (account_id,status,ticker,"size",price,notes) VALUES
(3,'1','PBR',100,14.54,NULL)
,(2,'1','PBR',100,14.54,NULL)
,(1,'1','PBR',100,14.54,NULL)
,(1,'1','FB',100,189.18,NULL)
,(1,'1','FB',100,189.18,NULL)
,(1,'1','FB',100,189.18,NULL)
,(2,'1','FB',100,189.18,NULL)
,(3,'1','MSFT',100,138.28,NULL)
,(3,'1','MSFT',100,138.28,NULL)
,(3,'1','MSFT',100,138.28,NULL)
;
INSERT INTO public.security_order (account_id,status,ticker,"size",price,notes) VALUES
(3,'1','MSFT',100,138.28,NULL)
,(3,'1','MSFT',100,138.28,NULL)
,(3,'1','MSFT',100,138.28,NULL)
;



