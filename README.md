# Trading app REST API
## Introduction
This REST API simulates the backend of a stock brocker. By acessing its endpoints you will be able to access stock prices, register traders, and buy and sell stocks. 
It was built using java8, SpringBoot with its standard configuration of Apache  Tomcat, Hibernate and PostgreSQL as database.
The stock prices are retrived from IEX trading, and if you wish to replicate this project, you will have to create an account on their website ([https://iextrading.com/](https://iextrading.com/))

## Quit Start
### Prerequisits:
 - Java 8 - 
 - Postgres Database - 
 - IEX account


## Environmental variables 
### Trading app
##### IEX 
Create an accont at [https://iexcloud.io/](https://iexcloud.io/) and get your free token and set it as an environmental variable
$IEX_TOKEN  - 
 - e.g.: IEX_TOKEN=pk_44erg7x2ss41490b1lz366klt3214529
##### Database conection
 - $PGURL - database connection URL
    - e.g.:PGURL=jdbc:postgresql://localhost:5432/jrvstrading 
  -  $PGUSERNAME - database username
     - e.g.: PGUSERNAME=postgres
   - $PGPASSWORD  - database password
     - e.g.: PGPASSWORD=password

<!--stackedit_data:
eyJoaXN0b3J5IjpbLTQ4NDQ0MTA4NSwtMTI2MzA1NzI2MSwtMj
E0NTkwNDczNiwyOTE0NDk1ODQsMjA0MDI5NzYyMl19
-->