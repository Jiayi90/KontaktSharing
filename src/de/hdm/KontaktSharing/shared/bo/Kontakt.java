package de.hdm.KontaktSharing.shared.bo;

import java.sql.SQLException;
import java.util.Date;

import java.sql.ResultSet;

/**
 * Realisierung eines exemplarischen Kunden. 
 * 
 */

public class Kontakt extends BusinessObject implements TeilbaresObjekt{
	
	private static final long serialVersionUID = 1L;

	  /**
	   * Der Name des Kontaktes.
	   */
	  private String name = "";
	  
	  /**
	   * Das Erzeugungsdatum des Kontaktes
	   */
	  private Date erzeugungsdatum;
	  
	  /**
	   * Das Modufikationsdatum des Kontaktes
	   */
	  private Date modifikationsdatum;
	  /**
	   * No Argument Constructor
	   */
	  
	  public Kontakt() {
		    super();
		  }
	  /**
	   * Falls Fehler beim erstellen eines Kontaktes auftritt, wird eine Exception geworfen
	   * @param rs
	   * @throws SQLException
	   */
	  public Kontakt(ResultSet rs) throws SQLException {
		  super(rs.getInt("idKontakt"));
		  this.setName(rs.getString("name"));
		  this.setErzeugungsdatum(rs.getDate("erzeugungsdatum"));
		  this.setModifikationsdatum(rs.getDate("modifikationsdatum"));
		  
	  }
	  
	  /**
	   * Auslesen des Namens.
	   */
	  public String getName() {
	    return this.name;
	  }
	  
	  /**
	   * Auslesen vom Erzeugungsdatum
	   */
	  
	  public Date getErzeugungsdatum() {
		  return this.erzeugungsdatum;
	  }
	  
	  /**
	   * Auslesen vom Modifikationsdatum
	   */
	  
	  public Date getModifikationsdatum() {
		  return this.modifikationsdatum;
	  }
	  
	  /**
	   * Setzen des Namens.
	   */
	  public void setName(String name) {
	    this.name = name;
	  }
	  
	  /**
	   * Setzen des Erzeugungsdatum
	   */
	  public void setErzeugungsdatum (Date erzeugungsdatum) {
		  this.erzeugungsdatum = erzeugungsdatum;
	  }
	  
	  /**
	   * Setzen des Modifikationsdatum
	   */
	  public void setModifikationsdatum (Date modifikationsdatum) {
		  this.modifikationsdatum = modifikationsdatum;
	  }

}
