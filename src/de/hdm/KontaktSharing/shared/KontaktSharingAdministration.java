package de.hdm.KontaktSharing.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.KontaktSharing.shared.bo.Nutzer;

@RemoteServiceRelativePath("overview")
public interface KontaktSharingAdministration extends RemoteService {
	
	public void init() throws IllegalArgumentException;

	public void setNutzer(Nutzer n);


	
}
