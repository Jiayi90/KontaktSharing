package de.hdm.KontaktSharing.client.page;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.KontaktSharing.client.widget.ChooseUserWidget;
import de.hdm.KontaktSharing.client.widget.NavigationWidget;
import de.hdm.KontaktSharing.shared.bo.Kontaktliste;
import de.hdm.KontaktSharing.shared.bo.Nutzer;

public class ShareContactList extends CommonPage implements ChooseUserPage{

	Kontaktliste liste;
	
	ShareContactList(Kontaktliste liste) {
		this.liste = liste;		
	}
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Kontaktliste teilen";
	}

	@Override
	protected void run() {
		final ShareContactList page = this;
		this.kontaktSharingAdmin.getAllNutzerWithoutCurrent(this.getLoggedInId(), new AsyncCallback<Vector<Nutzer>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Laden schlug fehl");
			}

			@Override
			public void onSuccess(Vector<Nutzer> result) {
				add(new ChooseUserWidget(page, result));
			}
			
		});
		
	}
	@Override
	public void confirmMailEvent(List<String> mails) {
		
		this.kontaktSharingAdmin.shareListe(getLoggedInId(), liste.getId(), mails, new AsyncCallback<Void>() {

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
