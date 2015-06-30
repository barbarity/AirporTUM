package main.java.de.tum.in.dbpra.model;

import org.postgresql.util.PGInterval;

import java.io.Serializable;
import java.sql.Time;

/**
 * Created by barbarity on 30/06/15.
 */
public class Route implements Serializable {

    private static final long serialVersionUID = 8844078686596612230L;

    private String id;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private FlightNumber flightNumber;
    private Airline airline;
    private Time departureTime;
    private PGInterval duration;
    private Time scheduledPushbackTime;
    private Integer distance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public FlightNumber getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(FlightNumber flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public PGInterval getDuration() {
        return duration;
    }

    public void setDuration(PGInterval duration) {
        this.duration = duration;
    }

    public Time getScheduledPushbackTime() {
        return scheduledPushbackTime;
    }

    public void setScheduledPushbackTime(Time scheduledPushbackTime) {
        this.scheduledPushbackTime = scheduledPushbackTime;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public int hashCode() {
        return (id != null)
                ? (this.getClass().hashCode() + id.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Route) && (id != null)
                ? id.equals(((Route) obj).id)
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("Route[id=%d,departureAiport=%s,arrivalAirport=%s,flightNumber=%s,airline=%s,departureTime=%s,duration=%s,,scheduledPushbackTime=%s,,duration=%d]",
                id, departureAirport, arrivalAirport, flightNumber, airline, departureTime, duration, scheduledPushbackTime, distance);
    }
}
