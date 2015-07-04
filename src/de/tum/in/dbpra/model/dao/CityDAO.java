package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.tum.in.dbpra.model.bean.CityBean;
import de.tum.in.dbpra.model.bean.CityListBean;


public class CityDAO extends AbstractDAO {

	public CityListBean getCities() throws CityNotFoundException, SQLException {
		String query = "SELECT cty.city_id, cty.name, c.name FROM city cty, country c WHERE cty.country_code=c.country_code";
		
		CityListBean cityList = new CityListBean();
		
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				while (resultSet.next()) {
					CityBean city = new CityBean();
					city.setId(resultSet.getInt(1));
					city.setName(resultSet.getString(2));
					city.setCountryName(resultSet.getString(3));
					cityList.addCityToList(city);
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
		return cityList;
	}
	
	
	public CityBean getCityById(CityBean city) throws CityNotFoundException, SQLException {
		String query = "SELECT cty.city_id, cty.name, c.name FROM city cty, country c WHERE cty.country_code=c.country_code and cty.city_id =?";
				
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			 preparedStatement.setInt(1, city.getId());
			
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				if (resultSet.next()) {
					city.setName(resultSet.getString(2));
					city.setCountryName(resultSet.getString(3));
				} 
				else{
					throw new CityNotFoundException("Database found no city for the given id!");
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
		return city;
	}
	
	@SuppressWarnings("serial")
	public static class CityNotFoundException extends Throwable {
		CityNotFoundException(String message){
			super(message);
		}
	}
}
