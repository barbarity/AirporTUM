package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;


import de.tum.in.dbpra.model.bean.PersonBean;


public class PersonDAO extends AbstractDAO {

	
	public PersonBean getCustomerById(PersonBean person) throws PersonNotFoundException, SQLException {
		
		String query = "SELECT 	person_id, firstName, lastName, passportId, gender, title, address, email, phone, birthdate, password, salt from person where person_id = ?";
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
				preparedStatement.setInt(1, person.getId());
				try (ResultSet resultSet = preparedStatement.executeQuery();) {
					if (resultSet.next()) {
						//it is a checkin-worker
						person.setFirstName(resultSet.getString(2));
						person.setLastName(resultSet.getString(3));
						person.setPassportId(resultSet.getString(4));
						person.setGender(resultSet.getString(5));
						person.setTitle(resultSet.getString(6));
						person.setAddress(resultSet.getString(7));
						person.setEmail(resultSet.getString(8));
						person.setPhone(resultSet.getString(9));
						person.setBirthDate(resultSet.getDate(10));
						person.setPassword(resultSet.getString(11));
						person.setSalt(resultSet.getString(12));					
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
		
		return person;
		
		
	}
	
	public PersonBean getPerson(String email, String passwordHashed) throws PersonNotFoundException, SQLException {
		
		String querySalt = "SELECT salt from person where email=?";
		
		String salt = "";
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(querySalt);) {
				preparedStatement.setString(1, email);
				try (ResultSet resultSet = preparedStatement.executeQuery();) {
					if (resultSet.next()) {
						salt = resultSet.getString(1);
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
		
					
		
		String query = "SELECT 	person_id, firstName, lastName, passportId, gender, title, address, email, phone, birthdate, password, salt from person where email=? and password = ?";
	
				
		PersonBean person = null;
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, passwordHashed);

			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				if (resultSet.next()) {
					person = new PersonBean();
					person.setId(resultSet.getInt(1));
					person.setFirstName(resultSet.getString(2));
					person.setLastName(resultSet.getString(3));
					person.setPassportId(resultSet.getString(4));
					person.setGender(resultSet.getString(5));
					person.setTitle(resultSet.getString(6));
					person.setAddress(resultSet.getString(7));
					person.setEmail(resultSet.getString(8));
					person.setPhone(resultSet.getString(9));
					person.setBirthDate(resultSet.getDate(10));
					person.setPassword(resultSet.getString(11));
					person.setSalt(resultSet.getString(12));
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
		
		return person;
	}
	
	
	
	
	public PersonBean getCheckInWorker(String email, String passwordHashed) throws PersonNotFoundException, SQLException {
		
		
		String querySalt = "SELECT * from checkinworker, person  where cw_id=person_id and email= ?";
		
		PersonBean checkinworker = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(querySalt);) {
				preparedStatement.setString(1, email);
				try (ResultSet resultSet = preparedStatement.executeQuery();) {
					if (resultSet.next()) {
						//it is a checkin-worker
						checkinworker = getPerson(email, passwordHashed);
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
		
		return checkinworker;
	}
	
	
	public String getSha256Hash(String email, String password) throws PersonNotFoundException, SQLException{
		
String querySalt = "SELECT * from person where email= ?";
				String hash=null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(querySalt);) {
				preparedStatement.setString(1, email);
				try (ResultSet resultSet = preparedStatement.executeQuery();) {
					if (resultSet.next()) {
						String salt = resultSet.getString(12);
						hash = sha256(password+salt);
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
		
		return hash;
		
	}
	
	
	public PersonBean addNewPerson(String firstName, String lastName, String passportId, String gender, String title, String address, String email, String phone, Date birthdate, String password, String salt) throws PersonNotFoundException, SQLException {
		
		
		String query = "INSERT INTO person (person_id, firstName, lastName, passportId, gender, title, address, email, phone, birthdate, password, salt)" +
				" VALUES ((SELECT max(person_id)+1 from person), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
				
		PersonBean person = null;
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, passportId);
			preparedStatement.setString(4, gender);
			preparedStatement.setString(5, title);
			preparedStatement.setString(6, address);
			preparedStatement.setString(7, email);
			preparedStatement.setString(8, phone);
			preparedStatement.setDate(9, birthdate);
			preparedStatement.setString(10, password);
			preparedStatement.setString(11, salt);
			
			if (preparedStatement.executeUpdate() == 1){
				//successful
				person = getPerson(email, password);				
			}
			else{
				throw new SQLException();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return person;
	}
	
	
	
	@SuppressWarnings("serial")
	public static class PersonNotFoundException extends Throwable {
		PersonNotFoundException(String message){
			super(message);
		}
	}
	
	
	public static String sha256(String base) {
		try{
		    MessageDigest digest = MessageDigest.getInstance("SHA-256");
		    byte[] hash = digest.digest(base.getBytes("UTF-8"));
		    StringBuffer hexString = new StringBuffer();

		    for (int i = 0; i < hash.length; i++) {
		        String hex = Integer.toHexString(0xff & hash[i]);
		        if(hex.length() == 1) hexString.append('0');
		        hexString.append(hex);
		    }

		return hexString.toString();
		} catch(Exception ex){
		throw new RuntimeException(ex);
		}
		}
	
	
}
