# Trading app REST API
## Introduction
This REST API simulates the backend of a stock brocker. By acessing its endpoints you will be able to access stock prices, register traders, and buy and sell stocks. 
It was built using java8, SpringBoot with its standard configuration of Apache  Tomcat, Hibernate and PostgreSQL as database.
The stock prices are retrived from IEX trading, and if you wish to replicate this project, you will have to create an account on their website .

## Quit Start
### Prerequisits:
 - Java 8 - git clone this project and build it as a maven project
 - Postgres Database - a working version of postgres sql. On the sql_dll folder, you will find the files to create the database and the necessary tables and views. 
 - IEX account - create an IEX account, ([https://iextrading.com/](https://iextrading.com/)) and save the public. It will be passed to the app as an environmental variable.
 - 
### Environmental variables 
Set the folowing env variables:
###### IEX 
$IEX_TOKEN  - 
 - e.g.: IEX_TOKEN=pk_44erg7x2ss41490b1lz366klt3214529
###### Database conection
 - $PGURL - database connection URL
    - e.g.:PGURL=jdbc:postgresql://localhost:5432/jrvstrading 
  -  $PGUSERNAME - database username
     - e.g.: PGUSERNAME=postgres
   - $PGPASSWORD  - database password
     - e.g.: PGPASSWORD=password
     
### Start the App
- With Maven installed, go to the base folder ( where the pom.xml file is located) and execute the command `mvn install`. It will create the jar file.
- The ext step is to run the app `java -jar target/trading-0.0.1-SNAPSHOT.jar`

## REST API Usage

### Swagger

### Quote Controller

### Tader Controller

### App controller

#
<!--stackedit_data:
eyJoaXN0b3J5IjpbMTM2MDE0NDM0NCwxNzcwNzQ4ODM2LC0xMj
YzMDU3MjYxLC0yMTQ1OTA0NzM2LDI5MTQ0OTU4NCwyMDQwMjk3
NjIyXX0=
-->