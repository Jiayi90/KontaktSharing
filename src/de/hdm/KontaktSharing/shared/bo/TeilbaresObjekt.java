package de.hdm.KontaktSharing.shared.bo;

public class TeilbaresObjekt extends BusinessObject {
	
	private int idKontaktliste;
	private int idEigenschaftsauspraegung;
	private int idTeilhaberschaft;
	
	/**
	 * auslesen von eigenschaftauspraegung
	 * @return idEigenschaftsauspraegung
	 */
	public int getIdEigenschaftsauspraegung() {
		return idEigenschaftsauspraegung;
	}
	
	/**
	 * aetzen von eigenschaftauspraegung
	 * @param idEigenschaftsauspraegung
	 */
	public void setIdEigenschaftsauspraegung(int idEigenschaftsauspraegung) {
		this.idEigenschaftsauspraegung = idEigenschaftsauspraegung;
	}
	
	/**
	 * auslesen der teilhaberschaftid
	 * @return idTeilhaberschaft
	 */
	public int getIdTeilhaberschaft() {
		return idTeilhaberschaft;
	}
	
	/**
	 * setzen der teilhaberschaftid
	 * @param idTeilhaberschaft
	 */
	public void setIdTeilhaberschaft(int idTeilhaberschaft) {
		this.idTeilhaberschaft = idTeilhaberschaft;
	}
	
	/**
	 * kontaktliste auslesen
	 * @return idKontaktliste
	 */
	public int getIdKontaktliste() {
		return idKontaktliste;
	}
	
	/**
	 * setzen der kontaktliste
	 * @param idKontaktliste
	 */
	public void setIdKontaktliste(int idKontaktliste) {
		this.idKontaktliste = idKontaktliste;
	}
	
}
