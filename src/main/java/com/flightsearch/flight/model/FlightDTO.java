package com.flightsearch.flight.model;

import java.math.BigDecimal;
import java.text.DateFormat;

public class FlightDTO {

    public FlightDTO(Flight flight, DateFormat dateFormat) {
        this.origem = flight.getAirportFrom();
        this.destino = flight.getAirportTo();
        this.saida = dateFormat.format(flight.getDeparture());
        this.chegada = dateFormat.format(flight.getArrival());
        this.operadora = flight.getCompany();
        this.preco = flight.getPrice();
    }

    public final String origem;
    public final String destino;
    public final String saida;
    public final String chegada;
    public final String operadora;
    public final BigDecimal preco;
}
