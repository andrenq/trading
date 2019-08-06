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
- You will be able to interact with the app by accessing its http address: `http://localhost:8080/swagger-ui.html#/` or by making direct calls to its endpoints.
![image](https://drive.google.com/uc?export=view&id=1CAvd_H93slk86gjNskusAfslaLDF2BZS)

## REST API Usage

### Swagger
This poject uses Swagger to help design, build, document and consume REST APIs. To learn more about Swagger go to [https://swagger.io](https://swagger.io/) 

### App controller
App controller returns a message to informe that the app is running.
#### End Points:
 - `GET /health/` :  returns the message `I'm alive`.
 - 
### Quote Controller
Quote controller is responsible for fetching stock prices from IEX website, and saving them at the database
#### End Points:
-   `GET /quote/dailylist`  : Returns the data from all stocks saved on the database.
-   `GET /quote/iex/ticker/{ticker,ticker,...,ticker}`  : Returns the data of all stocks listed from the IEX server (coma separated list of tickers).
-   `POST /quote/tickerId/{ticker,ticker,...,ticker}`  :  Returns the data of all stocks listed from the IEX server (coma separated list of tickers), and saves it into the database
-   `PUT /quote/`  : Manually updates the data of one stock.
-   `PUT /quote/iexMarketData`  : Updates the data of all stocks saved in the database.

### Tader Controller
This controller is used to manage the traders and their accounts. You can create a new trader with a new account, add or remove funds from the accounts, and  delete a trader. To delete traders, all their positions and accounts must be empty.

#### End Points
-   `DELETE /trader/dailyltraderId/{traderId}`  : Deletes a trader if funds on his account is zero and all positions are also zero.
-   `POST /trader/`  : Creates a new trader based on a `JSON` file
-   `POST trader/firstname/{firstname}/lastname/{lastname}/dob/{dob}/country/{country}/email/{email}`  : Creates a new trader.
-   `PUT /trader/deposit/accountId/{accountId}/amount/{amount}`  : Adds funds to a specified account
-   `PUT /trader/withdraw/accountId/{accountId}/amount/{amount}`  : Removes funds from a specified account 


 
### Dashboard controller
<!--stackedit_data:
eyJoaXN0b3J5IjpbLTIxNzIwMTIxNywxMDExNjU0NDk1LDMxMD
Y4NDc2NCwtMTEzMjAxODU5LDE3NzA3NDg4MzYsLTEyNjMwNTcy
NjEsLTIxNDU5MDQ3MzYsMjkxNDQ5NTg0LDIwNDAyOTc2MjJdfQ
==
-->