package de.tum.in.dbpra.model.bean;

import java.util.ArrayList;

public class AirlineListBean {

	private ArrayList<AirlineBean> airlineList = new ArrayList<AirlineBean>();

	public ArrayList<AirlineBean> getAirlineList() {
		return airlineList;
	}

	public void setAirlineList(ArrayList<AirlineBean> airlineList) {
		this.airlineList = airlineList;
	}

	public void addAirlineToList(AirlineBean airline) {
		airlineList.add(airline);
	}
}
