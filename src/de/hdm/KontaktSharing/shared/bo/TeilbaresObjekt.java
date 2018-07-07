package de.hdm.KontaktSharing.shared.bo;

public class TeilbaresObjekt extends BusinessObject {
	
	private int idKontaktliste;
	private int idEigenschaftsauspraegung;
	private int idTeilhaberschaft;
	
	
	public int getIdEigenschaftsauspraegung() {
		return idEigenschaftsauspraegung;
	}
	public void setIdEigenschaftsauspraegung(int idEigenschaftsauspraegung) {
		this.idEigenschaftsauspraegung = idEigenschaftsauspraegung;
	}
	public int getIdTeilhaberschaft() {
		return idTeilhaberschaft;
	}
	public void setIdTeilhaberschaft(int idTeilhaberschaft) {
		this.idTeilhaberschaft = idTeilhaberschaft;
	}
	public int getIdKontaktliste() {
		return idKontaktliste;
	}
	public void setIdKontaktliste(int idKontaktliste) {
		this.idKontaktliste = idKontaktliste;
	}
	
}
