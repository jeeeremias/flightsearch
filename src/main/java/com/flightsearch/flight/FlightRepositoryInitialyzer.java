package com.flightsearch.flight;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightsearch.flight.model.Flight;
import com.opencsv.CSVReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Lazy
@Configuration
public class FlightRepositoryInitialyzer {

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private final ObjectMapper mapper = new ObjectMapper();

    @Bean
    FlightRepository initFlightRepository() throws IOException, ParseException {
        List<Flight> flights = new ArrayList<>();

        FileReader fileReader = new FileReader("uberair.csv");
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
            flight.setPrice(Long.valueOf(csvDataLine[6]));
            flights.add(flight);
        }

        List<Map<String, String>> jsonData =
                mapper.readValue(new File("99planes.json"), new TypeReference<Map<String, String>>(){});
        for (Map<String, String> jsonDataLine : jsonData) {
            flight = new Flight();
            flight.setFlightNumber(jsonDataLine.get("voo"));
            flight.setAirportFrom(jsonDataLine.get("origem"));
            flight.setAirportTo(jsonDataLine.get("destino"));
            flight.setCompany("99Planes");
            flight.setDeparture(getDateFromString(jsonDataLine.get("data_saida"), jsonDataLine.get("saida")));
            flight.setArrival(getDateFromString(jsonDataLine.get("data_saida"), jsonDataLine.get("chegada")));
            flight.setPrice(Long.valueOf(jsonDataLine.get("valor")));
            flights.add(flight);
        }
        return new InMemoryFlightRepository(flights);
    }

    private Date getDateFromString(String dateString, String timeString) throws ParseException {
        return dateFormat.parse(dateString + " " + timeString);
    }
}
