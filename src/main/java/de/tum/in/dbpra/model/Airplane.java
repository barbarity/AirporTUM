package main.java.de.tum.in.dbpra.model;

import java.io.Serializable;

/**
 * Created by barbarity on 30/06/15.
 */
public class Airplane implements Serializable {

    private static final long serialVersionUID = -9003272467818201826L;

    private String registrationNumber;
    private AirplaneModel airplaneModel;
    private Airline airline;
    private Integer firstClassSeats;
    private Integer economyClassSeats;
    private Integer businessClassSeats;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public AirplaneModel getAirplaneModel() {
        return airplaneModel;
    }

    public void setAirplaneModel(AirplaneModel airplaneModel) {
        this.airplaneModel = airplaneModel;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Integer getFirstClassSeats() {
        return firstClassSeats;
    }

    public void setFirstClassSeats(Integer firstClassSeats) {
        this.firstClassSeats = firstClassSeats;
    }

    public Integer getEconomyClassSeats() {
        return economyClassSeats;
    }

    public void setEconomyClassSeats(Integer economyClassSeats) {
        this.economyClassSeats = economyClassSeats;
    }

    public Integer getBusinessClassSeats() {
        return businessClassSeats;
    }

    public void setBusinessClassSeats(Integer businessClassSeats) {
        this.businessClassSeats = businessClassSeats;
    }

    @Override
    public int hashCode() {
        return (registrationNumber != null)
                ? (this.getClass().hashCode() + registrationNumber.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Airplane) && (registrationNumber != null)
                ? registrationNumber.equals(((Airplane) obj).registrationNumber)
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("Airplane[registrationNumber=%d,airplaneModel=%s,airline=%s,firstClassSeats=%d,economyClassSeats=%d,businessClassSeats=%d]",
                registrationNumber, airplaneModel, airline, firstClassSeats, economyClassSeats, businessClassSeats);
    }
}
