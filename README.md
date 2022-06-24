# Running calculator with Karyon and RxNetty

## Required

1. Java 8 / Java 11

2. Install and configure Docker

## Starting

1. Clone this project
2. Open terminal and go until the project folder
3. Run: docker build -t calculator .
4. Run: docker run --name=calculator -d -p 8888:8888 calculator
 > if you want to change the project port, go to CalculatorApplicationRunner file, Dockerfile and change the port on run the calculator container

### Endpoints
The endpoints for the calculator is set by Get method and available on *http://localhost:8080/calculate*


For calculate is necessary provide values for: *value1*, *value2* and *operation*

- SUM

` http://localhost:8888/calculate?value1=<value>&value2=<value>&operation=SUM`

- SUB

`http://localhost:8888/calculate?value1=<value>&value2=<value>&operation=SUB`

- DIVISION

`http://localhost:8888/calculate?value1=<value>&value2=<value>&operation=DIVISION`

- MULTIPLY

`http://localhost:8888/calculate?value1=<value>&value2=<value>&operation=MULTIPLY`

- POW

`http://localhost:8888/calculate?value1=<value>&value2=<value>&operation=POW`