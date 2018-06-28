package de.hdm.KontaktSharing.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.KontaktSharing.client.page.*;
import de.hdm.KontaktSharing.client.widget.*;
import de.hdm.KontaktSharing.shared.*;
import de.hdm.KontaktSharing.shared.LoginService;
import de.hdm.KontaktSharing.shared.LoginServiceAsync;
import de.hdm.KontaktSharing.shared.bo.*;
import de.hdm.KontaktSharing.shared.report.AllKontaktReport;




//@author samina

public class KontaktSharing implements EntryPoint {

	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginMessage = new Label("Bitte logge dich mit dem Google Account ein");
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");
	
	private static KontaktSharingAdministrationAsync ksverwaltung = ClientsideSettings
			.getKontaktSharingAdministration();
	
	private TextBox textBox = new TextBox();
	private Button suchenButton = new Button("Detailsuche");
	private Button logoutButton = new Button("Ausloggen");
	private Button loginButton = new Button("LOGIN");

	@Override
	public void onModuleLoad() {
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL() + "KontaktSharing.html", new LoginCallback());

	}
	
	private void loadLogin() {

		loginButton.addClickHandler(new loginButtonClickHandler());
		loginButton.setStylePrimaryName("loginButton");
		Widget welcomeMessage = null;
		welcomeMessage.setStylePrimaryName("landingPageWelcomeMessage");
		loginPanel.setStylePrimaryName("loginPanel");
		loginMessage.setStylePrimaryName("landingPageLoginMessage");
		loginPanel.add(welcomeMessage);
		loginPanel.add(loginMessage);
		loginPanel.add(loginButton);
		RootPanel.get("content").add(loginPanel);
	}
	
	private void loadKontaktSharing() {
		textBox.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
					Widget panelSuche = new Widget();
					RootPanel.get("content").clear();
					RootPanel.get("content").add(panelSuche);
					textBox.setText("");
				}
			}
		});
		
		HorizontalPanel flowpanel = new HorizontalPanel();
		// Cookies.setCookie("logout", loginInfo.getLogoutUrl());
		textBox.setStylePrimaryName("searchTextBox");
		textBox.setMaxLength(100);
		textBox.getElement().setPropertyString("placeholder", " Schnellsuche...");
		flowpanel.add(textBox);
		flowpanel.setStylePrimaryName("logoutBarContainer");
		RootPanel.get("logout").add(flowpanel);// hPanelBar.add(logoutButton);
		RootPanel.get("leftmenutree").clear();
		AllKontaktReport kontaktView = new AllKontaktReport();

		// AUFRUF DES BAUMS
//		CustomTreeModel ctm = new CustomTreeModel();
//		RootPanel.get("leftmenutree").clear();
//		RootPanel.get("leftmenutree").add(ctm);
//
//		signOutLink.setHref(loginInfo.getLogoutUrl());
//
//		Menubar mb = new Menubar();
	}
	class loginButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			signInLink.setHref(loginInfo.getLoginUrl());
			Window.open(signInLink.getHref(), "_self", "");
		}

	}

	class LoginCallback implements AsyncCallback<LoginInfo> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Login: " + caught.getMessage());
		}

		@Override
		public void onSuccess(LoginInfo result) {
			loginInfo = result;
			if (loginInfo.isLoggedIn()) {
				ksverwaltung.checkNutzer(loginInfo.getEmailAddress(), new FindNutzerCallback());

			} else {
				loadLogin();
			}
		}

	}

	class FindNutzerCallback implements AsyncCallback<Nutzer> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Login: " + caught.getMessage());
		}

		@Override
		public void onSuccess(Nutzer result) {
			if (result != null) {
				Cookies.setCookie("email", result.getEmail());
				Cookies.setCookie("id", result.getId() + "");
				loadKontaktSharing();
			} else {
				CreateNutzerDialogBox dialogbox = new CreateNutzerDialogBox(loginInfo.getEmailAddress());
				dialogbox.center();

			}
		}

	}

	class CreateNutzerDialogBox extends DialogBox {
		private Label frage = new Label(
				"Sie haben noch keinen Nutzer auf diesem Kontaktmanager. MÃ¶chten Sie einen neuen Nutzer anlegen?");
		private Button ja = new Button("Ja");
		private Button nein = new Button("Nein");
		private String googleMail = "";
		private VerticalPanel vpanel = new VerticalPanel();

		public CreateNutzerDialogBox(String mail) {
			googleMail = mail;
			ja.addClickHandler(new CreateNutzerClickHandler());
			nein.addClickHandler(new DontCreateNutzerClickHandler());
			vpanel.add(frage);
			vpanel.add(ja);
			vpanel.add(nein);
			this.add(vpanel);

		}

		class CreateNutzerCallback implements AsyncCallback<Nutzer> {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Ihr User konnte nicht erstellt werden" + caught.getMessage());
			}

			@Override
			public void onSuccess(Nutzer result) {
				Window.alert("Ihr Nutzer wurde erfolgreich angelegt");
				Cookies.setCookie("email", result.getEmail());
				Cookies.setCookie("id", result.getId() + "");
				loadKontaktSharing();
				hide();
			}

		}

		class CreateNutzerClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				ksverwaltung.checkNutzer(googleMail, new CreateNutzerCallback());

			}

		}

		class DontCreateNutzerClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				hide();
				signOutLink.setHref(loginInfo.getLogoutUrl());
				Window.open(signOutLink.getHref(), "_self", "");

			}

		}
	}

	public static class SuchenCommand implements Command {

		@Override
		public void execute() {
			Widget panelSuche = new Widget();
			RootPanel.get("content").clear();
			RootPanel.get("content").add(panelSuche);
		}
	}

}


	