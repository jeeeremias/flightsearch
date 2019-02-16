package com.flightsearch.flight.util;

import java.util.Calendar;
import java.util.Date;

public final class FlightTimeUtils {

    private FlightTimeUtils() {
    }

    public static Date setFirstTimeOfTheDay(Date date) {
        return new Calendar.Builder()
                .setInstant(date)
                .set(Calendar.HOUR_OF_DAY, 0)
                .set(Calendar.MINUTE, 0)
                .set(Calendar.SECOND, 0)
                .set(Calendar.MILLISECOND, 0)
                .build()
                .getTime();
    }

    public static Date setLastTimeOfTheDay(Date date) {
        return new Calendar.Builder()
                .setInstant(date)
                .set(Calendar.HOUR_OF_DAY, 23)
                .set(Calendar.MINUTE, 59)
                .set(Calendar.SECOND, 59)
                .set(Calendar.MILLISECOND, 999)
                .build()
                .getTime();
    }

    public static Date add12Hours(Date date) {
        Calendar calendar = new Calendar.Builder()
                .setInstant(date)
                .build();
        calendar.add(Calendar.HOUR_OF_DAY, 12);
        return calendar.getTime();
    }
}
