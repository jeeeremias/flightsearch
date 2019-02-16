package com.flightsearch.flight;

import com.flightsearch.flight.model.Flight;
import com.flightsearch.flight.util.FlightTimeUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryFlightRepository implements FlightRepository {

    private final List<Flight> flights;

    public InMemoryFlightRepository(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public List<Flight> getFlightsFromInTimeInterval(String airportFrom, String airportTo, Date date) {
        Date dateFrom = FlightTimeUtils.setFirstTimeOfTheDay(date);
        Date dateTo = FlightTimeUtils.setLastTimeOfTheDay(date);

        return flights.stream()
                .filter(f -> f.getAirportFrom().equals(airportFrom)
                        && f.getAirportTo().equals(airportTo)
                        && (f.getDeparture().equals(dateFrom) || f.getDeparture().after(dateFrom))
                        && (f.getDeparture().equals(dateTo) || f.getDeparture().before(dateTo)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> getFlightsFromInTimeInterval(final String airportFrom, final Date dateFrom, final Date dateTo) {
        return flights.stream()
                .filter(f -> f.getAirportFrom().equals(airportFrom)
                        && f.getArrival().after(dateFrom)
                        && f.getArrival().before(dateTo))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> getFlightsFromAndToInTimeInterval(String airportFrom, String airportTo, Date dateDepartureFrom,
                                                          Date dateDepartureTo) {
        return flights.stream()
                .filter(f -> f.getAirportFrom().equals(airportFrom)
                        && f.getAirportTo().equals(airportTo)
                        && f.getDeparture().after(dateDepartureFrom)
                        && f.getDeparture().before(dateDepartureTo))
                .collect(Collectors.toList());
    }
}
