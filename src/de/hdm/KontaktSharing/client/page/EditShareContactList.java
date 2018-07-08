package de.hdm.KontaktSharing.client.page;

import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import de.hdm.KontaktSharing.client.widget.ChooseUserWidget;
import de.hdm.KontaktSharing.client.widget.NavigationWidget;
import de.hdm.KontaktSharing.client.widget.SmallButton;
import de.hdm.KontaktSharing.shared.bo.Kontaktliste;
import de.hdm.KontaktSharing.shared.bo.Nutzer;

public class EditShareContactList extends CommonPage {
	
	int idTh;
	ChooseUserWidget userWidget;
	Kontaktliste liste;

	public EditShareContactList(int idTh, Kontaktliste liste) {
		this.idTh = idTh;
		this.liste = liste;
		FocusPanel wrapper = new FocusPanel();

		HorizontalPanel panel = new HorizontalPanel();

		panel.getElement().setClassName("navibutton");
		SmallButton backButton = new SmallButton("icons/back.png");
		panel.add(backButton);
		panel.add(new Label("Zurueck"));
		wrapper.add(panel);

		wrapper.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				NavigationWidget.navigateTo(new ListContactListPage());
			}

		});

		this.add(wrapper);
	}

	@Override
	protected String getHeadlineText() {
		return "Teilhaberschaft '"+liste.getKontaktlistenname()+"' bearbeiten";
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
						HorizontalPanel hPanel = new HorizontalPanel();
						hPanel.getElement().setClassName("navibutton");
						SmallButton share = new SmallButton("icons/share.png");
						share.addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								update();
							}
							
						});
						hPanel.add(share);
						hPanel.add(new Label("Teilen"));
						add(hPanel);
					
						
						userWidget = new ChooseUserWidget(allNutzer, sharedNutzer); 
						add(userWidget);
					}
					
				});
				
			}
			
		});
	}

	public void update() {
		this.kontaktSharingAdmin.updateTeilhaberschaftListe(idTh, userWidget.getMails(), new AsyncCallback<Void>() {

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
