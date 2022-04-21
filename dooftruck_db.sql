create user foodtruckuser with password 'password';

create database foodtruckdb with template=template0 owner=foodtruckuser;

\c foodtruckdb;

alter default priveledges grant all on tables to foodtruckuser;

alter default priveledges grant all on sequences to foodtruckuser;