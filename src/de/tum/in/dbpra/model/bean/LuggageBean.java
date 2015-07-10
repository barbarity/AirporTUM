package de.tum.in.dbpra.model.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class LuggageBean {

	private int id;
	private int weight;
	private int height;
	private int width;
	private int length;
	private BigDecimal additionalPrice;
	private Timestamp checkedInOn;
	private PersonBean checkInWorker;
	private boolean registeredAtBooking;


	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id=id;
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
	
	public Timestamp getCheckedInOn() {
		return checkedInOn;
	}

	public void setCheckedInOn(Timestamp checkedInOn) {
		this.checkedInOn=checkedInOn;
	}
	
	public PersonBean getCheckInWorker() {
		return checkInWorker;
	}

	public void setCheckInWorker(PersonBean checkInWorker) {
		this.checkInWorker=checkInWorker;
	}
	
	public boolean getRegisteredAtBooking() {
		return registeredAtBooking;
	}

	public void setRegisteredAtBooking(boolean registeredAtBooking) {
		this.registeredAtBooking=registeredAtBooking;
	}
	
}
