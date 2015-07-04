package de.tum.in.dbpra.model.bean;

import java.util.ArrayList;
import java.math.BigDecimal;
import org.postgresql.util.PGInterval;


public class ConnectionBean {

	private ArrayList<FlightBean> flightList;
	private CurrencyBean currency;
	private BigDecimal overallPrice;
	private PGInterval overallDuration;


	public ArrayList<FlightBean> getFlightList() {
		return flightList;
	}

	public void setFlightList(ArrayList<FlightBean> flightList) {
		this.flightList=flightList;
	}
	
	public CurrencyBean getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyBean currency) {
		this.currency=currency;
	}
	
	public BigDecimal getOverallPrice() {
		return overallPrice;
	}

	public void setOverallPrice(BigDecimal overallPrice) {
		this.overallPrice=overallPrice;
	}
	
	public PGInterval getOverallDuration() {
		return overallDuration;
	}

	public void setOverallDuration(PGInterval overallDuration) {
		this.overallDuration=overallDuration;
	}
}
