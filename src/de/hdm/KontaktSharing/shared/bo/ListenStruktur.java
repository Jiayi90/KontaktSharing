package de.hdm.KontaktSharing.shared.bo;

public class ListenStruktur {
	private int idKontaktliste;
	private int idKontakt;
	
	/**
	 * auslesen der kontaktlistenid
	 * @return idKontaktliste
	 */
	public int getIdKontaktliste() {
		return idKontaktliste;
	}
	
	/**
	 * setzen der kontaktlistenid
	 * @param idKontaktliste
	 */
	public void setIdKontaktliste(int idKontaktliste) {
		this.idKontaktliste = idKontaktliste;
	}
	/**
	 * kontaktid auslesen
	 * @return idKontakt
	 */
	public int getIdKontakt() {
		return idKontakt;
	}
	/**
	 * Kontaktid auslesen
	 * @param idKontakt
	 */
	public void setIdKontakt(int idKontakt) {
		this.idKontakt = idKontakt;
	}
}
