package de.hdm.KontaktSharing.shared.bo;


import java.io.Serializable;

public class LoginInfo implements Serializable {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
	 * 
	 */
	
private boolean loggedIn = false;
  private String loginUrl;
  private String logoutUrl;
  private String emailAddress;
  private String nickname;
  
  /**
   * ueberpruefung 
   * @return loggedIn
   */
  public boolean isLoggedIn() {
    return loggedIn;
  }
  /**
   * login setzen
   * @param loggedIn
   */
  public void setLoggedIn(boolean loggedIn) {
    this.loggedIn = loggedIn;
  }
  /**
   * loginUrl auslesen
   * @return loginUrl
   */
  public String getLoginUrl() {
    return loginUrl;
  }
  /**
   * loginUrl setzen
   * @param loginUrl
   */
  public void setLoginUrl(String loginUrl) {
    this.loginUrl = loginUrl;
  }
  /**
   * auslesen von logoutUrl
   * @return logoutUrl
   */
  public String getLogoutUrl() {
    return logoutUrl;
  }
  /**
   * logoutUrl auslesen
   * @param logoutUrl
   */
  public void setLogoutUrl(String logoutUrl) {
    this.logoutUrl = logoutUrl;
  }
  /**emailadresse auslesen
   * 
   * @return emailAddress
   */
  public String getEmailAddress() {
    return emailAddress;
  }
  /**
   * emailadresse auslesen
   * @param emailAddress
   */
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }
  /**
   * nickname auslesen
   * @return nickname
   */
  public String getNickname() {
    return nickname;
  }
  /**
   * Nickname auslesen
   * @param nickname
   */
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
}
