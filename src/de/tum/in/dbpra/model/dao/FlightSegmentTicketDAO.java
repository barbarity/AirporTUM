package de.tum.in.dbpra.model.dao;

import java.math.BigDecimal;
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

	private Connection connection;
	
	public FlightSegmentTicketDAO(Connection con) {
		this.connection=con;
	}
	
	public FlightSegmentTicketDAO() {
		this.connection=null;
	}
	
	
	
	public ArrayList<FlightSegmentTicketBean> getFlightSegmentTicketsFromBooking(BookingBean booking) throws FlightSegmentTicketNotFound, FoodTypeDAO.FoodTypeNotFoundException, FlightDAO.FlightNotFoundException,SQLException {
		
		
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
	
	public BookingBean addNewFlightSegmentTicket(BookingBean booking, Connection connection) throws FlightSegmentTicketNotFound, SQLException {
		String query = "select coalesce(max(fst_id),0)+1 from flightsegmentticket";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		try (ResultSet resultSet = preparedStatement.executeQuery();) {
			if (resultSet.next()) {
				for(int i=0; i<booking.getFlightSegmentTicketList().size(); i++){
					FlightSegmentTicketBean fst = booking.getFlightSegmentTicketList().get(i);
					fst.setId(resultSet.getInt(1)+i);
					query = "insert into flightsegmentticket (fst_id, booking_id, flight_id, catering_code, class, price, seatNr) values(?, ?, ?, ?, ?::airplaneclass, ?, ?)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(1, fst.getId());
					preparedStatement.setInt(2, booking.getId());
					preparedStatement.setInt(3, fst.getFlight().getFlightId());
					preparedStatement.setString(4, "NMML");
					preparedStatement.setString(5, "economy");
					preparedStatement.setBigDecimal(6, new BigDecimal("2.0"));
					preparedStatement.setInt(7, fst.getSeatNr());
					try {
						int rows = preparedStatement.executeUpdate();
						if(rows == 0){
							return null;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return booking;
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
	public static class FlightSegmentTicketNotFound extends Throwable {
		FlightSegmentTicketNotFound(String message){
			super(message);
		}
	}
	
	
	
}
