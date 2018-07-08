 package de.hdm.KontaktSharing.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.KontaktSharing.client.ClientsideSettings;
import de.hdm.KontaktSharing.shared.bo.Nutzer;
import de.hdm.KontaktSharing.shared.report.AllKontaktByNutzerReport;
import de.hdm.KontaktSharing.shared.report.HTMLReportWriter;
import de.hdm.KontaktSharing.shared.report.PlainTextReportWriter;

public class NaviReport {

	
	final Button allKontakteReport = new Button("Alle Kontakte");
	final Button selektierteEigenschaftenByNutzer = new Button("Nach ausgewaehlten Eigenschaften");
	final Button geteilteKontakte = new Button("Geteilte Kontakte");
	Nutzer n = new Nutzer();
	
	
	/**
	 * Konstruktor der Klasse
	 */
	public NaviReport(){
		//VerticalPanel erstellen
		VerticalPanel vp = new VerticalPanel();
		
		//Buttons dem VerticalPanel hinzufuegen
		vp.add(allKontakteReport);
		vp.add(selektierteEigenschaftenByNutzer);
		vp.add(geteilteKontakte);
		
		allKontakteReport.setStylePrimaryName("navi-button");
		selektierteEigenschaftenByNutzer.setStylePrimaryName("navi-button");
		geteilteKontakte.setStylePrimaryName("navi-button");
		
		//VerticalPanel dem RootPanel hinzufuegen
		RootPanel.get("navigator").add(vp);
		
		
		
		allKontakteReport.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				
				Window.alert("Button wurde geklickt");
				RootPanel.get("content").clear();
				AllKontaktByNutzer allKonRe = new AllKontaktByNutzer();
				Window.alert(allKonRe +"");
				RootPanel.get("content").add(allKonRe);
//				n.setId(Integer.valueOf(Cookies.getCookie("id")));
//				n.setEmail(Cookies.getCookie("email"));
//					final HTMLReportWriter writer = new HTMLReportWriter();			
//					//final PlainTextReportWriter writer1 = new PlainTextReportWriter();		
//					
//					Window.alert(n.toString());
//					ClientsideSettings.getReportGenerator().createAllKontaktReport(n, new AsyncCallback<AllKontaktByNutzerReport>(){
//
//						@Override
//						public void onFailure(Throwable caught) {
//							caught.getMessage().toString();
//							
//						}
//
//						@Override
//						public void onSuccess(AllKontaktByNutzerReport result) {
//							
//							RootPanel.get("content").clear();
//							writer.process(result);
//							HTML html = new HTML("<div align=\"center\">" + writer.getReportText()+ "</br></div>");
//							RootPanel.get("content").add(html);
//							
//						}
//						
//					});
			}
			
		});
		
		selektierteEigenschaftenByNutzer.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
					Window.alert("Hello1");				
			}
			
		});
		
		geteilteKontakte.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
					Window.alert("Hello2");				
			}
			
		});
		
	}
	
	
}
