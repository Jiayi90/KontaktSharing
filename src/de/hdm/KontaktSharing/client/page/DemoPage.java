package de.hdm.KontaktSharing.client.page;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

import de.hdm.KontaktSharing.client.ClientsideSettings;
import de.hdm.KontaktSharing.shared.LoginServiceAsync;
import de.hdm.KontaktSharing.shared.bo.Nutzer;


public class DemoPage extends CommonPage {
	Label text;
	
	class LogoutCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			text.setText("Error loggin out");
		}

		@Override
		public void onSuccess(Void result) {
			text.setText("Logged out");
		}
	}

	class LoginCallback implements AsyncCallback<Nutzer> {
		@Override
		public void onFailure(Throwable caught) {
			text.setText("Error loggin in");
		}

		@Override
		public void onSuccess(Nutzer result) {
			text.setText("Logged in");
		}
	}

	@Override
	protected String getHeadlineText() {
		return "Demopage";
	}

	@Override
	protected void run() {
		final LoginServiceAsync loginService = ClientsideSettings.getLoginService();
		Button login = new Button("Loginn");
		Button logout = new Button("Logout");

		login.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loginService.login("max.muster@example.com", "", new LoginCallback());
			}
		});
		logout.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loginService.logout(new LogoutCallback());
			}
		});
		text = new Label("unknown");
		this.add(text);
		this.add(login);
		this.add(logout);
	}
}
