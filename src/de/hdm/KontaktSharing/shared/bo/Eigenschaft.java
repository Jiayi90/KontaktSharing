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
	private String bezeichnung;

	/**
	 * Typ der Eigenschaft
	 */
	private Typ typ;
	
	/**
	 * 
	 */
	private boolean mehrfach;

	/**
	 * Auslesen der Bezeichnung aus Eigenschaft
	 * 
	 */
	/**
	 * 
	 * @return bezeichnung
	 */
	public String getBezeichnung() {
		return this.bezeichnung;
	}


	/**
	 * Auslesen vom Typ der Eigenschaft
	 */
	/**
	 * 
	 * @return typ
	 */
	public Typ getTyp() {
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
	/**
	 * 
	 * @param bezeichnung
	 */
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	/**
	 * Setzen des Typs.
	 */
	public void setTyp(Typ typ) {
		this.typ = typ;
	}
	/**
	 * 
	 * @param typ
	 */
	public void setTyp(String typ) {
		typ = typ.toLowerCase();
		if(typ.equals("string")) {
			this.typ = Typ.STRING;
		} else if(typ.equals("int")) {
			this.typ = Typ.INT;
		} else if(typ.equals("date")) {
			this.typ = Typ.DATE;
		}
	}

	/**
	 * 
	 * @return mehrfach
	 */
	public boolean isMehrfach() {
		return mehrfach;
	}

	/**
	 * 
	 * @param mehrfach
	 */
	public void setMehrfach(boolean mehrfach) {
		this.mehrfach = mehrfach;
	}

}
