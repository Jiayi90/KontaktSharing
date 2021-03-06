package de.hdm.KontaktSharing.shared.bo;

import java.util.Comparator;
import java.util.Date;
import java.util.Vector;

/**
 * Realisierung eines exemplarischen Kunden.
 * 
 */

public class Kontakt extends BusinessObject implements Comparator<Kontakt> {

	private static final long serialVersionUID = 1L;

	/**
	 * Das Erzeugungsdatum des Kontaktes
	 */
	private Date erzeugungsdatum;

	/**
	 * Das Modufikationsdatum des Kontaktes
	 */
	private Date modifikationsdatum;
	
	private int idNutzer;
	
	private Vector<Eigenschaftauspraegung> eigenschaftauspraegung;
	
	private boolean wasShared;
	
	private Integer teilhaberschaftId;

	/**
	 * No Argument Constructor
	 */

	public Kontakt() {
		super();
		this.erzeugungsdatum = new Date();
		this.modifikationsdatum = new Date();
	}

	/**
	 * Auslesen vom Erzeugungsdatum
	 */
	/**
	 * 
	 * @return erzeugungsdatum
	 */
	public Date getErzeugungsdatum() {
		return this.erzeugungsdatum;
	}

	/**
	 * Auslesen vom Modifikationsdatum
	 */
	/**
	 * 
	 * @return modifikationsdatum
	 */
	public Date getModifikationsdatum() {
		return this.modifikationsdatum;
	}

	
	/**
	 * Setzen des Erzeugungsdatum
	 *
	 *
	 * 
	 * @param erzeugungsdatum
	 */
	public void setErzeugungsdatum(Date erzeugungsdatum) {
		this.erzeugungsdatum = erzeugungsdatum;
	}

	/**
	 * Setzen des Modifikationsdatum
	 */
	public void setModifikationsdatum(Date modifikationsdatum) {
		this.modifikationsdatum = modifikationsdatum;
	}
	/**
	 * <nutzerid auslesen
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
	 * eigenschaftauspraegung auslesen
	 * @return eigenschaftauspraegung
	 */
	public Vector<Eigenschaftauspraegung> getEigenschaftauspraegung() {
		return eigenschaftauspraegung;
	}
	/**
	 * Name auslesen
	 * @return auspraegung
	 */
	public String getName() {
		for(int i = 0; i<eigenschaftauspraegung.size(); i++) {
			Eigenschaftauspraegung auspraegung = eigenschaftauspraegung.get(i);
			if(auspraegung.getIdEigenschaft() == 1) {
				return auspraegung.getText();
			}
		}
		return null;
	}
	/**
	 * Eigenschaftauspraegung setzen
	 * @param eigenschaftauspraegung
	 */
	public void setEigenschaftauspraegung(Vector<Eigenschaftauspraegung> eigenschaftauspraegung) {
		this.eigenschaftauspraegung = eigenschaftauspraegung;
	}
	/**
	 * 
	 */
	@Override
	public int compare(Kontakt arg0, Kontakt arg1) {
		return arg0.getName().toLowerCase().compareTo(arg1.getName().toLowerCase());
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