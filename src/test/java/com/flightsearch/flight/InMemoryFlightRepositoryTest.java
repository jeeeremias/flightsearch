package com.flightsearch.flight;

import com.flightsearch.flight.model.Flight;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;

import static com.flightsearch.flight.FlightUtils.DATE_FORMAT;
import static org.junit.Assert.assertEquals;

public class InMemoryFlightRepositoryTest {

    private FlightRepository flightRepository;

    @Before
    public void setUp() throws ParseException {
        flightRepository = new InMemoryFlightRepository(FlightUtils.getMockedFlights());
    }

    @Test
    public void getFlightsFromInTimeInterval_shouldFilterAndReturnAllVCPFlights() throws ParseException {
        List<Flight> flightsFromVCP = flightRepository.getFlightsFromInTimeInterval("VCP",
                DATE_FORMAT.parse("2019-02-17 07:00:00"), DATE_FORMAT.parse("2019-02-18 23:59:59"));
        assertEquals(11, flightsFromVCP.size());
        for (Flight flight : flightsFromVCP) {
            assertEquals("VCP", flight.getAirportFrom());
        }
    }

    @Test
    public void getFlightsFromInTimeInterval_shouldFilterAndReturnAllBELFlights() throws ParseException {
        List<Flight> flightsFromBEL = flightRepository.getFlightsFromInTimeInterval("BEL",
                DATE_FORMAT.parse("2019-02-17 07:00:00"), DATE_FORMAT.parse("2019-02-18 23:59:59"));
        assertEquals(11, flightsFromBEL.size());
        for (Flight flight : flightsFromBEL) {
            assertEquals("BEL", flight.getAirportFrom());
        }
    }

    @Test
    public void getFlightsFromInTimeInterval_shouldFilterAndReturnOnlyFlightsInTimeRange() throws ParseException {
        List<Flight> flightsFromVCP = flightRepository.getFlightsFromInTimeInterval("VCP",
                DATE_FORMAT.parse("2019-02-17 09:50:00"), DATE_FORMAT.parse("2019-02-17 21:21:52"));
        assertEquals(4, flightsFromVCP.size());
        for (Flight flight : flightsFromVCP) {
            assertEquals("VCP", flight.getAirportFrom());
        }
    }

    @Test
    public void getFlightsFromInTimeInterval_shouldFilterAndReturnEmpty() throws ParseException {
        List<Flight> flightsFromVCP = flightRepository.getFlightsFromInTimeInterval("VCP",
                DATE_FORMAT.parse("2019-02-18 23:58:00"), DATE_FORMAT.parse("2019-02-19 21:21:52"));
        assertEquals(0, flightsFromVCP.size());
    }

    @Test
    public void getDayFlightsFrom_shouldFilterAndReturnOnlyFlightsFomAndTo() throws ParseException {
        List<Flight> flightsFromVCP = flightRepository.getFlightsFromInTimeInterval("VCP", "BEL",
                DATE_FORMAT.parse("2019-02-17 21:21:52"));
        assertEquals(6, flightsFromVCP.size());
        for (Flight flight : flightsFromVCP) {
            assertEquals("VCP", flight.getAirportFrom());
        }
    }

    @Test
    public void getDayFlightsFrom_shouldFilterAndReturnEmpty() throws ParseException {
        List<Flight> flightsFromVCP = flightRepository.getFlightsFromInTimeInterval("VCP", "BEL",
                DATE_FORMAT.parse("2019-02-19 21:21:52"));
        assertEquals(0, flightsFromVCP.size());
    }

    @Test
    public void getFlightsFromAndToInTimeInterval_shouldFilterAndReturnFlightsInTimeInterval() throws ParseException {
        List<Flight> flightsFromVCP = flightRepository.getFlightsFromAndToInTimeInterval("VCP", "BEL",
                DATE_FORMAT.parse("2019-02-17 21:21:52"), DATE_FORMAT.parse("2019-02-19 08:20:14"));
        assertEquals(5, flightsFromVCP.size());
        for (Flight flight : flightsFromVCP) {
            assertEquals("VCP", flight.getAirportFrom());
            assertEquals("BEL", flight.getAirportTo());
        }
    }

    @Test
    public void getFlightsFromAndToInTimeInterval_shouldFilterAndReturnFlightsFromBEL() throws ParseException {
        List<Flight> flightsFromBEL = flightRepository.getFlightsFromAndToInTimeInterval("BEL", "VCP",
                DATE_FORMAT.parse("2019-02-16 21:21:52"), DATE_FORMAT.parse("2019-02-19 08:20:14"));
        assertEquals(11, flightsFromBEL.size());
        for (Flight flight : flightsFromBEL) {
            assertEquals("BEL", flight.getAirportFrom());
            assertEquals("VCP", flight.getAirportTo());
        }
    }

    @Test
    public void getFlightsFromAndToInTimeInterval_shouldFilterAndReturnFlightsFrom16() throws ParseException {
        List<Flight> flightsFromBEL = flightRepository.getFlightsFromAndToInTimeInterval("BEL", "VCP",
                DATE_FORMAT.parse("2019-02-16 21:21:52"), DATE_FORMAT.parse("2019-02-18 00:00:00"));
        assertEquals(6, flightsFromBEL.size());
        for (Flight flight : flightsFromBEL) {
            assertEquals("BEL", flight.getAirportFrom());
            assertEquals("VCP", flight.getAirportTo());
        }
    }

    @Test
    public void getFlightsFromAndToInTimeInterval_shouldFilterAndReturnFlightsNotInclusive() throws ParseException {
        List<Flight> flightsFromBEL = flightRepository.getFlightsFromAndToInTimeInterval("BEL", "VCP",
                DATE_FORMAT.parse("2019-02-16 21:21:52"), DATE_FORMAT.parse("2019-02-17 23:59:59"));
        assertEquals(5, flightsFromBEL.size());
        for (Flight flight : flightsFromBEL) {
            assertEquals("BEL", flight.getAirportFrom());
            assertEquals("VCP", flight.getAirportTo());
        }
    }

    @Test
    public void getFlightsFromAndToInTimeInterval_shouldFilterAndReturnEmpty() throws ParseException {
        List<Flight> flightsFromBEL = flightRepository.getFlightsFromAndToInTimeInterval("BEL", "VCP",
                DATE_FORMAT.parse("2019-02-17 21:21:52"), DATE_FORMAT.parse("2019-02-17 23:59:59"));
        assertEquals(0, flightsFromBEL.size());
        for (Flight flight : flightsFromBEL) {
            assertEquals("BEL", flight.getAirportFrom());
            assertEquals("VCP", flight.getAirportTo());
        }
    }
}