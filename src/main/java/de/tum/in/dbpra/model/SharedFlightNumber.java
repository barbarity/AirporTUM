package main.java.de.tum.in.dbpra.model;

import java.io.Serializable;

/**
 * Created by barbarity on 30/06/15.
 */
public class SharedFlightNumber implements Serializable {

    private static final long serialVersionUID = 8341605847061890152L;

    private String flightNumber;
    private Airline airline;
    private Route route;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public int hashCode() {
        return (flightNumber != null)
                ? (this.getClass().hashCode() + flightNumber.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof SharedFlightNumber) && (flightNumber != null)
                ? flightNumber.equals(((SharedFlightNumber) obj).flightNumber)
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("SharedFlightNumber[flightNumber=%d,airline=%s,route=%s]",
                flightNumber, airline, route);
    }
}
