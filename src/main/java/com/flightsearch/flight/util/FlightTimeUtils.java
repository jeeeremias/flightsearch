package com.flightsearch.flight.util;

import java.util.Calendar;
import java.util.Date;

public final class FlightTimeUtils {

    private FlightTimeUtils() {
    }

    public static Date setFirstTimeOfTheDay(Date date) {
        Calendar time = new Calendar.Builder().setInstant(date).build();
        time.set(Calendar.HOUR_OF_DAY, 0);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);
        return time.getTime();
    }

    public static Date setLastTimeOfTheDay(Date date) {
        Calendar time = new Calendar.Builder().setInstant(date).build();
        time.set(Calendar.HOUR_OF_DAY, 23);
        time.set(Calendar.MINUTE, 59);
        time.set(Calendar.SECOND, 59);
        time.set(Calendar.MILLISECOND, 999);
        return time.getTime();
    }

    public static Date add12Hours(Date date) {
        Calendar calendar = new Calendar.Builder()
                .setInstant(date)
                .build();
        calendar.add(Calendar.HOUR_OF_DAY, 12);
        return calendar.getTime();
    }
}
