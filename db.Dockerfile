FROM mysql:latest

COPY ./db/interpreter-create-mysql.sql /docker-entrypoint-initdb.d/
COPY ./db/interpreter-data.sql /docker-entrypoint-initdb.d/
