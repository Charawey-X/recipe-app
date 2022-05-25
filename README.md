# Recipe App
By; Yvonne Charawe,Violet Auko,Ymelda Monari,Aggrey Odinga,Peter Karongo



## Table of contents
+ [Description](#Description)
+ [Project resources](#project-resources)
+ [Setup/Installation Requirements](#setupinstallation-requirements)
+ [Technologies used](#technologies-used)
+ [Contact Information](#contact-information)
+ [Copyright and License](#copyright-and-license-information)


## Description
This is an application that allows a user to create, delete and modify an existing recipe.

## Setup/Installation Requirements
+ Go to the projects [repository]
+ Clone the project
  git clone 'https://github.com/Charawey-X/recipe-app'
+ Install gradle
sdk install gradle 7.4.2
+ Install java
sdk install java
+ Open the directory in terminal
+ Move to main
cd build/classes/java/main
+ Run the following command to execute the Terminal-java application
java App
#### To recreate database:

1.Launch postgres

2.Type in psql -U (username) then run the following
- CREATE DATABASE recipe_app;
- \c recipe_app;

- CREATE TABLE recipes (id serial PRIMARY KEY, title varchar, preptime INT, cooktime INT, servings varchar,ingredients varchar, directions varchar, postedby varchar);
- CREATE DATABASE recipe_test WITH TEMPLATE recipe;


## Technologies used
+ IntelliJ IDEA
+ Java
+ Gradle
+ Heroku
+ Postgres



## Contact information
+ Yvonne Charawe : `yvonne.charawe@student.moringaschool.com`
+ Violet Auko : `violet.auko@student.moringaschool.com`
+ Ymelda Monari : `monaryymelda@gmail.com`
+ Peter Karongo : `peter.karongo@student.moringaschool.com`
+ Aggrey Odinga : `aggrey.odinga@student.moringaschool.com`

## Copyright and license information

Copyright (c) 2022 [click here to view license](LICENSE)
