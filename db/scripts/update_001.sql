CREATE TABLE IF NOT EXISTS post (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    created TIMESTAMP
);

CREATE TABLE city (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) UNIQUE
);

CREATE TABLE IF NOT EXISTS candidate (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    city_id int REFERENCES city(id),
    created TIMESTAMP
);

CREATE TABLE IF NOT EXISTS users (
     id SERIAL PRIMARY KEY,
     name VARCHAR(50),
     email VARCHAR(30) UNIQUE,
     password VARCHAR(50)
);

INSERT INTO city (name) VALUES ('Алматы');
INSERT INTO city (name) VALUES ('Астана');
INSERT INTO city (name) VALUES ('Москва');
INSERT INTO city (name) VALUES ('Санкт-Петербург');
INSERT INTO city (name) VALUES ('Киев');

