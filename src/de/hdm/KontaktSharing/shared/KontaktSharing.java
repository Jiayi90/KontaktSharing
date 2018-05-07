package de.hdm.KontaktSharing.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("overview")
public interface KontaktSharing extends RemoteService {
	
	public void init() throws IllegalArgumentException;

}
