package de.hdm.KontaktSharing.client;

import com.google.gwt.core.client.EntryPoint;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.KontaktSharing.client.page.CommonPage;
import de.hdm.KontaktSharing.client.page.DemoPage;
import de.hdm.KontaktSharing.client.widget.HeaderWidget;
import de.hdm.KontaktSharing.client.widget.NavigationWidget;
import de.hdm.KontaktSharing.shared.LoginServiceAsync;
import de.hdm.KontaktSharing.shared.bo.Nutzer;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;

public class KontaktSharing implements EntryPoint {
	@Override
	public void onModuleLoad() {
		VerticalPanel rootPanel = new VerticalPanel();
		
		HorizontalPanel mainPanel = new HorizontalPanel();
		mainPanel.getElement().setId("main");
		
		CommonPage contentPanel = new DemoPage();
		HeaderWidget headerPanel = new HeaderWidget();
		NavigationWidget navPanel = new NavigationWidget(mainPanel, contentPanel);
		
		

		rootPanel.add(headerPanel);
		rootPanel.add(mainPanel);
		mainPanel.add(navPanel);
		mainPanel.add(contentPanel);

		RootPanel.get("rootElement").add(rootPanel);


	}

}
