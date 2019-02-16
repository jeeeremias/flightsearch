package com.flightsearch.flight;

import com.flightsearch.flight.model.Flight;

import java.util.Date;
import java.util.List;

public interface FlightRepository {

    /**
     * Retorna todos os voos de um dia saindo de um aeroporto para outro aeroporto diferente.
     * As horas do objeto {@code date} serão ignoradas, serão retornados os voos encontrados
     * das 12:00AM até as 11:59PM do dia especificado.
     * @param airportFrom aeroporto de origem.
     * @param airportTo aeroporto de destino.
     * @param date data do voo (horas podem ser randomicas, pois será considerado apenas o dia).
     * @return Lista de voos encontrados de um aeroporto para o outro no dia especificado sem escalas.
     */
    List<Flight> getFlightsFromInTimeInterval(String airportFrom, String airportTo, Date date);

    /**
     * Retorna todos os voos que saem de um aeroporto para um outro qualquer dentro de um intervalo de tempo.
     * Nesta consulta as horas serão consideradas.
     * @param airportFrom aeroporto de origem.
     * @param dateFrom data inicial de saída do voo.
     * @param dateTo data final de saída do voo.
     * @return Lista de voos encontrados saindo do aeroporoto especificado em um intervalo de tempo.
     */
    List<Flight> getFlightsFromInTimeInterval(String airportFrom, Date dateFrom, Date dateTo);

    /**
     * Retorna todos os voos que chegem a um aeroporto saindo um outro qualquer dentro de um intervalo de tempo.
     * Nesta consulta as horas serão consideradas.
     *
     * @param airportFrom
     * @param airportTo aeroporto de destino.
     * @param dateDepartureFrom data inicial de saída do voo.
     * @param dateDepartureTo data final de saída do voo.
     * @return Lista de voos encontrados saindo do aeroporoto especificado em um intervalo de tempo.
     */
    List<Flight> getFlightsFromAndToInTimeInterval(String airportFrom, String airportTo, Date dateDepartureFrom, Date dateDepartureTo);
}
