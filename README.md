#Trading app REST API
##Introduction
This REST API simulates the backend of a stock brocker. By acessing its endpoints you will be able to get stock quotes, register traders, buy and sell stocks 


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
eyJoaXN0b3J5IjpbMjA1NzgzNjQxNCwtMTI2MzA1NzI2MSwtMj
E0NTkwNDczNiwyOTE0NDk1ODQsMjA0MDI5NzYyMl19
-->