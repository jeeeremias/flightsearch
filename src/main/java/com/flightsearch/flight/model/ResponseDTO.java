package com.flightsearch.flight.model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ResponseDTO {

    public ResponseDTO(Flight flight, DateFormat dateFormat) {
        this.origem = flight.getAirportFrom();
        this.destino = flight.getAirportTo();
        this.saida = dateFormat.format(flight.getDeparture());
        this.chegada = dateFormat.format(flight.getArrival());
        this.trechos = Collections.singletonList(new FlightDTO(flight, dateFormat));
    }

    public ResponseDTO(LinkedList<Flight> flights, DateFormat dateFormat) {
        this.origem = flights.getFirst().getAirportFrom();
        this.destino = flights.getLast().getAirportTo();
        this.saida = dateFormat.format(flights.getFirst().getDeparture());
        this.chegada = dateFormat.format(flights.getLast().getArrival());
        this.trechos = new ArrayList<>();
        for (Flight flight : flights) {
            this.trechos.add(new FlightDTO(flight, dateFormat));
        }
    }

    public final String origem;
    public final String destino;
    public final String saida;
    public final String chegada;
    public final List<FlightDTO> trechos;

}
