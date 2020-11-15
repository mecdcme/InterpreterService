
# IS - InterpreterService
A JAVA REST API service to load, mapping, validate and view microdata of a generic survey.

Metadata and microdata are described according to the standard GSIM model. 

## What you’ll need
In order to build the IS2 application, your environment should fulfill the following requirements:

* A favorite text editor or IDE;
* JDK 8+; 
* Maven 3.0+;
* Mysql Server 8.0+ or PostgreSQL 9.6+ ;  


## How to build
Download and unzip the source code in your developing folder `IS_PATH`.
Before building the application you must create and popolulate the IS database.

You can create a MySQL database using the script `interpreter-create-mysql.sql` stored in the [IS_PATH/db](db/interpreter-create-mysql.sql) folder.
Also you can create a PostgreSQL database using the script `interpreter-create-postgres.sql` stored in the [IS_PATH/db](db/interpreter-create-postgres.sql) folder.

Then yo can load the metadata and the test data, using the script `interpreter-data.sql` stored in the [IS_PATH/db](db/interpreter-data.sql) folder.

The script will populate the `USER/ROLES` tables with the user:
```
Username: admin@is2.it
Password: istat
``` 


As a first step, configure the database connection in the 'application.properties' file, located in the path `[IS_PATH]/src/main/resources`:

Postgres connection
```
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres?currentSchema=interpreter&charset=UTF-8
spring.datasource.username=db_username
spring.datasource.password=db_password
spring.datasource.driverClassName=org.postgresql.Driver
```
MySQL connection
```
spring.datasource.url=jdbc:mysql://localhost:3306/interpreter?useSSL=false&useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username=db_username
spring.datasource.password=db_password
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
```

Now you can perform your first build of the application using Maven comand:
```
[IS_PATH]> mvn package
```
If the build process ends successfully, you are ready to run the application:
The application is built using the open source framework Spring Boot, which generates an 
executable jar (that can be run from the command line). Spring Boot creates a stand-alone Spring 
based Applications, with an embedded Tomcat, that you can "just run".
```
[IS_PATH]> java –jar target/intService.jar
```
or using Maven comand:
```
[IS_PATH]> mvn spring-boot:run 
```

Docker compose
```
[IS_PATH]> docker-compose up
```

## Test the application 
The Interpreter can perform a generic step defined in the sm_step_instance table for a given edition (Statistical Program cycle) of the survey, through the URL:

  http://localhost:8090/api/interpreter/dostep/{edition}/{step}
  
For the loaded data you can do:
  
1.   http://localhost:8090/api/interpreter/dostep/1/1
      Step 1 (COPY_ROWS_DATA) is carried out for edition 1 (FATT survey): the data is copied from a data collection system (mi_fatt and
       fatt_portal) to the Working data table (fatt_sintesi) after having been filtered and mapped through mapping rules (SM_RULE table).
  
2.   http://localhost:8090/api/interpreter/dostep/1/2
      Step 2 (VALIDATE_RULES) is performed for edition 1 (FATT survey): the data are "Validated" according to the validation rules defined in the table
       SM_RULE.

3.   http://localhost:8090/api/interpreter/dostep/1/3
      Step 3 (VIEW_DATA) is performed for edition 1 (FATT survey): the data are loaded and displayed on the screen.



## License
InterpreterService is EUPL-licensed
