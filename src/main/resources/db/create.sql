CREATE DATABASE recipe;
\c recipe;

CREATE TABLE recipes (
 id SERIAL PRIMARY KEY ,
 title VARCHAR,
 preptime INTEGER,
 cooktime INTEGER,
 servings INTEGER,
 ingredients VARCHAR,
 directions VARCHAR,
 postedby VARCHAR
);