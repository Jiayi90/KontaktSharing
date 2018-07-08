 package de.hdm.KontaktSharing.shared.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Realisierung einer exemplarischen Teilhaberschaft.
 * 
 */

public class Teilhaberschaft extends BusinessObject {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private int idNutzer;

	/**
	 * No Argument Constructor
	 */
	public Teilhaberschaft() {
		super();
	}
	/**
	 * auslesen von name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * setzen von name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * auslesen von Nutzerid
	 * @return idNutzer
	 */
	public int getIdNutzer() {
		return idNutzer;
	}
	/**
	 * setzen von Nutzerid
	 * @param idNutzer
	 */
	public void setIdNutzer(int idNutzer) {
		this.idNutzer = idNutzer;
	}
	
	

}
