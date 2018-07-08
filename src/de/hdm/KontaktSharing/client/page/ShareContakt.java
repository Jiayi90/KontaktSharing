package de.hdm.KontaktSharing.client.page;

import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.KontaktSharing.client.widget.ChooseEigenschaftsauspraegungenWidget;
import de.hdm.KontaktSharing.client.widget.ChooseUserWidget;
import de.hdm.KontaktSharing.client.widget.NavigationWidget;
import de.hdm.KontaktSharing.client.widget.SmallButton;
import de.hdm.KontaktSharing.shared.bo.Eigenschaft;
import de.hdm.KontaktSharing.shared.bo.Eigenschaftauspraegung;
import de.hdm.KontaktSharing.shared.bo.Kontakt;
import de.hdm.KontaktSharing.shared.bo.Nutzer;

public class ShareContakt extends CommonPage {
	ChooseEigenschaftsauspraegungenWidget ce;
	ChooseUserWidget cu;
	
	Kontakt kontakt;
	HorizontalPanel panel = new HorizontalPanel();
	
	public ShareContakt(Kontakt kontakt) {
		this.kontakt = kontakt;
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
				NavigationWidget.navigateTo(new ListContactsPage());
			}

		});

		this.add(wrapper);
	}

	@Override
	protected String getHeadlineText() {
		return "Kontakt '"+ kontakt.getName() +"' teilen";
	}

	@Override
	protected void run() {
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
		
		
		panel.getElement().setClassName("full-width");
		this.add(panel);
		ce = new ChooseEigenschaftsauspraegungenWidget();
		panel.add(ce);
		this.kontaktSharingAdmin.getAllEigenschaftauspraegungByKontakt(kontakt, new AsyncCallback<Vector<Eigenschaftauspraegung>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Laden schlug fehl");
			}

			@Override
			public void onSuccess(final Vector<Eigenschaftauspraegung> eps) {
				kontaktSharingAdmin.getAllEigenschaft(new AsyncCallback<Vector<Eigenschaft>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Laden schlug fehl");
					}

					@Override
					public void onSuccess(final Vector<Eigenschaft> es) {
						ce.run(eps, es);
					}
					
				});
				
			}
			
		});
		
		this.kontaktSharingAdmin.getAllNutzerWithoutCurrent(this.getLoggedInId(), new AsyncCallback<Vector<Nutzer>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Laden schlug fehl");
			}

			@Override
			public void onSuccess(Vector<Nutzer> result) {
				cu = new ChooseUserWidget(result);
				panel.add(cu);
			}
			
		});
	}

	public void create() {

		this.kontaktSharingAdmin.shareKontakt(this.getLoggedInId(), ce.getSelected(), cu.getMails(),  new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Beim Teilen ist ein Fehler aufgetretten");
			}

			@Override
			public void onSuccess(Void result) {
				NavigationWidget.navigateTo(new ListContactsPage());
			}
			
		});
	}

}
