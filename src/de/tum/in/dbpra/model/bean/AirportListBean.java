package de.tum.in.dbpra.model.bean;
import java.util.ArrayList;

public class AirportListBean {


	private ArrayList<AirportBean> airportList = new ArrayList<AirportBean>();

	public ArrayList<AirportBean> getAirportList() {
		return airportList;
	}

	public void setAirportList(ArrayList<AirportBean> airportList) {
		this.airportList=airportList;
	}
	
}
