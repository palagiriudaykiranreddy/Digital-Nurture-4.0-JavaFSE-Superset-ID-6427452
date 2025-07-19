Microservices Architecture Output
================================

This folder summarizes the generated Spring Cloud microservices system, which includes:

1. Eureka Discovery Server
2. Account Service
3. Loan Service
4. Greet Service
5. API Gateway (with global logging filter)

Each service is in its own folder at the root of the workspace. The main files for each are:

---

Eureka Discovery Server:
- eureka-discovery-server/pom.xml
- eureka-discovery-server/src/main/java/com/cognizant/eurekadiscoveryserver/EurekaDiscoveryServerApplication.java
- eureka-discovery-server/src/main/resources/application.properties

Account Service:
- account/pom.xml
- account/src/main/java/com/cognizant/account/AccountApplication.java
- account/src/main/resources/application.properties

Loan Service:
- loan/pom.xml
- loan/src/main/java/com/cognizant/loan/LoanApplication.java
- loan/src/main/resources/application.properties

Greet Service:
- greet-service/pom.xml
- greet-service/src/main/java/com/cognizant/greetservice/GreetServiceApplication.java
- greet-service/src/main/java/com/cognizant/greetservice/controller/GreetController.java
- greet-service/src/main/resources/application.properties

API Gateway:
- api-gateway/pom.xml
- api-gateway/src/main/java/com/cognizant/apigateway/ApiGatewayApplication.java
- api-gateway/src/main/java/com/cognizant/apigateway/filter/LoggingGlobalFilter.java
- api-gateway/src/main/resources/application.properties

---

To build and run each service, use Maven commands in each respective folder:
  mvn clean install
  mvn spring-boot:run

Start Eureka first, then the other services, then the API Gateway. 