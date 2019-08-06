#Trading app REST API
##Introduction
This REST API simulates the backend of a stock brocker. By acessing its endpoints you will be able to access stock prices, register traders, and buy and sell stocks. 
It was built using Springboot


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
eyJoaXN0b3J5IjpbNzE0MjkwODU5LC0xMjYzMDU3MjYxLC0yMT
Q1OTA0NzM2LDI5MTQ0OTU4NCwyMDQwMjk3NjIyXX0=
-->