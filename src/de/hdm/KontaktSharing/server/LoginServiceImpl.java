package de.hdm.KontaktSharing.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import de.hdm.KontaktSharing.client.LoginInfo;
import de.hdm.KontaktSharing.shared.LoginService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {


	private static final long serialVersionUID = 1L;
	
	public LoginInfo login(String requestUri) {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		LoginInfo loginInfo = new LoginInfo();
		
		

			if (user != null) {
				loginInfo.setLoggedIn(true);
				loginInfo.setEmailAddress(user.getEmail());
				loginInfo.setNickname(user.getNickname());
				loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
			} else {
				loginInfo.setLoggedIn(false);
				loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
			}
			
			return loginInfo;
	  }
}
	

//		} catch (Exception e) {
			
//			System.out.println("##################### " + requestUri); 
//			loginInfo.setLoggedIn(false);
//			loginInfo.setLoginUrl(requestUri);

//			loginInfo.setLoggedIn(true);
//            loginInfo.setEmailAddress("dummy@hdm-stuttgart.de");
//            loginInfo.setNickname("Dummy");
		
