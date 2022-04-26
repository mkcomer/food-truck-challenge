# Food Truck Challenge

## Prerequisites

The only tool needed to run this solution is Docker. Please [install Docker](https://docs.docker.com/get-docker/) first.

## Build and Running code

The code can be built and run using one command: `docker-compose up --build`. Docker will build the api and database as well as run the components based on the configuration defined in the `docker-compose.yaml` file.

To tear down the containers afetr testing, run `docker-compose down --volumes`

## Tech Stack

- Docker - containerizing api and database
- Postgis (Postgres DB with Postgis extension pre-installed) - Postgis allows for location queries.
- Maven - Java dependency management 
- Java/Spring Boot
  - Lombok - auto implements getters/setters/toString methods from annotation


## Testing 

I would recommend using [Postman](https://www.postman.com/downloads/) to test the API. 

1. Create a POST request
2. Request URL: localhost:8081/api/foodtrucks/location
3. Example request body:
```json
{
    "longitude": "-122.67",
    "latitude":  "33.12"
}
```

## References

- https://postgis.net/docs/ST_Point.html
- https://blog.crunchydata.com/blog/postgis-and-the-geography-type
- https://techcommunity.microsoft.com/t5/azure-database-for-postgresql/take-your-spatial-data-analysis-to-the-next-level-with-postgis/ba-p/1124288