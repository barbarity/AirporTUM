package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class AbstractDAO {

    public Connection getConnection() throws SQLException {
    	try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		};
        return DriverManager.getConnection("jdbc:postgresql://localhost/airportTUM", "barbarity", "");
    }
}
