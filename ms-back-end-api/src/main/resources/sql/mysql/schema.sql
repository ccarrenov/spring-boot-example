create database backend_api;

create table user(
	id integer auto_increment,
	name varchar(90) not null,
	primary key id
);