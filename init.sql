CREATE USER ecom WITH ENCRYPTED PASSWORD 'ecom';

CREATE DATABASE settlement_service OWNER ecom;
\connect settlement_service
ALTER SCHEMA public OWNER TO ecom;
GRANT ALL PRIVILEGES ON SCHEMA public TO ecom;

CREATE DATABASE store_service OWNER ecom;
\connect store_service
ALTER SCHEMA public OWNER TO ecom;
GRANT ALL PRIVILEGES ON SCHEMA public TO ecom;