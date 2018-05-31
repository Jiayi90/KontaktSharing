package de.hdm.KontaktSharing.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.KontaktSharing.shared.bo.Nutzer;

public interface LoginServiceAsync {
	void login(String mail, String password, AsyncCallback<Nutzer> callback);
	void logout(AsyncCallback<Void> callback);
	void getCurrentUser(AsyncCallback<Nutzer> callback);
}
