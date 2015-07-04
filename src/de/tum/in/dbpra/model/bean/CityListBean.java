package de.tum.in.dbpra.model.bean;

import java.util.ArrayList;

public class CityListBean {

	private ArrayList<CityBean> cityList = new ArrayList<CityBean>();

	public ArrayList<CityBean> getCityList() {
		return cityList;
	}

	public void setCityList(ArrayList<CityBean> cityList) {
		this.cityList=cityList;
	}
	
	public void addCityToList(CityBean city) {
		cityList.add(city);
	}
}
