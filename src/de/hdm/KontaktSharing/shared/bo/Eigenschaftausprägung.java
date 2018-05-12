package de.hdm.KontaktSharing.shared.bo;

import java.util.Date;

/**
 * Realisierung einer Eigenschaftausprägung. Eine Eigenschaftsausprägung besitzt
 * Text, Datum und Zahl.
 */

public class Eigenschaftausprägung extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Text von Eigenschaftsausprägung
	 */
	private String text = "";
	
	/**
	 * Zahl von Eigenschaftsausprägung
	 */
	private int zahl = 0;
	
	/**
	 * Datum von Eigenschaftsausprägung
	 */
	 private Date datum;//nochmal nach schauen
	 
	 public Eigenschaftausprägung() {
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
	   * Setzen des Textes der Eigenschaftsausprägung
	   */
	  public void setText(String string) {
	    this.text = string;
	  }
	  
	  /**
	   * Setzen der Zahl der Eigenschaftsausprägung
	   */
	  public void setZahl(int i) {
		  this.zahl = i;
	  }
	  
	  /**
	   * Setzen des Datums der Eigenschaftsausprägung
	   */
	  public void setDatum (Date datum) {
		  this.datum = datum;
	  }
}
