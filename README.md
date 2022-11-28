# Movie storage application
REST application for storing information about movies
## Prerequisites
Java 17
## Getting Started
- Clone: https://github.com/alter3ego/movie-storage
- Console run: `java -jar movie-storage-0.0.1-SNAPSHOT.jar`
## Important information
- Log-file located in the folder `src/main/resources/log` 
- Reference for actuator: `http://localhost:9090/actuator`
- Reference for openapi: `http://localhost:9090/actuator/openapi`
- Reference for swagger-ui: `http://localhost:9090/actuator/swagger-ui` 
## Request example HTTP IDEA Client
### For GET
GET movies by year
```
GET http://localhost:8080/movie-api/v1/movies/year/2023
```
GET movies by title
```
GET http://localhost:8080/movie-api/v1/movies/title/Boys
```
GET movies by type (available values are [FULL, SHORT, SERIAL])
```
GET http://localhost:8080/movie-api/v1/movies/type/FULL
```
GET movies by genre
```
GET http://localhost:8080/movie-api/v1/movies/genre/drama
```

### For POST
Single movie save
``` 
POST http://localhost:8080/movie-api/v1/add/movie
Content-Type: application/json

  {
    "title": "Movie Title",
    "description": "Title Description",
    "type": "FULL",
    "genre": "crime, drama",
    "releaseDate": "2023-09-14"
  }
```
Save many movies
```
POST http://localhost:8080/movie-api/v1/add/movies
Content-Type: application/json

  [
  {
    "title": "Movie Title 1",
    "description": "Title Description 1",
    "type": "FULL",
    "genre": "drama",
    "releaseDate": "2023-09-15"
  },
   {
    "title": "Movie Title 2",
    "description": "Title Description 2",
    "type": "SHORT",
    "genre": "drama",
    "releaseDate": "2023-09-16"
  },
   {
    "title": "Movie Title 3",
    "description": "Title Description 3",
    "type": "SERIAL",
    "genre": "action",
    "releaseDate": "2023-09-11"
  }
  ]
```