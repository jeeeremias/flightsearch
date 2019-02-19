# flightsearch
Project specification: https://github.com/tegraoss/desafio-tegra

## Build
```
mvn clean package
```

## Unit tests
```
mvn test
```

## Start
* You can run it in docker:
```
docker build -f Dockerfile -t flightsearch .
docker run -p 8080:8080 flightsearch
```

* You can run it using maven
```
mvn spring-boot:run
```
## Endpoint
API documentation: https://github.com/jeeeremias/flightsearch/blob/master/flightsearch-swagger.yaml

- Access: https://editor.swagger.io/
- File -> Import File
- Upload the .yaml file above
