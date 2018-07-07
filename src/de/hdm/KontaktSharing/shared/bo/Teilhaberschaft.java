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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdNutzer() {
		return idNutzer;
	}

	public void setIdNutzer(int idNutzer) {
		this.idNutzer = idNutzer;
	}
	
	

}
