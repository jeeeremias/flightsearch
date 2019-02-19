FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/flights-search-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java","-jar","/app.jar"]