package de.tum.in.dbpra.model.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

import de.tum.in.dbpra.model.bean.CityBean;
import de.tum.in.dbpra.model.bean.ConnectionBean;
import de.tum.in.dbpra.model.bean.ConnectionListBean;
import de.tum.in.dbpra.model.bean.CurrencyBean;
import de.tum.in.dbpra.model.bean.FlightBean;

public class ConnectionDAO extends AbstractDAO {
	public ConnectionListBean getConnections(Date dateFrom, Date dateTo, CityBean departureCity, CityBean arrivalCity, String className, CurrencyBean currency) {
		ConnectionListBean connectionListBean = new ConnectionListBean();
		connectionListBean.setConnectionList(new ArrayList<ConnectionBean>());
		
		//TODO: Implement algorithm to retrieve the ConnectionList. Data mocked up till then.
		
		// Start of mock up data
		ConnectionBean connectionBean1 = new ConnectionBean();
		
		connectionBean1.setCurrency(currency);
		
		ArrayList<FlightBean> flights = new ArrayList<FlightBean>();
		
		FlightBean flightBean1 = new FlightBean();
		flightBean1.setFlightId("TR1012");
		flightBean1.setDepartureCity(departureCity);
		flightBean1.setArrivalCity(arrivalCity);
		
		flights.add(flightBean1);
		
		connectionBean1.setFlightList(flights);
		connectionBean1.setOverallPrice(new BigDecimal(2000));
		
		connectionListBean.getConnectionList().add(connectionBean1);
		
		ConnectionBean connectionBean2 = new ConnectionBean();
		
		connectionBean2.setCurrency(currency);
		
		ArrayList<FlightBean> flights2 = new ArrayList<FlightBean>();
		
		FlightBean flightBean2 = new FlightBean();
		flightBean2.setFlightId("FK7987");
		flightBean2.setDate(dateFrom);
		flightBean2.setDepartureCity(departureCity);
		flightBean2.setArrivalCity(arrivalCity);
		
		flights2.add(flightBean2);
		
		connectionBean2.setFlightList(flights2);
		connectionBean2.setOverallPrice(new BigDecimal(3000));
		
		connectionListBean.getConnectionList().add(connectionBean2);
		
		// End of mock up data
		
		return connectionListBean;
	}
}
