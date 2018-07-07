package de.hdm.KontaktSharing.shared.bo;

import java.util.List;

/**
 * Realisierung einer exemplarischen Kontaktliste.
 * 
 */

public class Kontaktliste extends BusinessObject{

	private static final long serialVersionUID = 1L;

	/**
	 * Der Kontaktlistenname der Kontaktliste.
	 */
	private String kontaktlistenname = "";
	
	/**
	 * In der Kontaktliste werden Kontakte gespeichert
	 */
	private List<Kontakt> kontakte;
	
	/**
	 * Die Id des Nutzers, der die Liste erstellt hat
	 */
	private int nutzerId;
	
	private boolean wasShared;
	
	
	private Integer teilhaberschaftId;

	/**
	 * No-Argument Constructor
	 */
	public Kontaktliste() {
		super();
	}
	
	/**
	 * Auslesen der Kontakte aus der Liste
	 * @return kontakte
	 */
	public List<Kontakt> getKontakte() {
		return kontakte;
	}
	
	/**
	 * Setzen der Kontakte in der Liste
	 * @param kontakte
	 */
	public void setKontakte(List<Kontakt> kontakte) {
		this.kontakte = kontakte;
	}

	/**
	 * Auslesen des Kontaktlistenname.
	 */
	public String getKontaktlistenname() {
		return this.kontaktlistenname;
	}

	/**
	 * Setzen des Kontaktlistennamen.
	 */
	public void setKontaktlistenname(String kontaktlistenname) {
		this.kontaktlistenname = kontaktlistenname;
	}

	public int getNutzerId() {
		return nutzerId;
	}

	public void setNutzerId(int nutzerId) {
		this.nutzerId = nutzerId;
	}

	public boolean isWasShared() {
		return wasShared;
	}

	public void setWasShared(boolean wasShared) {
		this.wasShared = wasShared;
	}

	public boolean isShared() {
		return teilhaberschaftId != null;
	}


	public Integer getTeilhaberschaftId() {
		return teilhaberschaftId;
	}

	public void setTeilhaberschaftId(Integer teilhaberschaftId) {
		this.teilhaberschaftId = teilhaberschaftId;
	}

	
}
