package de.hdm.KontaktSharing.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.KontaktSharing.client.LoginInfo;
import de.hdm.KontaktSharing.client.gui.report.NaviReport;
import de.hdm.KontaktSharing.client.KontaktSharing.CreateNutzerDialogBox.CreateNutzerCallback;
import de.hdm.KontaktSharing.client.page.CommonPage;
import de.hdm.KontaktSharing.client.page.ListContactsPage;
import de.hdm.KontaktSharing.client.widget.HeaderWidget;
import de.hdm.KontaktSharing.client.widget.NavigationWidget;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Cookies;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.KontaktSharing.shared.*;
import de.hdm.KontaktSharing.shared.bo.*;


/*
 * Entrypoint der Klasse KontaktSharingReport
 * @author Vi Quan
 *
 */

public class KontaktSharingReport implements EntryPoint {

	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginMessage = new Label("Bitte logge dich mit dem Google Account ein");
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");

	private static ReportGeneratorAsync reportadministration = ClientsideSettings.getReportGenerator();
	private static KontaktSharingAdministrationAsync administration = ClientsideSettings
			.getKontaktSharingAdministration();
	private TextBox textBox = new TextBox();
	private Button logoutButton = new Button("Logout");
	private Button loginButton = new Button("Login");

	@Override
	public void onModuleLoad() {

		
		RootPanel.get("content").add((IsWidget) new NaviReport());
		
		
	}

}
