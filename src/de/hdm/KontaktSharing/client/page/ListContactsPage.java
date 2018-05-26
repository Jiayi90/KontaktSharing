package de.hdm.KontaktSharing.client.page;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;

import de.hdm.KontaktSharing.client.ClientsideSettings;
import de.hdm.KontaktSharing.client.page.DemoPage.LoginCallback;
import de.hdm.KontaktSharing.shared.bo.Kontakt;
import de.hdm.KontaktSharing.shared.bo.Nutzer;

public class ListContactsPage extends CommonPage {

	@Override
	protected String getHeadlineText() {
		return "Liste Ihrer Kontakte";
	}

	@Override
	protected void run() {
		ClientsideSettings.getKontaktSharingAdministration().init(new InitCallback());
		ClientsideSettings.getKontaktSharingAdministration().getAllKontaktByLoggedInNutzer(new GetAllKontaktByNutzerCallback(this));
	}
	
	class InitCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Void result) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class LoginCallback implements AsyncCallback<Nutzer> {
		@Override
		public void onFailure(Throwable caught) {
			
		}

		@Override
		public void onSuccess(Nutzer result) {
			
		}
	}
	
	public void createContactRow(Kontakt kontakt) {
		this.add(new HTML(String.valueOf(kontakt.getId())));
	}
	
	class GetAllKontaktByNutzerCallback implements AsyncCallback<Vector<Kontakt>> {
		ListContactsPage page;
		public GetAllKontaktByNutzerCallback(ListContactsPage listContactsPage) {
			this.page = listContactsPage;
		}

		@Override
		public void onFailure(Throwable caught) {
			this.page.add(new HTML("Fehler beim Laden der Kontakte"));
		}

		@Override
		public void onSuccess(Vector<Kontakt> result) {
			result.stream().forEach(kontakt -> this.page.createContactRow(kontakt));
		}
		
	}
}
