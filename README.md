**Application Description**

This application, developed with Spring Boot, provides a RESTful API to retrieve and process country information sourced from "restcountries.com". It delivers a centralized method to search countries by name, filter them based on population, sort them in ascending or descending order, and apply pagination to limit the results. The application's controller efficiently handles these operations by fetching raw country data from the external API and then applying various filters as requested by the user.

The core strength of this application lies in its flexibility and ease of use. With a single endpoint, you can perform a myriad of operations to refine and fetch data exactly as you need. The integration with the "restcountries.com" API ensures up-to-date country details, while the internal filtering mechanisms make data retrieval intuitive and efficient.

Running the Application Locally
Ensure you have Java (JDK) and Maven installed on your machine.
Clone the repository to your local machine.
Navigate to the root directory of the project via terminal or command prompt.
Run the command mvn spring-boot:run. This will start the application.
Once the application is running, you can access it at http://localhost:8080.
Example Endpoint Usage
Using the developed endpoint to fetch country data:

Search by Country Name:
GET http://localhost:8080/countries?query=UK

Population Filter (Less than 100 million):
GET http://localhost:8080/countries?maxPopulationInMillions=100

Sort Countries (Descending):
GET http://localhost:8080/countries?order=descend

Pagination (Retrieve 2 countries):
GET http://localhost:8080/countries?limit=2

Retrieve All Countries:
GET http://localhost:8080/countries

Search for Countries starting with 'A':
GET http://localhost:8080/countries?query=A

Sort Countries (Default Ascending Order):
GET http://localhost:8080/countries?query=Germany

Filter by Population and Sort (Descending):
GET http://localhost:8080/countries?maxPopulationInMillions=200&order=descend

Search, Sort (Ascending) & Pagination:
GET http://localhost:8080/countries?query=India&order=ascend&limit=5

Combine Search, Population Filter, Sort, and Pagination:
GET http://localhost:8080/countries?query=UK&maxPopulationInMillions=70&order=descend&limit=3

=========================================================================

[SONAR](https://sonarcloud.io/organizations/andriiprytula15/projects)