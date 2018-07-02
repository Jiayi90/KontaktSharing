package de.hdm.KontaktSharing.client.widget;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;

import de.hdm.KontaktSharing.client.ClientsideSettings;
import de.hdm.KontaktSharing.client.page.ListContactsPage;

public class DebugWidget extends ListBox {
	public DebugWidget() {
		this.addItem("1", "1");
		this.addItem("2", "2");
		this.addItem("3", "3");
		final DebugWidget box = this;
		
		this.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				int index = Integer.parseInt(box.getSelectedValue());
				ClientsideSettings.getKontaktSharingAdministration().setCurrentNutzerId(index, new AsyncCallback<Void>() {

		 			@Override
		 			public void onFailure(Throwable caught) {
		 				// TODO Auto-generated method stub
		 				
		 			}

		 			@Override
		 			public void onSuccess(Void result) {
		 				NavigationWidget.navigateTo(new ListContactsPage());
		 			}
		         	 
		          });
			}
		});
	}

}