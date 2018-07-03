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
import com.google.gwt.user.client.ui.HTML;
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
	
	private static KontaktSharingAdministrationAsync administration = ClientsideSettings
			.getKontaktSharingAdministration();
	
	private TextBox textBox = new TextBox();
	private Button suchenButton = new Button("Detailsuche");
	private Button logoutButton = new Button("Logout");
	private Button loginButton = new Button("Login");
	


	@Override
	public void onModuleLoad() {
		
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL() + "KontaktSharing.html", new LoginCallback());

	}
	
	private void loadLogin() {
		//CommonPage contentPanel = new EmptyPage("Login");
		
		
		loginPanel.add(new HTML ("<p>Herzlich Willkommen auf der Kontakt-Sharing Plattform!</p> "
		+ "<p>Diese Plattform bietet dir das Anlegen, Bearbeiten, Loeschen und Teilen</p>" +
		"<p>von Kontakten, Kontaktlisten und Eigenschaften an.</p> "));
		loginButton.addClickHandler(new loginButtonClickHandler());
		loginButton.setStylePrimaryName("loginButton");
		loginPanel.setStylePrimaryName("loginPanel");
		loginMessage.setStylePrimaryName("landingPageLoginMessage");
		loginPanel.add(loginMessage);
		loginPanel.add(loginButton);
		//contentPanel.add(loginPanel);
		RootPanel.get("content").add(loginPanel);
		
	//	this.showPage(contentPanel, false);
	}
	
	private void loadKontaktSharing() {
		
		RootPanel.get("content").clear();
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
				administration.getNutzerByMailOrCreate(loginInfo.getEmailAddress(), new FindNutzerCallback());
				
			} else {
				loadLogin();
			}
		}

	}

	class FindNutzerCallback implements AsyncCallback<Nutzer> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert(caught.toString());
			
		}

		@Override
		public void onSuccess(Nutzer nutzer) {
			Cookies.setCookie("email", nutzer.getEmail());
			Cookies.setCookie("id", nutzer.getId()+ "");
			
			showPage(new ListContactsPage(), true);

			

			
		}

	}

	class CreateNutzerDialogBox extends DialogBox {
		private Label frage = new Label(
				"Du bist noch kein Nutzer auf der Kontakt-Sharing Plattform?");
		private Button nein = new Button("Nein");
		private Button ja = new Button("Ja");
		private String googleMail1 = "";
		private VerticalPanel vpanel = new VerticalPanel();
		
		private String googleMail = "";

		public CreateNutzerDialogBox(String mail) {
			googleMail1 = mail;
			ja.addClickHandler(new CreateNutzerClickHandler());
			nein.addClickHandler(new DontCreateNutzerClickHandler());
			vpanel.add(frage);
			vpanel.add(nein);
			vpanel.add(ja);
			this.add(vpanel);

		}

		class CreateNutzerCallback implements AsyncCallback<Nutzer> {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("User konnte nicht angelegt werden" + caught.getMessage());
			}

			@Override
			public void onSuccess(Nutzer result) {
				Window.alert("Nun bist du ein Nutzer auf der Kontakt-Sharing Plattform");
				Cookies.setCookie("signout", loginInfo.getLogoutUrl());
				Cookies.setCookie("email", result.getEmail());
				Cookies.setCookie("id", result.getId() + "");
				loadKontaktSharing();
				hide();
			}

		}

		class CreateNutzerClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				administration.checkNutzer(googleMail1, new CreateNutzerCallback());

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
	private void showPage(CommonPage contentPanel, boolean showMenu) {

		VerticalPanel rootPanel = new VerticalPanel();
		
		HorizontalPanel mainPanel = new HorizontalPanel();
		mainPanel.getElement().setId("main");
		
		
		HeaderWidget headerPanel = new HeaderWidget();
		NavigationWidget navPanel = NavigationWidget.getNavigationWidget(mainPanel, contentPanel, showMenu);
		
		

		rootPanel.add(headerPanel);
		rootPanel.add(mainPanel);
		mainPanel.add(navPanel);
		mainPanel.add(contentPanel);

		RootPanel.get("rootElement").add(rootPanel);
		
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


	