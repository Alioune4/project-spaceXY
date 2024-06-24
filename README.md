# SpaceXY
SpaceXY is an application for managing space tourism trips. It allows for managing shuttles, flights, technical revisions, and passenger reservations.

## Authors
- Papa Alioune Touré
- Louka Tokotuu-lamm
- Julien Meyer
- Guy-Noé Djoufaing-nzumgueng


## Technologies

- Java 21
- Spring Boot 3.x
- Spring Data JPA
- Spring Security
- H2 Database (in-memory)
- Springdoc OpenAPI

## Usage

The application provides several roles with different functionalities:

### Technicians

- View, create, and delete shuttles
- Modify the status of a shuttle
- Create and delete revisions for a shuttle

### Planners

- Create, read, update, and delete flights
- Notify passengers in case of flight modification or cancellation

### Passengers

- View the list of upcoming flights with the number of remaining seats
- Register for upcoming flights


## API Documentation

API documentation is available via Swagger UI.

Access the documentation by starting the application and opening the following link in your browser: http://127.0.0.1:8080/swagger-ui/index.html