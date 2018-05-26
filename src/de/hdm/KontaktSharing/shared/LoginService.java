package de.hdm.KontaktSharing.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.KontaktSharing.shared.bo.Nutzer;

@RemoteServiceRelativePath("loginService")
public interface LoginService extends RemoteService {
	public void logout() throws IllegalArgumentException;
	public Nutzer login(String mail, String password) throws IllegalArgumentException, Exception;
	public Nutzer getCurrentUser() throws IllegalArgumentException;
}
