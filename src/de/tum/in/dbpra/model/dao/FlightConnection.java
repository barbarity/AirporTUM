

package de.tum.in.dbpra.model.dao;



import de.tum.in.dbpra.model.bean.CityBean;
import de.tum.in.dbpra.model.bean.AirportBean;
import de.tum.in.dbpra.model.bean.ConnectionListBean;
import de.tum.in.dbpra.model.bean.ConnectionBean;
import de.tum.in.dbpra.model.bean.CurrencyBean;
import de.tum.in.dbpra.model.bean.FlightBean;
import de.tum.in.dbpra.model.dao.FlightDAO;

import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Deque;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class FlightConnection {
		ArrayList<ArrayList<String>> conns;
	
	private final int maxStops = 0;

	private FlightDAO flightDAO = new FlightDAO();
	
	public ConnectionListBean getConnections(Time dateFrom, Time dateTo, CityBean departureCity,
			CityBean arrivalCity, String flightClass, CurrencyBean currency, int seats) throws SQLException{
		// Init connections
		ConnectionListBean connections = new ConnectionListBean();
		connections.setConnectionList(new ArrayList<ConnectionBean>());	
		
		// Get DAOs;
		AirportDAO airportDAO = new AirportDAO();
		
		// Get starting and ending airports
		ArrayList<AirportBean> departureAirports = airportDAO .getAirportsInCity(departureCity).getAirportList();
		ArrayList<AirportBean> arrivalAirports = airportDAO .getAirportsInCity(arrivalCity).getAirportList();
		
		// Initialize openFlights
		ArrayList<ArrayList<FlightBean>> openPaths = new ArrayList<ArrayList<FlightBean>>();
		ArrayList<ArrayList<FlightBean>> closedPaths = new ArrayList<ArrayList<FlightBean>>();
		
		// Iterate every starting point
		for (AirportBean departureAirport: departureAirports) {
			// Get the initial flights
			ArrayList<FlightBean> foundFlights = flightDAO.getValidFlightsFrom(departureAirport, dateFrom, dateTo, flightClass, currency, seats);
			
			for (int i = 0; i < foundFlights.size(); i++) {
				boolean found = false;
				
				for (AirportBean arrivalAirport: arrivalAirports) {
					if (foundFlights.get(i).getArrivalAirport().getIATA().equals(arrivalAirport.getIATA())) {
						found = true;
						break;
					}
				}
				
				if (found) {
					ArrayList<FlightBean> closedPath = new ArrayList<FlightBean>();
					closedPath.add(foundFlights.get(i));
					closedPaths.add(closedPath);
				} else {
					ArrayList<FlightBean> openPath = new ArrayList<FlightBean>();
					openPath.add(foundFlights.get(i));
					openPaths.add(openPath);
				}
				
				// TODO: Add stops
				
			}
		}
		
		// Fill the connections
		for (ArrayList<FlightBean> closedPath: closedPaths) {
			// Instance of new connection
			ConnectionBean connection = new ConnectionBean();
			
			// Init values
			Time duration = new Time(0);
			BigDecimal overallPrice = new BigDecimal(0.00);
			
			// Iterate the the ArrayList
			for (FlightBean flight: closedPath) {
				//overallPrice.add(flight.getPriceInDollar());
			}
			
			connection.setOverallPrice(overallPrice);
			connection.setOverallDuration(duration);
			connection.setFlightList(closedPath);
			connection.setCurrency(currency);
			
			connections.getConnectionList().add(connection);
		}
		
		return connections;
		
	}
	
}

