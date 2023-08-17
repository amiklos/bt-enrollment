BT Code crafters Java challenge - SOLUTION


Requirements:
- JDK 11
- Maven 3

To run the app:
 mvn spring-boot:mvn

Swagger link :
- http://localhost:8080/swagger-ui/index.html#/

Test data for mock external services:
- if personal id length is > 10 new client
- if personal id length is <= 10 existing client
- mock risk score is personal id first digit * 20

