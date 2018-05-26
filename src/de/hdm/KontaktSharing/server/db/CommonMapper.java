package de.hdm.KontaktSharing.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;



public abstract class CommonMapper<T> {
	
	protected abstract T createFromResultSet(ResultSet rs) throws SQLException;
	Logger logger = java.util.logging.Logger.getLogger("NameOfYourLogger");
	
	protected T findObject(String sqlStatement) throws SQLException {
		Connection con = DBConnection.connection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sqlStatement);
		if (rs.next()) {
			return createFromResultSet(rs);
		}
		return null;
	}
	
	protected void excecute(String sqlStatement) throws SQLException {
		Connection con = DBConnection.connection();
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sqlStatement);
	}
	
	protected Vector<T> findVector(String sqlStatement) throws SQLException {
		Connection con = DBConnection.connection();
		Vector<T> result = new Vector<T>();

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sqlStatement);
		while (rs.next()) {
			T n = createFromResultSet(rs);
			result.addElement(n);
		}
		return result;
	}

}
