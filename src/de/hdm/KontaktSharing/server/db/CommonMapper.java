package de.hdm.KontaktSharing.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;



public abstract class CommonMapper<T> {
	
	/**
	 * Methode mappt ein resultset auf ein objekt und liefert dieses objekt zurück
	 * @param rs
	 * @return null
	 * @throws SQLException
	 */
	protected abstract T createFromResultSet(ResultSet rs) throws SQLException;
	public abstract T findByKey(int id) throws SQLException;
	public abstract Vector<T> findAll() throws SQLException;
	
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
		stmt.executeUpdate(String.format(sqlStatement));
	}

	protected T insert(String sqlStatement, Object ... args) throws SQLException {
		Connection con = DBConnection.connection();
		Statement stmt = con.createStatement();
		List<String> list = Arrays.stream(args).map(arg -> (arg == null) ? "null" : "'" + arg + "'").collect(Collectors.toList());
		String sql = String.format(sqlStatement, list.toArray());
		stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = stmt.getGeneratedKeys();
		if(rs.next()) {
			int id = rs.getInt(1);
			return this.findByKey(id);
		}
		return null;
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
