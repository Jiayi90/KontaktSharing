package de.hdm.KontaktSharing.shared.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Realisierung eines exemplarischen Nutzers. 
 * 
 */

public class Nutzer extends BusinessObject{
	
	private static final long serialVersionUID = 1L;

	  /**
	   * Die Email des Nutzers.
	   */
	  private String email = "";
	  
	  /**
	   * No-Argument Constructor
	   */
	  public Nutzer() {
		    super();
		  }
	  /**
	   * Falls Fehler beim erstellen eines Nutzers auftritt, wird eine Exception geworfen
	   * @param rs
	   * @throws SQLException
	   */
	  public Nutzer(ResultSet rs) throws SQLException {
		  super(rs.getInt("idNutzer"));
		  this.setEmail(rs.getString("email"));
	  }
	  /**
	   * Auslesen der Email.
	   */
	  public String getEmail() {
	    return this.email;
	  }
	  
	  /**
	   * Setzen der Email.
	   */
	  public void setEmail(String email) {
	    this.email = email;
	  }
	  
	  
	  

}
