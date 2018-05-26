 package de.hdm.KontaktSharing.shared.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Realisierung einer exemplarischen Teilhaberschaft.
 * 
 */

public class Teilhaberschaft extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * No Argument Constructor
	 */
	public Teilhaberschaft() {
		super();
	}
	
	/**
	 * Falls Fehler beim erstellen einer Teilhaberschaft auftritt, wird eine Exception geworfen
	 * @param rs
	 * @throws SQLException
	 */
	public Teilhaberschaft(ResultSet rs) throws SQLException {
		super(rs.getInt("idTeilhaberschaft"));
		
	}

}
