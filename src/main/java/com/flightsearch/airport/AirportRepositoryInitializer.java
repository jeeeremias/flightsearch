package com.flightsearch.airport;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Lazy
@Configuration
public class AirportRepositoryInitializer {

    private final ObjectMapper mapper = new ObjectMapper();

    private final ResourceLoader resourceLoader;

    @Autowired
    public AirportRepositoryInitializer(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    AirportRepository initAirportRepository() throws IOException {
        InputStream stream = resourceLoader
            .getResource("classpath:aeroportos.json").getInputStream();

        List<Airport> airports = mapper.readValue(stream, new TypeReference<List<Airport>>() {});

        return new InMemoryAirportRepository(airports);
    }
}
