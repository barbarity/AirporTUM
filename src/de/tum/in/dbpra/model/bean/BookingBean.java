package de.tum.in.dbpra.model.bean;

import java.math.BigDecimal;
import java.util.ArrayList;

public class BookingBean {

	private String id;
	private ArrayList<FlightSegmentTicketBean> flightSegmentTicketList;
	private PersonBean person;
	private BigDecimal price;
	private CurrencyBean currency;
	private ArrayList<LuggageBean> luggageList;
	private AirlineBean bookedAtAirline;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<FlightSegmentTicketBean> getFlightSegmentTicketList() {
		return flightSegmentTicketList;
	}

	public void setFlightSegmentTicketList(ArrayList<FlightSegmentTicketBean> flightSegmentTicketList) {
		this.flightSegmentTicketList = flightSegmentTicketList;
	}

	public PersonBean getPerson() {
		return person;
	}

	public void setPerson(PersonBean person) {
		this.person = person;
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
	
	public ArrayList<LuggageBean> getLuggageList() {
		return luggageList;
	}

	public void setLuggageList(ArrayList<LuggageBean> luggageList) {
		this.luggageList = luggageList;
	}
	
	public AirlineBean getBookedAtAirline() {
		return bookedAtAirline;
	}

	public void setBookedAtAirline(AirlineBean bookedAtAirline) {
		this.bookedAtAirline = bookedAtAirline;
	}
	
}
