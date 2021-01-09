CREATE SCHEMA IF NOT EXISTS chat_schema
    AUTHORIZATION postgres;

CREATE SEQUENCE IF NOT EXISTS chat_schema.hibernate_sequence
    INCREMENT 1
    START 1
    MINVALUE 0
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE chat_schema.hibernate_sequence
    OWNER TO postgres;
CREATE TABLE IF NOT EXISTS "chat_schema"."message" (
    id UUID PRIMARY KEY,
    sender_name varchar,
    text varchar,
    send_date varchar
);

