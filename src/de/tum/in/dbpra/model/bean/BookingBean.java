package de.tum.in.dbpra.model.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class BookingBean {

	private int id;
	private ArrayList<FlightSegmentTicketBean> flightSegmentTicketList;
	private PersonBean person;
	private BigDecimal price;
	private CurrencyBean currency;
	private ArrayList<LuggageBean> luggageList;
	private AirlineBean bookedAtAirline;
	private Date bookingTimestamp;
	private Date checkedInOn;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
	public Date getBookingTimestamp(){
		return bookingTimestamp;
	}
	
	public void setBookingTimestamp(Date bookingTimestamp){
		this.bookingTimestamp = bookingTimestamp;
	}
	
	public Date getCheckedInOn(){
		return checkedInOn;
	}
	
	public void setCheckedInOn(Date checkedInOn){
		this.checkedInOn = checkedInOn;
	}
	
}
