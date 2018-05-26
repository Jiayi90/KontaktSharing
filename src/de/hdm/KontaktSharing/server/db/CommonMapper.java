package de.hdm.KontaktSharing.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;



public abstract class CommonMapper<T> {
	
	/**
	 * Methode mappt ein resultset auf ein objekt und liefert dieses objekt zurück
	 * @param rs
	 * @return null
	 * @throws SQLException
	 */
	protected abstract T createFromResultSet(ResultSet rs) throws SQLException;
	Logger logger = java.util.logging.Logger.getLogger("NameOfYourLogger");
	
	/**
	 * liefert anhand eines sql statements ein objekt zurück
	 * @param sqlStatement
	 * @return null
	 * @throws SQLException
	 */
	protected T findObject(String sqlStatement) throws SQLException {
		Connection con = DBConnection.connection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sqlStatement);
		if (rs.next()) {
			return createFromResultSet(rs);
		}
		return null;
	}
	/**
	 * liefert anhand eines sql statements ein vektor mit objekten zurück
	 * @param sqlStatement
	 * @throws SQLException
	 */
	protected void excecute(String sqlStatement) throws SQLException {
		Connection con = DBConnection.connection();
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sqlStatement);
	}
	
	/**
	 * führt ein sql statement aus, wie zb update, delete, insert
	 * @param sqlStatement
	 * @return result
	 * @throws SQLException
	 */
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
