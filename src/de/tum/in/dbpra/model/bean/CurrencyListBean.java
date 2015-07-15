package de.tum.in.dbpra.model.bean;

import java.util.ArrayList;

public class CurrencyListBean {

	private ArrayList<CurrencyBean> currencyList = new ArrayList<CurrencyBean>();

	public ArrayList<CurrencyBean> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(ArrayList<CurrencyBean> currencyList) {
		this.currencyList = currencyList;
	}

	public void addCurrencyToList(CurrencyBean currency) {
		currencyList.add(currency);
	}
}
