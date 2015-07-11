package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.tum.in.dbpra.model.bean.CurrencyBean;
import de.tum.in.dbpra.model.bean.CurrencyListBean;


public class CurrencyDAO extends AbstractDAO {

	public CurrencyListBean getCurrencies() throws CurrencyNotFoundException, SQLException {
		String query = "SELECT currency_code, name, symbol FROM currency";
		
		CurrencyListBean currencyList = new CurrencyListBean();
		
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				while (resultSet.next()) {
					CurrencyBean currency = new CurrencyBean();
					currency.setCurrencyCode(resultSet.getString(1));
					currency.setName(resultSet.getString(2));
					currency.setSymbol(resultSet.getString(3));
					currencyList.addCurrencyToList(currency);
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
		return currencyList;
	}
	
	
	public CurrencyBean getCurrencyByCurrencyCode(CurrencyBean currency) throws CurrencyNotFoundException, SQLException {
		String query = "SELECT currency_code, name, symbol FROM currency WHERE currency_code =?";
				
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			 preparedStatement.setString(1, currency.getCurrencyCode());
			
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				if (resultSet.next()) {
					currency.setName(resultSet.getString(2));
					currency.setSymbol(resultSet.getString(3));
				} 
				else{
					throw new CurrencyNotFoundException("Database found no currency for the given id!");
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
		return currency;
	}
	
	@SuppressWarnings("serial")
	public static class CurrencyNotFoundException extends Throwable {
		CurrencyNotFoundException(String message){
			super(message);
		}
	}
}
