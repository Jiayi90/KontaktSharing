package de.hdm.KontaktSharing.client.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.KontaktSharing.client.widget.NavigationWidget;
import de.hdm.KontaktSharing.client.widget.SmallButton;
import de.hdm.KontaktSharing.shared.bo.Eigenschaft;
import de.hdm.KontaktSharing.shared.bo.Eigenschaftauspraegung;
import de.hdm.KontaktSharing.shared.bo.Kontakt;
import de.hdm.KontaktSharing.shared.bo.Kontaktliste;
import de.hdm.KontaktSharing.shared.bo.TeilhaberschaftKontaktliste;

public class DetailKontaktliste extends CommonPage {

	Kontaktliste liste;

	VerticalPanel viewPanel;

	public DetailKontaktliste(Kontaktliste liste) {
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
		return "Listendetails";
	}

	
	@Override
	protected void run() {
		viewPanel = new VerticalPanel();
		viewPanel.getElement().addClassName("list-view");
		add(viewPanel);
		this.kontaktSharingAdmin.getKontaktlisteWithUserinformation(liste.getId(), new AsyncCallback<Kontaktliste>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Kontaktliste liste) {

				for (Kontakt kontakt : liste.getKontakte()) {
					createKontaktEntry(kontakt);
				}
			}

		});

	}

	/**
	 * 
	 * @param kontakt
	 */
	private void createKontaktEntry(final Kontakt kontakt) {
		FocusPanel fLabel = new FocusPanel();
		fLabel.add(new Label(kontakt.getName()));
		fLabel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				NavigationWidget.navigateTo(new DetailKontakt(kontakt, liste));
			}

		});
		viewPanel.add(fLabel);
	}

}
