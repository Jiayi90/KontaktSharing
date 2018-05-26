package de.hdm.KontaktSharing.server;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.KontaktSharing.server.db.NutzerMapper;
import de.hdm.KontaktSharing.shared.LoginService;
import de.hdm.KontaktSharing.shared.bo.Nutzer;
import de.hdm.KontaktSharing.shared.exception.InvalidLoginException;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
	private NutzerMapper nutzerMapper;
	@Override
	public void init() throws IllegalArgumentException {
		this.nutzerMapper = NutzerMapper.nutzerMapper();
	}

	@Override
	public void logout() throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public Nutzer login(String mail, String password) throws IllegalArgumentException, SQLException, InvalidLoginException {
		Nutzer nutzer = this.nutzerMapper.findByMail(mail);
		if(nutzer == null) {
			throw new InvalidLoginException();
		}
		this.storeUserInSession(nutzer);
		return nutzer;
	}
	
	@Override
	public Nutzer getCurrentUser() {
		Nutzer nutzer = null;
		HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
		HttpSession session = httpServletRequest.getSession();
		Object userObj = session.getAttribute("user");
		if (userObj != null && userObj instanceof Nutzer) {
			nutzer = (Nutzer) userObj;
		}
		return nutzer;
	}	
	
    public void storeUserInSession(Nutzer nutzer)
    {
        HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("nutzer", nutzer);
    }
 
    public void deleteUserFromSession()
    {
        HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute("nutzer");
    }

}
