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
/**
 * Entry-Point-klasse des Projekts <b>KontaktSharing</b>
 * @author jiayi.li
 *
 */

public class KontaktSharing implements EntryPoint {
	/**
	 * Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	 * zusichert, benötige eine Methode
	 * <code>public void onMeduleLoad()</code>. Diese ist das GET-Pendant der 
	 * <code>main()</code>-Methide normaler java-applikation. 
	 */
	@Override
	public void onModuleLoad() {
		
		
	    /*
	     * Der Navigator ist als einspaltige Aneinanderreihung von Buttons
	     * realisiert. Daher bietet sich ein VerticalPanel als Container an.
	     */
		VerticalPanel rootPanel = new VerticalPanel();
	    /*
	     * Das VerticalPanel wird einem DIV-Element namens "Navigator" in der
	     * zugehörigen HTML-Datei zugewiesen und erhält so seinen Darstellungsort.
	     */
		
		HorizontalPanel mainPanel = new HorizontalPanel();
		mainPanel.getElement().setId("main");
		
		CommonPage contentPanel = new DemoPage();
		HeaderWidget headerPanel = new HeaderWidget();
		NavigationWidget navPanel = NavigationWidget.getNavigationWidget(mainPanel, contentPanel);
		
		

		rootPanel.add(headerPanel);
		rootPanel.add(mainPanel);
		mainPanel.add(navPanel);
		mainPanel.add(contentPanel);

		RootPanel.get("rootElement").add(rootPanel);


	}

}
