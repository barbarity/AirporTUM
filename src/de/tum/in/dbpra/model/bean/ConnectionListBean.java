package de.tum.in.dbpra.model.bean;

import java.util.ArrayList;

public class ConnectionListBean {

	private ArrayList<ConnectionBean> connectionList = new ArrayList<ConnectionBean>();

	public ArrayList<ConnectionBean> getConnectionList() {
		return connectionList;
	}

	public void setConnectionList(ArrayList<ConnectionBean> connectionList) {
		this.connectionList = connectionList;
	}
}
