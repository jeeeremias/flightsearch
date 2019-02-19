package com.flightsearch.airport;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Lazy
@Configuration
public class AirportRepositoryInitializer {

    private final ObjectMapper mapper = new ObjectMapper();

    @Bean
    AirportRepository initAirportRepository() throws IOException {
        return mapper.readValue(new File(getClass().getResource("/aeroportos.json").getFile()), new TypeReference<List<Airport>>(){});
    }
}
