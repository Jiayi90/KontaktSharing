package de.hdm.KontaktSharing.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.KontaktSharing.client.page.CommonPage;
import de.hdm.KontaktSharing.client.page.DemoPage;
import de.hdm.KontaktSharing.client.widget.ContentWidget;
import de.hdm.KontaktSharing.client.widget.HeaderWidget;
import de.hdm.KontaktSharing.client.widget.NavigationWidget;
import de.hdm.KontaktSharing.shared.KontaktSharingAdministration;
import de.hdm.KontaktSharing.shared.KontaktSharingAdministrationAsync;
import de.hdm.KontaktSharing.shared.LoginService;
import de.hdm.KontaktSharing.shared.LoginServiceAsync;
import de.hdm.KontaktSharing.shared.bo.LoginInfo;
import de.hdm.KontaktSharing.shared.bo.Nutzer;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;

//@author samina

public class KontaktSharing implements EntryPoint {

	// /**
	// * The message displayed to the user when the server cannot be reached or
	// * returns an error.
	// */
	//
	// private static final String SERVER_ERROR = "An error occurred while"
	// + "attempting to contact the server. Please check your network" + "connection
	// and try again.";
	//
	// /**
	// * Create a remote service proxy to talk to the server-side Greeting service.
	// *
	// */
	// private final KontaktSharingAdministrationAsync projektService =
	// GWT.create(KontaktSharingAdministration.class);
	//

	/**
	 * Panel, Label und Anchor für die Anmeldung
	 */
	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label(
			"Please sign in to your Google Account to access the StockWatcher application.");
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");

	private static KontaktSharingAdministrationAsync ksverwaltung = ClientsideSettings
			.getKontaktSharingAdministration();
	/**
	 * Anlegen vom report und anchor
	 */
	private Button zumreportgenerator = new Button("Zum Report Generator");
	private Anchor reportLink = new Anchor();
	private Button zuKontaktsharing = new Button("Zu Kontaktsharing");

	/**
	 * Hier steigt das Programm beim Start ein
	 */
	public void onModuleLoad() {

		
		//loadPanel();
		// Check login status using login service.
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		// Start-URL der Anwendung GWT.getHostPageBaseURL() !!!
		loginService.login(GWT.getHostPageBaseURL() + "KontaktSharing.html", new AsyncCallback<LoginInfo>() {

			/**
			 * siehe client google report
			 */
			public void onFailure(Throwable caught) {
				Window.alert("Fehler Login: " + caught.toString());
			}

			/**
			 * siehe client google report
			 */
			public void onSuccess(LoginInfo result) {
				loginInfo = result;
				if (loginInfo.isLoggedIn()) {

					ksverwaltung.checkNutzer(loginInfo.getEmailAddress(), new AsyncCallback<Nutzer>() {

						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							Window.alert("Fehler beim Laden: " + caught.getLocalizedMessage());
						}

						public void onSuccess(Nutzer result) {
							// Ueberpruefung, ob Nutzer bereits existiert.
							if (result != null) {
								RootPanel.get("Details").clear();
								loadKontaktSharing(result);
							} else {
								RootPanel.get("Details").clear();

							}

						}

					});

				} else {
					loadLogin();
				}

			}

		});
	}

	/**
	 * Zusammenstellung des Anmeldeabschnitts
	 */
	private void loadLogin() {
		zuKontaktsharing.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				signInLink.setHref(loginInfo.getLoginUrl());
				Window.open(signInLink.getHref(), "_self", "");

			}
		});
		zumreportgenerator.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				reportLink.setHref(GWT.getHostPageBaseURL() + "KontaktSharingReport.html");
				Window.open(reportLink.getHref(), "_self", "");
			}
		});
		loginPanel.add(loginLabel);
		loginPanel.add(zuKontaktsharing);
		loginPanel.add(zumreportgenerator);
		RootPanel.get("Details").add(loginPanel);

	}

	/**
	 * laden des KontaktSharing. (Startseite Menübar)
	 * 
	 * @param profil
	 */
	private void loadKontaktSharing(final Nutzer nutzer) {
		signOutLink.setHref(loginInfo.getLogoutUrl());
		RootPanel.get("Topbar").add(signOutLink);
		RootPanel.get("Details").add(new ContentWidget(nutzer));
		RootPanel.get("Navigator").add(new NavigationWidget(loginPanel, null));

	}

}

// VerticalPanel vpNavigation = new VerticalPanel();
// Button btStartseite = new Button("Startseite");
// Button btPost = new Button("Post");
// Button btImpressum = new Button("Impressum");
// Button btAusloggen = new Button("Ausloggen");
//
// public void onModuleLoad() {
//
// // Check login status using login service.
// LoginServiceAsync loginService = GWT.create(LoginService.class);
//// loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>()
// {
//// public void onFailure(Throwable error) {
//// }
////
//// public void onSuccess(LoginInfo result) {
//// loginInfo = result;
//// if (loginInfo.isLoggedIn()) {
//// loadPanel();
//// } else {
//// loadLogin();
//// }
////
//// }
//// });
//
// Cookies.setCookie("email", "hdm@hdm4.de");
//
// loadGUI();
//
//
// }
//
// private void loadGUI() {
//
// }
//
// private void loadLogin() {
// // Assemble login panel.
// signInLink.setHref(loginInfo.getLoginUrl());
// loginPanel.add(loginLabel);
// loginPanel.add(signInLink);
// RootPanel.get("rootElement").add(loginPanel);
// }
//
// private void loadPanel() {
// VerticalPanel rootPanel = new VerticalPanel();
//
// HorizontalPanel mainPanel = new HorizontalPanel();
// mainPanel.getElement().setId("main");
//
// CommonPage contentPanel = new DemoPage();
// HeaderWidget headerPanel = new HeaderWidget();
// NavigationWidget navPanel = new NavigationWidget(mainPanel, contentPanel);
//
// rootPanel.add(headerPanel);
// rootPanel.add(mainPanel);
// mainPanel.add(navPanel);
// mainPanel.add(contentPanel);
//
// RootPanel.get("rootElement").add(rootPanel);
//
// }
//
// }
