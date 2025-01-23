# Card Cost API

## *High level description and requirements*

For the purposes of this task, a Java backend application is created
which accepts POST requests on localhost /api/payment-cards-cost, includes 
the payment card number to be searched for.

The response to this request is a JSON object including the country iso2 code
and the cost.

To determine the cost, an additional API is created, namely /api/clearing-costs which includes in its body
the country iso2 code and the cost to be configured.

These are saved in a MySQL database, cardcostapi, and the information is saved at clearing_cost_matrix table.


## *Development Environment and Setup Installations*

1. [IDE Intellij](https://www.jetbrains.com/idea/download/?section=windows)
2. [JAVA version "17.0.12" 2024-07-16 LTS](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
3. Spring boot 3.4.1
4. [Gradle 8.12](https://docs.gradle.org/8.12/release-notes.html)
5. [Git](https://git-scm.com/downloads)
6. [MySQL 8.0.40](https://dev.mysql.com/downloads/)


## Compile and run

After cloning the repository to your system
- git clone https://github.com/apagiabl/cardcostapi

then update the database configuration as shown in the following section.

Open a new terminal and in the project's directory:
- compile: .\gradlew clean build
- run: java -jar .\build\libs\cardcostapi-0.0.1-SNAPSHOT.jar


## *Configuration*

### *Database*
The sql data file contains the database and sql table creation. It is placed under resources directory.
Before running the tests, a successful connection to the database is needed along the
proper credentials.

These must be configured in application.properties file. Specifically, update accordingly:

- spring.datasource.username=</YOUR MYSQL USERNAME>
- spring.datasource.password=</YOUR MYSQL PASSWORD>


## *Testing*

Testing is held initially via postman. Then mock test are created, where we mock the 
behaviour of the two APIs.

- ClearingCostControllerTests can be updated to include any country and cost test of your choice. 
  - For the moment we test that we can create and get for the API, as well as save and get from SQL.
  The controller however, includes all CRUD operations.

- PaymentCostControllerTests includes again a save operation for the country code to be found in the database and its cost.


## *Future Work and limitations*

*Database*
- The database table includes a limit in the country code column of two letters. That said the "Others" cost as this is 
explained in the original description is not supported in the database currently.
- Add Flyway migrations for each default database schema construction, additions and role back.

*REST API costs*
- The clearing costs repository can be enhanced to include the "Others" costs, in the function call to findByCountryCode which now returns an exception.

*REST API payments*
- Validation needs further improvements; i.e. we need to check if the card number is between 8-19 digits in getCardCountry.
- A new mechanism is needed in place to be able to handle multiple requests. eg ExecutorService

*Security concerns*
- Most probably, since we exchange sensitive data, an authentication and authorization mechanism is needed. For instance, we may have users and roles that can access the API.

*Docker*
- It is apparent, that an automated procedure is needed for development and deployment. This is achieved via Docker. However, did not find the time to properly finalise this task.


