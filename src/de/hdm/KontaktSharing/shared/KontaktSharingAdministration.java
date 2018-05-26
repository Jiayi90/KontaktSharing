package de.hdm.KontaktSharing.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.KontaktSharing.shared.bo.Kontakt;
import de.hdm.KontaktSharing.shared.bo.Nutzer;

@RemoteServiceRelativePath("administrationService")
public interface KontaktSharingAdministration extends RemoteService {
	
	public void init() throws IllegalArgumentException;
	
	public Vector<Kontakt> getAllKontaktByLoggedInNutzer() throws IllegalArgumentException, Exception;

	public void setNutzer(Nutzer n);


	
}
