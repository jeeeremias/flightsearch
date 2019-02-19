package com.flightsearch.flight;

import com.flightsearch.flight.model.Flight;
import com.flightsearch.flight.util.FlightTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Lazy
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;

    }

    public List<Flight> getDirectFlights(final String airportFrom, String airportTo, final Date dateTo) {
        return flightRepository.getFlightsFromInTimeInterval(airportFrom, airportTo, dateTo);
    }

    public List<LinkedList<Flight>> getIndirectFlights(final String airportFrom, String airportTo, final Date date) {
        Date dateFrom = FlightTimeUtils.setFirstTimeOfTheDay(date);
        Date dateTo = FlightTimeUtils.setLastTimeOfTheDay(date);
        List<LinkedList<Flight>> allFlights = new ArrayList<>();

        List<Flight> flights = flightRepository.getFlightsFromInTimeInterval(airportFrom, dateFrom, dateTo);
        List<Flight> indirectFlights = flights.stream()
                .filter(f -> !f.getAirportTo().equals(airportTo))
                .collect(Collectors.toList());

        LinkedList<Flight> indirectFlight;
        for (Flight flight : indirectFlights) {
            List<Flight> flightsEnd = flightRepository.getFlightsFromAndToInTimeInterval(flight.getAirportTo(),
                    airportTo, flight.getArrival(), FlightTimeUtils.add12Hours(flight.getArrival()));
            for (Flight flightEnd : flightsEnd) {
                indirectFlight = new LinkedList<>();
                indirectFlight.add(flight);
                indirectFlight.add(flightEnd);
                allFlights.add(indirectFlight);
            }
        }
        return allFlights;
    }
}
