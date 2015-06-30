package main.java.de.tum.in.dbpra.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by barbarity on 30/06/15.
 */
public class Flight implements Serializable {

    private static final long serialVersionUID = -8460967005638912708L;

    private String id;
    private Route route;
    private Airplane airplane;
    private Date date;
    private Timestamp actualPushbackTime;
    private Timestamp actualArrivalTime;
    private String gateNumber;
    private FlightStateType state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getActualPushbackTime() {
        return actualPushbackTime;
    }

    public void setActualPushbackTime(Timestamp actualPushbackTime) {
        this.actualPushbackTime = actualPushbackTime;
    }

    public Timestamp getActualArrivalTime() {
        return actualArrivalTime;
    }

    public void setActualArrivalTime(Timestamp actualArrivalTime) {
        this.actualArrivalTime = actualArrivalTime;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public FlightStateType getState() {
        return state;
    }

    public void setState(FlightStateType state) {
        this.state = state;
    }

    public enum FlightStateType {
        ONAIR("onAir"),
        ARRIVED("arrived"),
        BOARDING("boarding"),
        CANCELLED("cancelled");

        private final String value;

        FlightStateType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    @Override
    public int hashCode() {
        return (id != null)
                ? (this.getClass().hashCode() + id.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Flight) && (id != null)
                ? id.equals(((Flight) obj).id)
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("Flight[id=%d,route=%s,airplane=%s,date=%s,actualPushbackTime=%s,actualArrivalTime=%s,gateNumber=%s,state=%s]",
                id, route, airplane, date, actualPushbackTime, actualArrivalTime, gateNumber, state);
    }
}
