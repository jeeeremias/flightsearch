package com.flightsearch.flight;

import com.flightsearch.flight.model.Flight;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

class FlightUtils {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static List<Flight> getMockedFlights() throws ParseException {
        Date[] dates = new Date[34];
        dates[0] = DATE_FORMAT.parse("2019-02-17 07:00:51");
        dates[1] = DATE_FORMAT.parse("2019-02-17 07:32:51");
        dates[2] = DATE_FORMAT.parse("2019-02-17 08:01:51");
        dates[3] = DATE_FORMAT.parse("2019-02-17 08:09:51");
        dates[4] = DATE_FORMAT.parse("2019-02-17 09:50:51");
        dates[5] = DATE_FORMAT.parse("2019-02-17 10:42:51");
        dates[6] = DATE_FORMAT.parse("2019-02-17 11:59:51");
        dates[7] = DATE_FORMAT.parse("2019-02-17 12:55:51");
        dates[8] = DATE_FORMAT.parse("2019-02-17 13:47:51");
        dates[9] = DATE_FORMAT.parse("2019-02-17 14:30:51");
        dates[10] = DATE_FORMAT.parse("2019-02-17 21:21:51");
        dates[11] = DATE_FORMAT.parse("2019-02-17 23:59:59");
        dates[12] = DATE_FORMAT.parse("2019-02-18 11:59:59");
        dates[13] = DATE_FORMAT.parse("2019-02-18 13:29:59");
        dates[14] = DATE_FORMAT.parse("2019-02-18 15:57:59");
        dates[15] = DATE_FORMAT.parse("2019-02-18 17:13:59");
        dates[16] = DATE_FORMAT.parse("2019-02-18 20:49:59");
        dates[17] = DATE_FORMAT.parse("2019-02-18 21:02:59");
        dates[18] = DATE_FORMAT.parse("2019-02-18 21:58:59");
        dates[19] = DATE_FORMAT.parse("2019-02-18 22:11:59");
        dates[20] = DATE_FORMAT.parse("2019-02-18 23:00:00");
        dates[21] = DATE_FORMAT.parse("2019-02-18 23:41:00");
        dates[22] = DATE_FORMAT.parse("2019-02-17 09:00:51");
        dates[23] = DATE_FORMAT.parse("2019-02-17 09:32:51");
        dates[24] = DATE_FORMAT.parse("2019-02-17 10:01:51");
        dates[25] = DATE_FORMAT.parse("2019-02-17 10:09:51");
        dates[26] = DATE_FORMAT.parse("2019-02-17 11:50:51");
        dates[27] = DATE_FORMAT.parse("2019-02-17 12:42:51");
        dates[28] = DATE_FORMAT.parse("2019-02-17 13:59:51");
        dates[29] = DATE_FORMAT.parse("2019-02-17 14:55:51");
        dates[30] = DATE_FORMAT.parse("2019-02-17 15:47:51");
        dates[31] = DATE_FORMAT.parse("2019-02-17 16:30:51");
        dates[32] = DATE_FORMAT.parse("2019-02-17 23:21:51");

        List<Flight> flights = new ArrayList<>();
        Flight flight;
        Calendar calendar;
        for (int i = 0; i < 22; i++) {
            calendar = new Calendar.Builder().setInstant(dates[i]).build();
            calendar.add(Calendar.HOUR, 1);
            flight = new Flight();
            flight.setDeparture(dates[i]);
            flight.setArrival(calendar.getTime());
            if (i % 2 == 0) {
                flight.setAirportFrom("VCP");
                flight.setAirportTo("BEL");
            } else {
                flight.setAirportFrom("BEL");
                flight.setAirportTo("VCP");
            }
            flights.add(flight);
        }
        for (int i = 22; i < 33; i++) {
            calendar = new Calendar.Builder().setInstant(dates[i]).build();
            calendar.add(Calendar.HOUR, 1);
            flight = new Flight();
            flight.setDeparture(dates[i]);
            flight.setArrival(calendar.getTime());
            if (i % 2 == 0) {
                flight.setAirportFrom("BEL");
                flight.setAirportTo("AJU");
            } else {
                flight.setAirportFrom("VCP");
                flight.setAirportTo("VIX");
            }
        }
        return flights;
    }
}
