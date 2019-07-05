# web-app
Open Liberty Java EE Example


Prerequisites for the project is you need to install Oracle XE database and need to have ojdbc driver to connect to the database.

Following scripts are used to create tables in the database

create table currency_detail(
currency_code varchar(5) primary key,
currency_name varchar(50) not null
);

insert into currency_detail values('INR','Indian Rupeees');

create table currency_conversion (
from_to_currency varchar(15) primary key,
conversion_rate number(4,2) not null,
updatedDate Timestamp not null
);

To run the project provide the following command from the project directory

mvn clean package liberty:run-server

Open the following URL to view the REST API in OpenAPI

http://localhost:9080/openapi/ui/
