package de.hdm.KontaktSharing.client.page;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

import de.hdm.KontaktSharing.client.ClientsideSettings;
import de.hdm.KontaktSharing.client.widget.NavigationWidget;
import de.hdm.KontaktSharing.client.widget.SmallButton;
import de.hdm.KontaktSharing.shared.bo.Eigenschaftauspraegung;
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
		ClientsideSettings.getKontaktSharingAdministration().getAllKontaktByNutzer(getLoggedInId(), new GetAllKontaktByNutzerCallback(this));
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
	
	private void createContactRow(final Kontakt kontakt, final FlexTable table) {
		final ListContactsPage page = this;
		final int row = table.getRowCount();
		
		this.kontaktSharingAdmin.getAllEigenschaftauspraegungByKontakt(kontakt, new AsyncCallback<Vector<Eigenschaftauspraegung>>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vector<Eigenschaftauspraegung> result) {
				final String name = findEigenschaftauspraegungById(result, 1).getText();
				SmallButton editButton = new SmallButton("icons/edit.png");
				editButton.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						NavigationWidget.navigateTo(new EditContact(kontakt));
					}
					
				});
				
				SmallButton deleteButton = new SmallButton("icons/delete.png");
				deleteButton.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						boolean delete = Window.confirm("Soll der Kontakt " + name + " geloescht werden? Diese Aktion ist nicht rueckgaengig zu machen");
						if(delete) {
							ClientsideSettings.getKontaktSharingAdministration().delete(kontakt, new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Beim loeschen ist ein Fehler aufgetretten");
								}

								@Override
								public void onSuccess(Void result) {
									Window.alert("User mit id " + kontakt.getId() + " und seine Eigenschaftsauspraegungen wurde geloescht");
									table.removeAllRows();
									ClientsideSettings.getKontaktSharingAdministration().getAllKontaktByNutzer(getLoggedInId(), new GetAllKontaktByNutzerCallback(page));
								}
							});
						}
					}
					
				});
				table.insertRow(row);
				table.setWidget(row, 0, editButton);
				table.setWidget(row, 1, deleteButton);
				table.setText(row, 2, name);
				table.setText(row, 3, kontakt.getErzeugungsdatum().toString());
				table.setText(row, 4, kontakt.getModifikationsdatum().toString());
			}
			
		});
	}
	
	private Eigenschaftauspraegung findEigenschaftauspraegungById(Vector<Eigenschaftauspraegung> auspraegungen, int id) {
		for(Eigenschaftauspraegung auspraegung: auspraegungen) {
			if (auspraegung.getIdEigenschaft() == id) {
				return auspraegung;
			}
		}
		return null;
	}
	
	private void createContaktTable(Vector<Kontakt> contacts) {
		FlexTable table = new FlexTable();
		table.getElement().setId("kontakt-list");
		table.setText(0, 2, "Name");
		table.setText(0, 3, "Erzeugt am");
		table.setText(0, 4, "Modifiziert am");
		for(Kontakt kontakt: contacts) {
			createContactRow(kontakt, table);
		}
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