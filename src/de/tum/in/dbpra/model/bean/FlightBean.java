package de.tum.in.dbpra.model.bean;

import java.sql.Date;
import java.sql.Time;
import org.postgresql.util.PGInterval;
import java.util.ArrayList;
import java.math.BigDecimal;

public class FlightBean {

	private String flightId;
	private Date date;
	private Time localDepartureTime;
	private Time localArrivalTime;
	private PGInterval duration;
	private AirportBean departureAirport;
	private CityBean departureCity;
	private AirportBean arrivalAirport;
	private CityBean arrivalCity;
	private int distance;
	private String operatingFlightNumber;
	private ArrayList<String> sharedFlightNumbers;
	private String gateNr;
	private BigDecimal price;
	private String travelClass;
	private String airplaneModel;
	

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getLocalDepartureTime() {
		return localDepartureTime;
	}

	public void setLocalDepartureTime(Time localDepartureTime) {
		this.localDepartureTime = localDepartureTime;
	}
	
	public Time getLocalArrivalTime() {
		return localArrivalTime;
	}

	public void setLocalArrivalTime(Time localArrivalTime) {
		this.localArrivalTime = localArrivalTime;
	}
	
	public PGInterval getDuration() {
		return duration;
	}

	public void setDuration(PGInterval duration) {
		this.duration = duration;
	}
	
	public AirportBean getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(AirportBean departureAirport) {
		this.departureAirport = departureAirport;
	}
	
	public CityBean getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(CityBean departureCity) {
		this.departureCity = departureCity;
	}
	
	public AirportBean getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(AirportBean arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}
	
	public CityBean getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(CityBean arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public String getOperatingFlightNumber() {
		return operatingFlightNumber;
	}

	public void setOperatingFlightNumber(String operatingFlightNumber) {
		this.operatingFlightNumber = operatingFlightNumber;
	}
	
	public ArrayList<String> getSharedFlightNumbers() {
		return sharedFlightNumbers;
	}

	public void setSharedFlightNumbers(ArrayList<String> sharedFlightNumbers) {
		this.sharedFlightNumbers = sharedFlightNumbers;
	}
	
	public String getGateNr() {
		return gateNr;
	}

	public void setGateNr(String gateNr) {
		this.gateNr = gateNr;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public String getTravelClass() {
		return travelClass;
	}

	public void setTravelClass(String travelClass) {
		this.travelClass = travelClass;
	}
	
	public String getAirplaneModel() {
		return airplaneModel;
	}

	public void setAirplaneModel(String airplaneModel) {
		this.airplaneModel = airplaneModel;
	}
}
