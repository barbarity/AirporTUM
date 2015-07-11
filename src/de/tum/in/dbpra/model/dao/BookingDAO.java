package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import de.tum.in.dbpra.model.bean.BookingBean;
import de.tum.in.dbpra.model.bean.AirlineBean;
import de.tum.in.dbpra.model.bean.PersonBean;
import de.tum.in.dbpra.model.bean.CurrencyBean;


public class BookingDAO extends AbstractDAO {

	public BookingBean getBookingById(int id) throws BookingNotFoundException, PersonDAO.PersonNotFoundException, AirlineDAO.AirlineNotFoundException, CurrencyDAO.CurrencyNotFoundException, SQLException {
		
		
		String query = "SELECT booking_id, customer_id, airline_code, price, currency_code, bookingTimestamp " +
				"from booking " +
				"WHERE booking_id = ?" ;	
				
		BookingBean booking = null;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);) {
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
					personDao.getPerson(person.getEmail(), person.getPassword());
					
					
					CurrencyBean currency = new CurrencyBean();
					person.setId(resultSet.getInt(5));
					
					CurrencyDAO currencyDao = new CurrencyDAO();
					currencyDao.getCurrencyByCurrencyCode(currency);
					
					
					booking = new BookingBean();
					booking.setPrice(resultSet.getBigDecimal(4));
					booking.setBookedAtAirline(airline);
					booking.setCurrency(currency);
					booking.setPerson(person);
					booking.setBookingTimestamp(resultSet.getDate(6));
					//luggageList = luggageDao.getLuggageItemsForBooking( int bookingid)
					//booking.setLuggageList(luggageList);
					
					
				}
				
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return booking;
	}
	
	
	
	
	@SuppressWarnings("serial")
	public static class BookingNotFoundException extends Throwable {
		BookingNotFoundException(String message){
			super(message);
		}
	}
	
	
	
}
