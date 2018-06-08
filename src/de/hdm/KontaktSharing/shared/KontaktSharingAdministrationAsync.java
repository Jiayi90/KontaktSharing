package de.hdm.KontaktSharing.shared;

import java.util.ArrayList;
import java.util.Vector;
import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hdm.KontaktSharing.shared.bo.*;

public interface KontaktSharingAdministrationAsync {

	void setNutzer(Nutzer nutzer, AsyncCallback<Void> callback);

	void init(AsyncCallback<Void> callback);
	
	void getAllKontaktByLoggedInNutzer(AsyncCallback<Vector<Kontakt>> callback);

	void createKontakt(Kontakt k, AsyncCallback<Kontakt> callback);

	void createEigenschaft(Kontakt k, AsyncCallback<Eigenschaft> callback);

	void createEigenschaftauspraegung(Eigenschaftauspraegung e, AsyncCallback<Eigenschaftauspraegung> callback);

	void createKontaktliste(Nutzer n, AsyncCallback<Kontaktliste> callback);

	void delete(Kontakt k, AsyncCallback<Void> callback);

	void delete(Eigenschaft e, AsyncCallback<Void> callback);

	void delete(Kontaktliste kl, AsyncCallback<Void> callback);

	void delete(Eigenschaftauspraegung ea, AsyncCallback<Void> callback);

	void update(Eigenschaft e, AsyncCallback<Void> callback);

	void update(Eigenschaftauspraegung ea, AsyncCallback<Void> callback);

	void update(Kontaktliste kl, AsyncCallback<Void> callback);

	void update(Kontakt k, AsyncCallback<Void> callback);

	void getEigenschaftauspraegungOf(Eigenschaft e, AsyncCallback<ArrayList<Eigenschaftauspraegung>> callback);

	void getEigenschaftOf(Kontakt k, AsyncCallback<ArrayList<Eigenschaft>> callback);

	void getKontaktOf(Kontaktliste kl, AsyncCallback<ArrayList<Kontakt>> callback);

	void getNutzerByEmail(String email, AsyncCallback<Nutzer> callback);

	void getNutzer(AsyncCallback<Nutzer> callback);
	
	void getAllEigenschaft(AsyncCallback<Vector<Eigenschaft>> callback);

}
