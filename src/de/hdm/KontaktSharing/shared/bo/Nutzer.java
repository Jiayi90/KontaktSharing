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
