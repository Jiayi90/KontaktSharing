package de.hdm.KontaktSharing.shared.bo;

import java.util.Date;

/**
 * Realisierung einer Eigenschaftauspr�gung. Eine Eigenschaftsauspr�gung besitzt
 * Text, Datum und Zahl.
 */

public class Eigenschaftauspraegung extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Text von Eigenschaftsauspr�gung
	 */
	private String text = "";
	
	/**
	 * Zahl von Eigenschaftsauspr�gung
	 */
	private int zahl = 0;
	
	/**
	 * Datum von Eigenschaftsauspr�gung
	 */
	 private Date datum;//nochmal nach schauen
	 
	 
	 private int idEigenschaft;
	 private int idKontakt;
	 
	 public Eigenschaftauspraegung() {
		    super();
		  }
	 
	 /**
	   * Auslesen des Textes.
	   */
	  public String getText() {
	    return this.text;
	    
	  }
	  
	  /**
	   * Auslesen der Zahl
	   */
	  
	  public int getZahl() {
		  return this.zahl;
	  }
	  
	  /**
	   * Auslesen vom Datum
	   */
	  
	  public Date getDatum() {
		  return this.datum;
	  }
	  
	  /**
	   * Setzen des Textes der Eigenschaftsauspr�gung
	   */
	  public void setText(String string) {
	    this.text = string;
	  }
	  
	  /**
	   * Setzen der Zahl der Eigenschaftsauspr�gung
	   */
	  public void setZahl(int i) {
		  this.zahl = i;
	  }
	  
	  /**
	   * Setzen des Datums der Eigenschaftsauspr�gung
	   */
	  public void setDatum (Date datum) {
		  this.datum = datum;
	  }

	public int getIdEigenschaft() {
		return idEigenschaft;
	}

	public void setIdEigenschaft(int idEigenschaft) {
		this.idEigenschaft = idEigenschaft;
	}

	public int getIdKontakt() {
		return idKontakt;
	}

	public void setIdKontakt(int idKontakt) {
		this.idKontakt = idKontakt;
	}
	  
}
