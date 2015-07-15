package de.tum.in.dbpra.model.bean;

import java.util.ArrayList;

public class BookingListBean {

	private ArrayList<BookingBean> bookingList = new ArrayList<BookingBean>();

	public ArrayList<BookingBean> getBookingList() {
		return bookingList;
	}

	public void setBookingList(ArrayList<BookingBean> bookingList) {
		this.bookingList = bookingList;
	}
}
