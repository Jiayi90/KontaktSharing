package de.hdm.KontaktSharing.shared.bo;

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
	   * 
	   * @return email
	   */
	  public String getEmail() {
	    return this.email;
	  }
	  
	  /**
	   * Setzen der Email.
	   * 
	   * @param email
	   */
	  public void setEmail(String email) {
	    this.email = email;
	  }
	  
	  
	  

}
