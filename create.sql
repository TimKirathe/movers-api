psql

CREATE DATABASE movers_database;

\c movers_database;

CREATE TABLE invoice (
 id serial PRIMARY KEY,
 userId int,
 name varchar,
 email varchar,
 price int,
 noOfBedrooms varchar,
 amount int,
 latFrom varchar,
 longFrom varchar,
 latTo varchar,
 longTo varchar,
 date varchar
 );


 CREATE TABLE service (
 id serial PRIMARY KEY,
 noOfBedRooms varchar,
 photoLink varchar,
 price int
 );



