package de.tum.in.dbpra.model.bean;

import java.util.ArrayList;

public class FoodTypeListBean {

	private ArrayList<FoodTypeBean> foodTypeList = new ArrayList<FoodTypeBean>();

	public ArrayList<FoodTypeBean> getFoodTypeList() {
		return foodTypeList;
	}

	public void setFoodTypeList(ArrayList<FoodTypeBean> foodTypeList) {
		this.foodTypeList = foodTypeList;
	}
}
