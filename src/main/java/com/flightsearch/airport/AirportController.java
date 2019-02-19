package com.flightsearch.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/airports")
public class AirportController {

  private final AirportRepository airportRepository;

  @Autowired
  public AirportController(AirportRepository airportRepository) {
    this.airportRepository = airportRepository;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<Airport> getAirports() {
    return airportRepository.getAll();
  }
}
