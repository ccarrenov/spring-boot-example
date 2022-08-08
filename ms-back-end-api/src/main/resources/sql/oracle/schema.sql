/* Creación de usuario si está trabajando con BD Oracle XE */
CREATE USER backend_api IDENTIFIED BY "1234567890"
DEFAULT TABLESPACE "USERS"
TEMPORARY TABLESPACE "TEMP";
ALTER USER backend_api QUOTA UNLIMITED ON USERS;
GRANT CREATE SESSION TO backend_api;
GRANT "RESOURCE" TO backend_api;
ALTER USER backend_api DEFAULT ROLE "RESOURCE";

create table user(
	id integer,
	name varchar(90) not null,
	primary key id
);
