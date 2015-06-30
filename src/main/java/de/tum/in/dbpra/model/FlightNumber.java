package main.java.de.tum.in.dbpra.model;

import java.io.Serializable;

/**
 * Created by barbarity on 30/06/15.
 */
public class FlightNumber implements Serializable {

    private static final long serialVersionUID = 1038661525520717988L;

    private String flightNumber;
    private Airline airline;
    private Boolean active;

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        return (flightNumber != null)
                ? (this.getClass().hashCode() + flightNumber.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof FlightNumber) && (flightNumber != null)
                ? flightNumber.equals(((FlightNumber) obj).flightNumber)
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("FlightNumber[flightNumber=%d,airline=%s,active=%s]",
                flightNumber, airline, active);
    }
}
