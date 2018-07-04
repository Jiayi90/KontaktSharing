package de.hdm.KontaktSharing.client.page;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

import de.hdm.KontaktSharing.client.widget.NavigationWidget;
import de.hdm.KontaktSharing.client.widget.SmallButton;
import de.hdm.KontaktSharing.shared.bo.Kontaktliste;

public class ListContactListPage extends CommonPage {
	FlexTable table;
	@Override
	protected String getHeadlineText() {
		return "Kontaktlisten";
	}

	@Override
	protected void run() {
		PushButton createContactButton = new PushButton(new Image("icons/icons8-plus-48.png"));
		createContactButton.getElement().setId("createContactButton");
		createContactButton.addClickHandler(new CreateContactListButtonClickHandler());
		this.add(createContactButton);
		
		this.kontaktSharingAdmin.getAllKontaktlistenWithUserCountForNutzer(getLoggedInId(), new RenderTable());
	}
	
	class RenderTable implements AsyncCallback<Vector<Kontaktliste>> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Kontaktliste> result) {
			table = new FlexTable();
			table.getElement().setId("kontakt-list");
			table.setText(0, 2, "Name");
			table.setText(0, 3, "Anzahl");
			
			for(final Kontaktliste liste: result) {
				int row = table.getRowCount();
				
				SmallButton deleteButton = new SmallButton("icons/delete.png");
				deleteButton.addClickHandler(new DeleteKontaktliste(liste));
				table.setWidget(row, 1, deleteButton);
				
				SmallButton editButton = new SmallButton("icons/edit.png");
				editButton.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						NavigationWidget.navigateTo(new EditContactList(liste));
					}
					
				});
				table.setWidget(row, 0, editButton);
				
				table.setText(row, 2, liste.getKontaktlistenname());
				table.setText(row, 3, "" + liste.getKontakte().size());
			}

			page.add(table);
		}
		
	}
	
	class CreateContactListButtonClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			NavigationWidget.navigateTo(new CreateContactList());
		}
	}
	
	class DeleteKontaktliste implements ClickHandler {
		Kontaktliste liste;
		public DeleteKontaktliste(Kontaktliste liste) {
			this.liste = liste;
		}

		@Override
		public void onClick(ClickEvent event) {
			boolean delete = Window.confirm("Soll die Kontaktliste " + liste.getKontaktlistenname() + " geloescht werden? Diese Aktion ist nicht rueckgaengig zu machen");
			if(delete) {
				page.kontaktSharingAdmin.delete(liste, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Beim loeschen ist ein Fehler aufgetretten");
					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Liste mit id " + liste.getId() + " wurde geloescht");
						table.removeAllRows();
						page.kontaktSharingAdmin.getAllKontaktlistenWithUserCountForNutzer(getLoggedInId(), new RenderTable());
					}
				});
			}
		}
		
	}

}