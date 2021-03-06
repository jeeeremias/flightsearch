package com.flightsearch.flight;

import com.flightsearch.flight.model.Flight;
import com.flightsearch.flight.model.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FlightController {

    private final FlightService flightService;
    private final DateFormat dateFormat;
    private final DateFormat requestDateFormat;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
        this.requestDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @GetMapping(value = "/flights", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ResponseDTO> getFlights(@RequestParam String airportFrom,
        @RequestParam String airportTo, @RequestParam String date)
        throws ParseException {
        Date dateFlight = requestDateFormat.parse(date);
        List<Flight> directFlights = flightService.getDirectFlights(airportFrom,
            airportTo,dateFlight);

        List<LinkedList<Flight>> indirectFlights = flightService.getIndirectFlights(airportFrom,
            airportTo, dateFlight);

        List<ResponseDTO> response = new ArrayList<>();
        for (Flight directFlight : directFlights) {
            response.add(new ResponseDTO(directFlight, dateFormat));
        }

        for (LinkedList<Flight> indirectFlight : indirectFlights) {
            response.add(new ResponseDTO(indirectFlight, dateFormat));
        }
        return response.stream()
            .sorted((f1, f2) -> f1.chegada.compareTo(f2.chegada))
            .collect(Collectors.toList());
    }
}
