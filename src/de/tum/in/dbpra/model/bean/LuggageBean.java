package de.tum.in.dbpra.model.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class LuggageBean implements Cloneable{

	private int id;
	private int bookingId;
	private int weight;
	private int height;
	private int width;
	private int length;
	private BigDecimal additionalPrice;
	private boolean registeredAtBooking;


	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id=id;
	}
	
	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId=bookingId;
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight=weight;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height=height;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width=width;
	}
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length=length;
	}
	
	public BigDecimal getAdditionalPrice() {
		return additionalPrice;
	}

	public void setAdditionalPrice(BigDecimal additionalPrice) {
		this.additionalPrice=additionalPrice;
	}
	
	public boolean getRegisteredAtBooking() {
		return registeredAtBooking;
	}

	public void setRegisteredAtBooking(boolean registeredAtBooking) {
		this.registeredAtBooking=registeredAtBooking;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
}
