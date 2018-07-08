package de.hdm.KontaktSharing.shared.bo;

public class TeilhaberschaftNutzer {
	private int idTeilhaberschaft;
	private int idNutzer;
	
	/**
	 * Nutzer auslesen
	 * @return idNutzer
	 */
	public int getIdNutzer() {
		return idNutzer;
	}
	/**
	 * Nutzerid auslesen
	 * @param idNutzer
	 */
	public void setIdNutzer(int idNutzer) {
		this.idNutzer = idNutzer;
	}
	/**
	 * auslesen der Teilhaberschaft
	 * @return idTeilhaberschaft
	 */
	public int getIdTeilhaberschaft() {
		return idTeilhaberschaft;
	}
	
	/**
	 * Teilhaberschaft setzen
	 * @param idTeilhaberschaft
	 */
	public void setIdTeilhaberschaft(int idTeilhaberschaft) {
		this.idTeilhaberschaft = idTeilhaberschaft;
	}
	
}
