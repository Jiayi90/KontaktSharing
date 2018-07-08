package de.hdm.KontaktSharing.client.page;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import de.hdm.KontaktSharing.client.widget.NavigationWidget;
import de.hdm.KontaktSharing.client.widget.SmallButton;

public class EditShareContakt extends CommonPage {
	
	int idTh;

	public EditShareContakt(Integer teilhaberschaftId) {
		idTh = teilhaberschaftId; 
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
		return "Teilhaberschaft bearbeiten";
	}

	@Override
	protected void run() {
		
		FocusPanel wrapper = new FocusPanel();

		HorizontalPanel panel = new HorizontalPanel();

		panel.getElement().setClassName("navibutton");
		SmallButton backButton = new SmallButton("icons/delete.png");
		panel.add(backButton);
		panel.add(new Label("Teilhaberschaft loeschen"));
		wrapper.add(panel);

		wrapper.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if(Window.confirm("Teilhaberschaft wirklich loeschen?")) {
					kontaktSharingAdmin.deleteTeilhaberschaft(idTh, new AsyncCallback<Void>(){
	
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler beim löschen aufgetretten");
						}
	
						@Override
						public void onSuccess(Void result) {
							NavigationWidget.navigateTo(new ListContactsPage());
						}
						
					});
				}
			}

		});

		this.add(wrapper);
		
		this.add(new Label("Everything else not implemented yet"));
	}

}
