package com.flightsearch.airport;

import java.util.List;
import java.util.Optional;

public interface AirportRepository {

    List<Airport> getAll();
    Optional<Airport> getByAirport(String name);
}
