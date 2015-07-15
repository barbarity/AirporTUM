package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.tum.in.dbpra.model.bean.BookingBean;
import de.tum.in.dbpra.model.bean.FlightSegmentTicketBean;
import de.tum.in.dbpra.model.bean.FoodTypeBean;
import de.tum.in.dbpra.model.bean.FlightBean;


public class FlightSegmentTicketDAO extends AbstractDAO {

	public ArrayList<FlightSegmentTicketBean> getFlightSegmentTicketsFromBooking(BookingBean booking) throws BookingNotFoundException, FoodTypeDAO.FoodTypeNotFoundException, FlightDAO.FlightNotFoundException,SQLException {
		
		
		String query = "SELECT fst_id, flight_id, catering_code, class, price, seatnr " +
				"from flightsegmentticket " +
				"WHERE booking_id = ?" ;	
				
		ArrayList<FlightSegmentTicketBean> flightSegmentTickets = new ArrayList<FlightSegmentTicketBean>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, booking.getId());

			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				while (resultSet.next()) {
					FlightSegmentTicketBean flightSegmentTicket = new FlightSegmentTicketBean();
					flightSegmentTicket.setId(resultSet.getInt(1));
					
					FlightBean flight = new FlightBean();
					flight.setFlightId(resultSet.getInt(2));
					FlightDAO flightDao = new FlightDAO();
					flightSegmentTicket.setFlight(flightDao.getFlightById(flight));
					
					FoodTypeBean foodType = new FoodTypeBean();
					foodType.setCateringCode(resultSet.getString(3));
					FoodTypeDAO foodTypeDao = new FoodTypeDAO();
					flightSegmentTicket.setFoodType(foodTypeDao.getFoodType(foodType));
					
					flightSegmentTicket.setTravelClass(resultSet.getString(4));
					flightSegmentTicket.setPrice(resultSet.getBigDecimal(5));
					flightSegmentTicket.setSeatNr(resultSet.getInt(6));

					flightSegmentTicket = setBookedFlightNumber(booking, flightSegmentTicket);
					
					flightSegmentTickets.add(flightSegmentTicket);
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
		
		
		return flightSegmentTickets;
	}
	
	
	
	/*
	 * sets the flight number that has actually been booked in the given FlightSegmentTicketBean. 
	 * We need this to take codesharing into account.
	 */
	private FlightSegmentTicketBean setBookedFlightNumber(BookingBean booking, FlightSegmentTicketBean flightSegmentTicket){
		String bookedFlightNumber = flightSegmentTicket.getFlight().getOperatingAirline().getCode()+flightSegmentTicket.getFlight().getOperatingFlightNumber();
		if(booking.getBookedAtAirline()!=null && !bookedFlightNumber.startsWith(booking.getBookedAtAirline().getCode())){
			for (int i=0; i<flightSegmentTicket.getFlight().getSharedFlightNumbers().size(); i++){
				String flightNumber = flightSegmentTicket.getFlight().getSharedFlightNumbers().get(i);
				if(flightNumber.startsWith(booking.getBookedAtAirline().getCode())){
					bookedFlightNumber = flightNumber; 
					break;
				}
			}
		}
		
		flightSegmentTicket.setBookedFlightNumber(bookedFlightNumber);
		return flightSegmentTicket;
	}
	
	
	
	@SuppressWarnings("serial")
	public static class BookingNotFoundException extends Throwable {
		BookingNotFoundException(String message){
			super(message);
		}
	}
	
	
	
}
