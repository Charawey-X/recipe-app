SET MODE postgres

CREATE DATABASE recipe_app OWNER x;

\c recipe_app;

CREATE TABLE recipes (id SERIAL PRIMARY KEY, title VARCHAR, prepTime INTEGER, cookTime INTEGER, servings INTEGER, ingredients VARCHAR, directions VARCHAR, postedBy VARCHAR);

\c

CREATE DATABASE recipe_app_test WITH TEMPLATE recipe_app OWNER x;