package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.tum.in.dbpra.model.bean.AirlineBean;
import de.tum.in.dbpra.model.bean.AirlineListBean;


public class AirlineDAO extends AbstractDAO {

	public AirlineListBean getAirlines() throws AirlineNotFoundException, SQLException {
		//TODO: change "callsign" to "name for the real database"
		String query = "SELECT code, callsign from airline";
		
		AirlineListBean airlineList = new AirlineListBean();
		
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				while (resultSet.next()) {
					AirlineBean airline = new AirlineBean();
					airline.setCode(resultSet.getString(1));
					airline.setName(resultSet.getString(2));
					airlineList.addAirlineToList(airline);
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
		return airlineList;
	}
	
	
	public AirlineBean getAirlineById(AirlineBean airline) throws AirlineNotFoundException, SQLException {
		//TODO also change callsign -> name
		String query = "SELECT code, callsign from airline where code =?";
				
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			 preparedStatement.setString(1, airline.getCode());
			
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				if (resultSet.next()) {
					airline.setName(resultSet.getString(2));
				} 
				else{
					throw new AirlineNotFoundException("Database found no airline for the given id!");
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
		return airline;
	}
	
	@SuppressWarnings("serial")
	public static class AirlineNotFoundException extends Throwable {
		AirlineNotFoundException(String message){
			super(message);
		}
	}
}
