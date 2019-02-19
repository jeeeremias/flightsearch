package com.flightsearch.airport;

import java.util.List;
import java.util.Optional;

public class InMemoryAirportRepository implements AirportRepository {

    private final List<Airport> airports;

    public InMemoryAirportRepository(List<Airport> airports) {
        this.airports = airports;
    }

    @Override
    public List<Airport> getAll() {
        return airports;
    }

    @Override
    public Optional<Airport> getByAirport(String airport) {
        return airports.stream()
                .filter(a -> a.getAeroporto().equals(airport))
                .findFirst();
    }
}
