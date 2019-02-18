package com.flightsearch.flight;

import com.flightsearch.flight.model.Flight;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static com.flightsearch.flight.FlightUtils.DATE_FORMAT;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlightServiceTest {

    private FlightService flightService;
    private FlightRepository flightRepository;

    @Before
    public void setUp() {
        flightRepository = mock(FlightRepository.class);
        flightService = new FlightService(flightRepository);
    }

    @Test
    public void getDirectFlights_shouldReturnAllFlightsSameAsDatabaseReturns() throws ParseException {
        Date dateTo = new Date();
        String bel = "BEL";
        String vcp = "VCP";
        when(flightRepository.getFlightsFromInTimeInterval(eq(bel), eq(vcp), eq(dateTo)))
                .thenReturn(FlightUtils.getMockedFlights());
        List<Flight> directFlights = flightService.getDirectFlights(bel, vcp, dateTo);
        assertEquals(FlightUtils.getMockedFlights().size(), directFlights.size());
    }
}