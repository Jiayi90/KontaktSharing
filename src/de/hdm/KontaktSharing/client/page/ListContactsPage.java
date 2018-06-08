package de.hdm.KontaktSharing.client.page;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

import de.hdm.KontaktSharing.client.ClientsideSettings;
import de.hdm.KontaktSharing.client.widget.NavigationWidget;
import de.hdm.KontaktSharing.shared.bo.Kontakt;
import de.hdm.KontaktSharing.shared.bo.Nutzer;

public class ListContactsPage extends CommonPage {

	@Override
	protected String getHeadlineText() {
		return "Liste Ihrer Kontakte";
	}

	@Override
	protected void run() {
		PushButton createContactButton = new PushButton(new Image("icons/icons8-plus-48.png"));
		createContactButton.getElement().setId("createContactButton");
		createContactButton.addClickHandler(new CreateContactButtonClickHandler());
		this.add(createContactButton);
		ClientsideSettings.getKontaktSharingAdministration().init(new InitCallback());
		ClientsideSettings.getKontaktSharingAdministration().getAllKontaktByLoggedInNutzer(new GetAllKontaktByNutzerCallback(this));
	}
	
	class CreateContactButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			NavigationWidget.navigateTo(new CreateContact());
		}
		
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
	
	private void createContactRow(Kontakt kontakt, FlexTable table) {
		int row = table.getRowCount();
		table.setText(row, 0, String.valueOf(kontakt.getId()));
	}
	
	private void createContaktTable(Vector<Kontakt> contacts) {
		FlexTable table = new FlexTable();
		table.setText(0, 0, "ID");
		contacts.stream().forEach(kontakt -> createContactRow(kontakt, table));
		this.add(table);
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
		public void onSuccess(Vector<Kontakt> contacts) {
			this.page.createContaktTable(contacts);
		}
		
	}
}
