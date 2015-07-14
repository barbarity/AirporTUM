package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.tum.in.dbpra.model.bean.BookingBean;
import de.tum.in.dbpra.model.bean.LuggageBean;


public class LuggageDAO extends AbstractDAO {
	
	private Connection connection;
	
	public LuggageDAO(Connection con){
		this.connection=con;
	}
	public LuggageDAO(){
		this.connection=null;
	}

	public ArrayList<LuggageBean> getLuggageItemsOfBooking(BookingBean booking) throws LuggageNotFoundException, PersonDAO.PersonNotFoundException, SQLException {
		String query = "SELECT luggage_id, booking_id, weight, height, width, length, additionalcost, registeredAtBooking  from luggage where booking_id = ? order by luggage_id";
				
		ArrayList<LuggageBean> luggageItems = new ArrayList<LuggageBean>();
		/*try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			 */
		PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, booking.getId());
			
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				while (resultSet.next()) {
					LuggageBean luggageItem = new LuggageBean();
					luggageItem.setId(resultSet.getInt(1));
					luggageItem.setWeight(resultSet.getInt(3));
					luggageItem.setHeight(resultSet.getInt(4));
					luggageItem.setWidth(resultSet.getInt(5));
					luggageItem.setLength(resultSet.getInt(6));
					luggageItem.setAdditionalPrice(resultSet.getBigDecimal(7));
					luggageItem.setRegisteredAtBooking(resultSet.getBoolean(8));

					luggageItems.add(luggageItem);
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
		return luggageItems;
	}
	
	
	
	public boolean deleteLuggageById(LuggageBean luggage) throws LuggageNotFoundException, SQLException {
		String query = "delete from luggage where luggage_id = ?";
				
		/*try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			 */
		PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, luggage.getId());
			try {
				int rows = preparedStatement.executeUpdate();
				if (rows == 1) return true;
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
		return false;
	}

	
	public LuggageBean getLuggageById(LuggageBean luggageItem) throws LuggageNotFoundException, PersonDAO.PersonNotFoundException, SQLException {
		String query = "select luggage_id, booking_id, weight, height, width, length, additionalcost, registeredAtBooking from luggage where luggage_id = ?";
				
		/*try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			 */

		PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, luggageItem.getId());
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				if (resultSet.next()) {
					luggageItem.setBookingId(resultSet.getInt(2));
					luggageItem.setWeight(resultSet.getInt(3));
					luggageItem.setHeight(resultSet.getInt(4));
					luggageItem.setWidth(resultSet.getInt(5));
					luggageItem.setLength(resultSet.getInt(6));
					luggageItem.setAdditionalPrice(resultSet.getBigDecimal(7));
					luggageItem.setRegisteredAtBooking(resultSet.getBoolean(8));
		
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
		return luggageItem;
	}
	
	
	public boolean updateLuggageById(LuggageBean luggageItem) throws LuggageNotFoundException, SQLException {
		String query = "update luggage set weight=?, height=?, width=?, length=?, additionalcost = ? where luggage_id = ?";
				
		/*try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			 */
		PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, luggageItem.getWeight());
			preparedStatement.setInt(2, luggageItem.getHeight());
			preparedStatement.setInt(3, luggageItem.getWidth());
			preparedStatement.setInt(4, luggageItem.getLength());
			preparedStatement.setBigDecimal(5, luggageItem.getAdditionalPrice());
			preparedStatement.setInt(6, luggageItem.getId());
			try {
				int rows = preparedStatement.executeUpdate();
				if (rows == 1) return true;
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
		return false;
	}
	
	
	public LuggageBean addNewLuggageItem(LuggageBean luggageItem) throws LuggageNotFoundException, SQLException {
		String query = "insert into luggage values((select max(luggage_id)+1 from luggage), ?, ?, ?, ?, ?, ?, ?)";
		/*try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			 */
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, luggageItem.getBookingId());
			preparedStatement.setInt(2, luggageItem.getWeight());
			preparedStatement.setInt(3, luggageItem.getHeight());
			preparedStatement.setInt(4, luggageItem.getWidth());
			preparedStatement.setInt(5, luggageItem.getLength());
			preparedStatement.setBigDecimal(6, luggageItem.getAdditionalPrice());
			preparedStatement.setBoolean(7, luggageItem.getRegisteredAtBooking());
			try {
				int rows = preparedStatement.executeUpdate();
				if (rows == 1){
					query = "select max(luggage_id) from luggage";
					preparedStatement = connection.prepareStatement(query);
					try (ResultSet resultSet = preparedStatement.executeQuery();) {
						if (resultSet.next()) {
							luggageItem.setId(resultSet.getInt(1));
						} 
						resultSet.close();
						return luggageItem;

					} catch (SQLException e) {
						e.printStackTrace();
						throw e;
					}
				}
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
		return null;
	}
	
	
	
	public LuggageBean getOldLuggageItem(LuggageBean luggageItem) throws LuggageNotFoundException, SQLException {
		String query = "select luggage_id, booking_id, weight, height, width, length, additionalcost, registeredAtBooking from luggage where luggage_id = ?";
		
		LuggageBean oldLuggageItem = new LuggageBean();
		
		oldLuggageItem.setId(luggageItem.getId());
		
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, oldLuggageItem.getId());
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				if (resultSet.next()) {
					oldLuggageItem.setBookingId(resultSet.getInt(2));
					oldLuggageItem.setWeight(resultSet.getInt(3));
					oldLuggageItem.setHeight(resultSet.getInt(4));
					oldLuggageItem.setWidth(resultSet.getInt(5));
					oldLuggageItem.setLength(resultSet.getInt(6));
					oldLuggageItem.setAdditionalPrice(resultSet.getBigDecimal(7));
					oldLuggageItem.setRegisteredAtBooking(resultSet.getBoolean(8));
				} 
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
	}
		return oldLuggageItem;

	}
	
	@SuppressWarnings("serial")
	public static class LuggageNotFoundException extends Throwable {
		LuggageNotFoundException(String message){
			super(message);
		}
	}
}
