# Trading app REST API
## Introduction
This REST API simulates the backend of a stockbroker application. By accessing its endpoints, you will be able to retrieve stock prices, register traders, and buy and sell stocks. 
It was built using java8, SpringBoot with its standard configuration of Apache  Tomcat, Hibernate and PostgreSQL as database.
The stock prices are retrieved from IEX trading, and if you wish to replicate this project, you will have to create an account on their website.

## Quit Start
### Prerequisites:
 - Java 8 - git clone this project and build it as a maven project
 - IEX account - create an IEX account, ([https://iextrading.com/](https://iextrading.com/)) and save the public. It will be passed to the app as an environmental variable.
 - Postgres Database - a working version of Postgres SQL. On the sql_dll folder, you will find the files to create the database and the necessary tables and views. 
 -- Database `jrvstrading` ER diagram: ![image](https://drive.google.com/uc?export=view&id=1rfalg0lU3i_7MU3ZZ9VUPTVH_iVATdEd)

### Environmental variables 
Set the folowing env variables:
##### IEX 
- $IEX_TOKEN  - 
  - e.g.: export IEX_TOKEN=pk_44erg7x2ss41490b1lz366klt3214529
##### Database conection
 - $RDS_HOSTNAME - host adress
    - e.g.: export RDS_HOSTNAME=localhost
  - $RDS_DB_NAME- database name
    - e.g.: export RDS_DB_NAME=jrvstrading
  - $RDS_PORT- database port
    - e.g.: export RDS_PORT=5432
  -  $RDS_USERNAME- database username
     - e.g.: export RDS_USERNAME=postgres
   - $RDS_PASSWORD - database password
     - e.g.: export RDS_PASSWORD=password
 ### Docker
 
### Start the App
- With Maven installed, go to the base folder ( where the pom.xml file is located) and execute the command `mvn install`. It will create the jar file.
- The next step is to run the app `java -jar target/trading-0.0.1-SNAPSHOT.jar`
- You will be able to interact with the app by accessing its Http address: `http://localhost:8080/swagger-ui.html#/` or by making direct calls to its endpoints.
![image](https://drive.google.com/uc?export=view&id=1CAvd_H93slk86gjNskusAfslaLDF2BZS)

## REST API Usage

### Swagger
This project uses Swagger to help design, build, document and consume REST APIs. To learn more about Swagger go to [https://swagger.io](https://swagger.io/) 

### App controller
App controller returns a message to inform that the app is running.
#### End Points:
 - `GET /health/` :  returns the message: `I'm alive`+ `hostname`.
### Dashboard controller
 DashBoard controller list information from traders. Traders' accounts and open positions.
#### End Points:
 - `GET /portfolio/listall` : List all accounts and open positions of all traders.
 - `GET /portfolio/traderId/{traderId}`: List all accounts and open positions of a given trader.
### Order Controller
Order controller submits orders, to buy or to sell stocks. If the order can not be executed, its status will be changed to `CANCELLED` and saved on the database. If it succeeds, all the necessary transfers will be made, and the order will be saved with the status `FILLED`.

#### End Points
- `POST /order/marketOrder`  : Executes the order
### Quote Controller
Quote controller is responsible for fetching stock prices from IEX website and saving them at the database
#### End Points:
-   `GET /quote/dailylist`  : Returns the data from all stocks saved on the database.
-   `GET /quote/iex/ticker/{ticker,ticker,...,ticker}`  : Returns the data of all stocks listed from the IEX server (comma-separated list of tickers).
-   `POST /quote/tickerId/{ticker,ticker,...,ticker}`  :  Returns the data of all stocks listed from the IEX server (comma-separated list of tickers), and saves it into the database
-   `PUT /quote/`  : Manually updates the data of one stock.
-   `PUT /quote/iexMarketData`  : Updates the data of all stocks saved in the database.

### Trader Controller
Trader controller manages the traders and their accounts. You can create a new trader with a new account, add or remove funds from the existing accounts, or delete a trader. To delete traders, all their positions and accounts must be empty.

#### End Points
-   `DELETE /trader/dailyltraderId/{traderId}`  : Deletes a trader if funds on his account is zero and all positions are also zero.
-   `POST /trader/`  : Creates a new trader based on a `JSON` file
-   `POST trader/firstname/{firstname}/lastname/{lastname}/dob/{dob}/country/{country}/email/{email}`  : Creates a new trader.
-   `PUT /trader/deposit/accountId/{accountId}/amount/{amount}`  : Adds funds to an account, based on the accountID. 
-   `PUT /trader/withdraw/accountId/{accountId}/amount/{amount}`  : Removes funds from an account, based on the accountID. 
### Architecture
![image](https://drive.google.com/uc?export=view&id=1a2LvTx4cFLhOTxMN0bNlZ-PAAR4dPJbr)

-   **Data storage**  is divided into two services, PostgreSql database and IEX REST Api service. 
    - PostgreSQL is used to persist all the data on the application, and we connect to it using hibernate's JDBC. 
    - IEX REST Api service gives us real stock market data through an Http connection.
- **Controllers**  are responsible for receiving the requests and forwarding them to the services. They also describe the endpoints.
- **Services** execute all the requests sent by the controllers. They are responsible for the business logic, receiving the request, validating it and executing it by interacting with the Postgres Database and the IEX server.
- **DAOs** - data access objects are responsible for connecting with the database and IEX to retrieve and store information. In this app, hibernate is managing the connection with the database, simplifying the `DAO` files, reducing the number of lines coded and reducing the chance of error.
- **SpringBoot** framework was used in this project. It allows us to set up a production-ready setup of a Spring project, using **Apache Tomcat** as Java Servlet.

### Future Improvements
 - Auto-update of IEX data.
 - Ability to create short positions.
 - Use social security number to create unique users.
 - Add the functionality to create more than one account for each trader.
 - Add more endpoints to accept `JSON` files for all `PUT` requests.

 

<!--stackedit_data:
eyJoaXN0b3J5IjpbLTIwMzM0MTE2NCwxNjIzMzY1MDE5LC0xMz
EwMTI0ODQ1LC0xMjEwMjEyMzc1LC0xODIzNDA0ODgwLC0xODAy
MTQ5NDQxLC01NTU5MzU4MzYsMTYyMDYwMDY2NSwtMTIxMjMzNT
Y1MywxMTkzNzI0Njk0LDkzNTM2OTE3OSwxNTk2OTQxNTE2LDEz
NzE3ODg0MjAsMTE3MDIyNTg1MywxMDExNjU0NDk1LDMxMDY4ND
c2NCwtMTEzMjAxODU5LDE3NzA3NDg4MzYsLTEyNjMwNTcyNjEs
LTIxNDU5MDQ3MzZdfQ==
-->