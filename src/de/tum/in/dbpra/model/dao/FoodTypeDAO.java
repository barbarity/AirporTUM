package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.tum.in.dbpra.model.bean.FoodTypeBean;


public class FoodTypeDAO extends AbstractDAO {

	public FoodTypeBean getFoodType(FoodTypeBean foodType) throws FoodTypeNotFoundException, SQLException {
		String query = "SELECT catering_code, name from foodtype where catering_code=?";
				
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setString(1, foodType.getCateringCode());
			
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				while (resultSet.next()) {
					foodType.setName(resultSet.getString(2));				
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
		return foodType;
	}
	
	
	
	@SuppressWarnings("serial")
	public static class FoodTypeNotFoundException extends Throwable {
		FoodTypeNotFoundException(String message){
			super(message);
		}
	}
}
