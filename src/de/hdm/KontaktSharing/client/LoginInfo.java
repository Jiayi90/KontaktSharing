package de.hdm.KontaktSharing.client;


import java.io.Serializable;

public class LoginInfo implements Serializable {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
private boolean loggedIn = false;
  private String loginUrl = "";
  private String logoutUrl = "";
  private String emailAddress = "";
  private String nickname = "";

  /**
   * 
   * @return loggedIn
   */
  public boolean isLoggedIn() {
    return loggedIn;
  }

  /**
   * 
   * @param loggedIn
   */
  public void setLoggedIn(boolean loggedIn) {
    this.loggedIn = loggedIn;
  }

  /**
   * 
   * @return loginUrl
   */
  public String getLoginUrl() {
    return loginUrl;
  }
  
  /**
   * 
   * @param loginUrl
   */
  
  public void setLoginUrl(String loginUrl) {
    this.loginUrl = loginUrl;
  }
  /**
   * 
   * @return logoutUrl
   */
  public String getLogoutUrl() {
    return logoutUrl;
  }
  /**
   * 
   * @param logoutUrl
   */
  public void setLogoutUrl(String logoutUrl) {
    this.logoutUrl = logoutUrl;
  }
  /**
   * 
   * @return emailAddress
   */
  public String getEmailAddress() {
    return emailAddress;
  }
  /**
   * 
   * @param emailAddress
   */
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }
  /**
   * 
   * @return nickname
   */
  public String getNickname() {
    return nickname;
  }
  /**
   * 
   * @param nickname
   */
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
}
