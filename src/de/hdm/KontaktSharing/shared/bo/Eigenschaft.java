package de.hdm.KontaktSharing.shared.bo;

/**
 * Realisierung einer exemplarischen Eigenschaftklasse.
 * 
 */

public class Eigenschaft extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Die Bezeichnung der Eigenschaft.
	 */
	private String bezeichnung = "";

	/**
	 * Typ der Eigenschaft
	 */
	private String typ = "";

	/**
	 * Auslesen der Bezeichnung aus Eigenschaft
	 */
	public String getBezeichnung() {
		return this.bezeichnung;
	}

	/**
	 * Auslesen vom Typ der Eigenschaft
	 */
	public String getTyp() {
		return this.typ;
	}
	/**
	 * No Argument Constructor
	 */
	public Eigenschaft() {
	    super();
	  }

	/**
	 * Setzen der Bezeichnung.
	 */
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	/**
	 * Setzen des Typs.
	 */
	public void setTyp(String typ) {
		this.typ = typ;
	}

}
