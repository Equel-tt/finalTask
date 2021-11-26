-- CREATE DATABASE manufacture
-- ENCODING 'UTF8';

CREATE USER system_access WITH PASSWORD 'final8412';
GRANT ALL ON manufacture.public.* TO system_access;