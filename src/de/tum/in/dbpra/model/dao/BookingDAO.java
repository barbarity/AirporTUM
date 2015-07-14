package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.tum.in.dbpra.model.bean.BookingBean;
import de.tum.in.dbpra.model.bean.LuggageBean;
import de.tum.in.dbpra.model.bean.AirlineBean;
import de.tum.in.dbpra.model.bean.PersonBean;
import de.tum.in.dbpra.model.bean.CurrencyBean;
import de.tum.in.dbpra.model.bean.FlightSegmentTicketBean;
import de.tum.in.dbpra.model.dao.AirlineDAO.AirlineNotFoundException;


public class BookingDAO extends AbstractDAO {

	private Connection connection;

	public BookingDAO(Connection con){
		this.connection=con;
	}
	
	public BookingBean getBookingById(int id) throws BookingNotFoundException, PersonDAO.PersonNotFoundException, AirlineDAO.AirlineNotFoundException, FoodTypeDAO.FoodTypeNotFoundException, LuggageDAO.LuggageNotFoundException, CurrencyDAO.CurrencyNotFoundException, FlightSegmentTicketDAO.BookingNotFoundException,FlightDAO.FlightNotFoundException, SQLException {
		
		
		String query = "SELECT booking_id, customer_id, airline_code, price, currency_code, bookingTimestamp, checkedInOn " +
				"from booking " +
				"WHERE booking_id = ?" ;	
				
		BookingBean booking = null;
	/*	try ( this.connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			*/
		PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				if (resultSet.next()) {
					
					AirlineBean airline = new AirlineBean();
					airline.setCode(resultSet.getString(3));
					
					AirlineDAO airlineDao = new AirlineDAO();
					airlineDao.getAirlineById(airline);
					
					
					
					PersonBean person = new PersonBean();
					person.setId(resultSet.getInt(2));
					
					PersonDAO personDao = new PersonDAO();
					personDao.getPersonById(person);
					
					
					
					CurrencyBean currency = new CurrencyBean();
					currency.setCurrencyCode(resultSet.getString(5));
					
					CurrencyDAO currencyDao = new CurrencyDAO();
					currencyDao.getCurrencyByCurrencyCode(currency);
					
					
				
					
					booking = new BookingBean();
					booking.setId(id);
					
					
					
					FlightSegmentTicketDAO flightSegmentTicketDao = new FlightSegmentTicketDAO();
					ArrayList<FlightSegmentTicketBean> flightSegmentTickets = flightSegmentTicketDao.getFlightSegmentTicketsFromBooking(booking);
					
					booking.setFlightSegmentTicketList(flightSegmentTickets);
					
					
					LuggageDAO luggageDao = new LuggageDAO(connection);
					ArrayList<LuggageBean> luggageItems = luggageDao.getLuggageItemsOfBooking(booking);
					
					booking.setLuggageList(luggageItems);
					
					booking.setPrice(resultSet.getBigDecimal(4));
					booking.setBookedAtAirline(airline);
					booking.setCurrency(currency);
					booking.setPerson(person);
					booking.setBookingTimestamp(resultSet.getDate(6));
					booking.setCheckedInOn(resultSet.getDate(7));
					
					
				}
				
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
	/*	
	} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		*/
		
		
		
		
		return booking;
	}
	
	
	
	/*
	 * writes the current date/time in the database to mark that the booking has been checked in now
	 */
	public boolean finishCheckIn(BookingBean booking) throws AirlineNotFoundException, SQLException {
		String query = "update booking set checkedInOn=NOW() where booking_id =?";
				
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			 preparedStatement.setInt(1, booking.getId());
			
			 try {
					int rows = preparedStatement.executeUpdate();
					if (rows == 1) return true;
				} catch (SQLException e) {
					e.printStackTrace();
					throw e;
				}
			 
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return false;
	}
	
	
	
	
	
	
	@SuppressWarnings("serial")
	public static class BookingNotFoundException extends Throwable {
		BookingNotFoundException(String message){
			super(message);
		}
	}
	
	
	
}
