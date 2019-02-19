package com.flightsearch.flight;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightsearch.flight.model.Flight;
import com.opencsv.CSVReaderBuilder;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Lazy
@Configuration
public class FlightRepositoryInitialyzer {

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private final ObjectMapper mapper = new ObjectMapper();

    @Bean
    FlightRepository initFlightRepository() throws IOException, ParseException {
        List<Flight> flights = new ArrayList<>();

        FileReader fileReader = new FileReader(ResourceUtils.getFile("classpath:uberair.csv"));
        List<String[]> csvData = new CSVReaderBuilder(fileReader)
                .withSkipLines(1)
                .build()
                .readAll();
        Flight flight;
        for (String[] csvDataLine : csvData) {
            flight = new Flight();
            flight.setFlightNumber(csvDataLine[0]);
            flight.setAirportFrom(csvDataLine[1]);
            flight.setAirportTo(csvDataLine[2]);
            flight.setCompany("UberAir");
            flight.setDeparture(getDateFromString(csvDataLine[3], csvDataLine[4]));
            flight.setArrival(getDateFromString(csvDataLine[3], csvDataLine[5]));
            flight.setPrice(new BigDecimal(csvDataLine[6]));
            flights.add(flight);
        }

       JsonNode jsonData =
                mapper.readTree(ResourceUtils.getFile("classpath:99planes.json"));
        for (JsonNode jsonPlane : jsonData) {
            flight = new Flight();
            flight.setFlightNumber(jsonPlane.get("voo").asText());
            flight.setAirportFrom(jsonPlane.get("origem").asText());
            flight.setAirportTo(jsonPlane.get("destino").asText());
            flight.setCompany("99Planes");
            flight.setDeparture(getDateFromString(jsonPlane.get("data_saida").asText(), jsonPlane.get("saida").asText()));
            flight.setArrival(getDateFromString(jsonPlane.get("data_saida").asText(), jsonPlane.get("chegada").asText()));
            flight.setPrice(new BigDecimal(jsonPlane.get("valor").toString()));
            flights.add(flight);
        }
        return new InMemoryFlightRepository(flights);
    }

    private Date getDateFromString(String dateString, String timeString) throws ParseException {
        return dateFormat.parse(dateString + " " + timeString);
    }
}
