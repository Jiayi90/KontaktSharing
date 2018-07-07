package de.hdm.KontaktSharing.client.page;

import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;

import de.hdm.KontaktSharing.client.widget.ChooseUserWidget;
import de.hdm.KontaktSharing.client.widget.NavigationWidget;
import de.hdm.KontaktSharing.shared.bo.Nutzer;

public class EditShareContactList extends CommonPage implements ChooseUserPage {
	
	int idTh;

	public EditShareContactList(int idTh) {
		this.idTh = idTh;
	}

	@Override
	protected String getHeadlineText() {
		return "Teilhaberschaft bearbeiten";
	}

	@Override
	protected void run() {
		Button button = new Button("Teilhaberschaft loeschen");
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(Window.confirm("Teilhaberschaft wirklich loeschen?")) {
					kontaktSharingAdmin.deleteTeilhaberschaft(idTh, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Beim löschen ist etwas schief gelaufen");
						}

						@Override
						public void onSuccess(Void result) {
							NavigationWidget.navigateTo(new ListContactListPage());
						}
						
					});
				}
			}
		});
		add(button);
		final EditShareContactList page = this;
		this.kontaktSharingAdmin.getAllNutzerWithoutCurrent(this.getLoggedInId(), new AsyncCallback<Vector<Nutzer>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Laden schlug fehl");
			}

			@Override
			public void onSuccess(final Vector<Nutzer> allNutzer) {
				kontaktSharingAdmin.getAllNutzerInTeilhaberschaft(idTh, new AsyncCallback<Vector<Nutzer>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Laden schlug fehl");
					}

					@Override
					public void onSuccess(Vector<Nutzer> sharedNutzer) {
						add(new ChooseUserWidget(page, allNutzer, sharedNutzer));
					}
					
				});
				
			}
			
		});
	}

	@Override
	public void confirmMailEvent(List<String> mail) {
		this.kontaktSharingAdmin.updateTeilhaberschaftListe(idTh, mail, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Update fehlgeschlagen");
			}

			@Override
			public void onSuccess(Void result) {
				NavigationWidget.navigateTo(new ListContactListPage());
			}
			
		});
	}

}
