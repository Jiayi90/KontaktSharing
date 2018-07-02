package de.hdm.KontaktSharing.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.KontaktSharing.client.LoginInfo;
import com.google.gwt.user.client.Cookies;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.KontaktSharing.shared.*;
import de.hdm.KontaktSharing.shared.bo.*;

/*
 * Entrypoint der Klasse KontaktSharingReport
 * @author Vi Quan
 *
 */

public class KontaktSharingReport implements EntryPoint {

	private HorizontalPanel hpHeader = new HorizontalPanel();
	private HorizontalPanel hpInfo = new HorizontalPanel();
	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginMessage = new Label("Bitte logge dich mit dem Google Account ein");
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");
	private static ReportGeneratorAsync administration = ClientsideSettings
			.getReportGenerator();
	private TextBox textBox = new TextBox();

	private Button btLogin = new Button("Login");
	private Button btLogout = new Button("Logout");
	private Button btImpressum = new Button("Impressum");
	
	@Override
	public void onModuleLoad() {
		
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL() + "KontaktSharingReport.html", new LoginCallback());


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
				administration.getNutzerByMail(loginInfo.getEmailAddress(), new FindNutzerCallback());

			} else {
				loadLogin();
			}
		}

	}
	class loginButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			signInLink.setHref(loginInfo.getLoginUrl());
			Window.open(signInLink.getHref(), "_self", "");
		}

	}
	private void loadLogin() {

		btLogin.addClickHandler(new loginButtonClickHandler());

		loginPanel.add(loginMessage);
		loginPanel.add(btLogin);
		RootPanel.get("content").add(loginPanel);

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
				loadReportgenerator();
			} else {
				Window.alert("Bitte registrieren Sie sich zu erst ueber die Kontakt-Sharing Plattform.");
				signOutLink.setHref(loginInfo.getLogoutUrl());
				Window.open(signOutLink.getHref(), "_self", "");
			}
		}

	}
	public class logoutClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			signOutLink.setHref(loginInfo.getLogoutUrl());
			Window.open(signOutLink.getHref(), "_self", "");

		}
	}
	private void loadReportgenerator() {
//		vPanelBar.add(logoutButton);
//		Cookies.setCookie("logout", loginInfo.getLogoutUrl());
//		RootPanel.get("logout").add(logoutButton);
		btLogout.addClickHandler(new logoutClickHandler());
		btLogout.setStylePrimaryName("logoutButton");
		hpHeader.add(btLogout);
		RootPanel.get("logout").add(hpHeader);
		RootPanel.get("leftmenutree").clear();
		RootPanel.get("menubar").clear();

		
	
		signOutLink.setHref(loginInfo.getLogoutUrl());
	}
	

//	public void onModuleLoad() {
//		LoginServiceAsync loginService = GWT.create(LoginService.class);
//		loginService.login(GWT.getHostPageBaseURL() + "KontaktSharingReport.html", new AsyncCallback<LoginInfo>() {
//			public void onFailure(Throwable error) {
//			}
//
//			@Override
//			public void onSuccess(LoginInfo result) {
//				loginInfo = result;
//				if (loginInfo.isLoggedIn()) {
//					administration.(loginInfo.getEmailAddress(), new FindNutzerCallback());
//				} else {
//					loadLogin();
//				}
//			}
//		});
//	}
//
//	private void loadLogin() {
//
//		btLogin.addClickHandler(new loginButtonClickHandler());
//		btLogin.setStylePrimaryName("loginButton");
//		loginPanel.setStylePrimaryName("loginPanel");
//		loginMessage.setStylePrimaryName("landingPageLoginMessage");
//		loginPanel.add(loginMessage);
//		loginPanel.add(btLogin);
//		RootPanel.get("content").add(loginPanel);
//	}
//
//	private void loadKontaktSharingReport() {
//		textBox.addKeyPressHandler(new KeyPressHandler() {
//
//			@Override
//			public void onKeyPress(KeyPressEvent event) {
//				if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
//					Widget panelSuche = new Widget();
//					RootPanel.get("content").clear();
//					RootPanel.get("content").add(panelSuche);
//					textBox.setText("");
//				}
//			}
//		});
//	}
//
//	class loginButtonClickHandler implements ClickHandler {
//
//		@Override
//		public void onClick(ClickEvent event) {
//			signInLink.setHref(loginInfo.getLoginUrl());
//			Window.open(signInLink.getHref(), "_self", "");
//		}
//
//	}
//
//	class LoginCallback implements AsyncCallback<LoginInfo> {
//
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert("Fehler beim Login: " + caught.getMessage());
//		}
//
//		@Override
//		public void onSuccess(LoginInfo result) {
//			loginInfo = result;
//			if (loginInfo.isLoggedIn()) {
//				administration.checkNutzer(loginInfo.getEmailAddress(), new FindNutzerCallback());
//
//			} else {
//				loadLogin();
//			}
//		}
//
//	}
//
//	class FindNutzerCallback implements AsyncCallback<Nutzer> {
//
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert("Login-Fehler!!: " + caught.getMessage());
//		}
//
//		@Override
//		public void onSuccess(Nutzer result) {
//			if (result != null) {
//				Cookies.setCookie("email", result.getEmail());
//				Cookies.setCookie("id", result.getId() + "");
//				loadKontaktSharingReport();
//			} else {
//				Window.alert("Nutzer ist nicht vorhanden");
//
//			}
//		}
//
//	}

}
