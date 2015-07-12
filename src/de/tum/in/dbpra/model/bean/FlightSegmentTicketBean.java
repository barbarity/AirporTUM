package de.tum.in.dbpra.model.bean;

import java.math.BigDecimal;

public class FlightSegmentTicketBean {

	private int id;
	private FlightBean flight;
	private FoodTypeBean foodType;
	private int seatNr;
	private BigDecimal price;
	private CurrencyBean currency;
	private String travelClass;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public FlightBean getFlight() {
		return flight;
	}

	public void setFlight(FlightBean flight) {
		this.flight = flight;
	}

	public FoodTypeBean getFoodType() {
		return foodType;
	}

	public void setFoodType(FoodTypeBean foodType) {
		this.foodType = foodType;
	}

	public int getSeatNr() {
		return seatNr;
	}

	public void setSeatNr(int seatNr) {
		this.seatNr = seatNr;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public CurrencyBean getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyBean currency) {
		this.currency = currency;
	}
	
	public String getTravelClass() {
		return travelClass;
	}

	public void setTravelClass(String travelClass) {
		this.travelClass = travelClass;
	}
	
}
