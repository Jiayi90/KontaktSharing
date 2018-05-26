package de.hdm.KontaktSharing.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.KontaktSharing.shared.bo.Kontakt;
import de.hdm.KontaktSharing.shared.bo.Nutzer;

public interface KontaktSharingAdministrationAsync {

	
	void setNutzer (Nutzer nutzer, AsyncCallback<Void> callback);
		
	void init(AsyncCallback<Void> callback);
	
	void getAllKontaktByLoggedInNutzer(AsyncCallback<Vector<Kontakt>> callback);

}
