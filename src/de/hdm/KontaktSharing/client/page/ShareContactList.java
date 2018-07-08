package de.hdm.KontaktSharing.client.page;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import de.hdm.KontaktSharing.client.widget.ChooseEigenschaftsauspraegungenWidget;
import de.hdm.KontaktSharing.client.widget.ChooseUserWidget;
import de.hdm.KontaktSharing.client.widget.NavigationWidget;
import de.hdm.KontaktSharing.client.widget.SmallButton;
import de.hdm.KontaktSharing.shared.bo.Kontaktliste;
import de.hdm.KontaktSharing.shared.bo.Nutzer;

public class ShareContactList extends CommonPage{

	Kontaktliste liste;
	ChooseUserWidget userWidget;
	
	ShareContactList(Kontaktliste liste) {
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
		// TODO Auto-generated method stub
		return "Kontaktliste teilen";
	}
	
	@Override
	protected void run() {
		
		this.kontaktSharingAdmin.getAllNutzerWithoutCurrent(this.getLoggedInId(), new AsyncCallback<Vector<Nutzer>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Laden schlug fehl");
			}

			@Override
			public void onSuccess(Vector<Nutzer> result) {
				HorizontalPanel hPanel = new HorizontalPanel();
				hPanel.getElement().setClassName("navibutton");
				SmallButton share = new SmallButton("icons/share.png");
				share.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						create();
					}
					
				});
				hPanel.add(share);
				hPanel.add(new Label("Teilen"));
				add(hPanel);
				
				userWidget =new ChooseUserWidget(result);
				add(userWidget);
				
				
			}
			
		});
		
	}
	
	public void create() {
		this.kontaktSharingAdmin.shareListe(getLoggedInId(), liste.getId(), userWidget.getMails(), new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Not Workted");
			}

			@Override
			public void onSuccess(Void result) {
				NavigationWidget.navigateTo(new ListContactListPage());
			}
			
		});
	}

}
