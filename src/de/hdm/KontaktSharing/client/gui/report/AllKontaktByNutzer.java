package de.hdm.KontaktSharing.client.gui.report;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.KontaktSharing.client.ClientsideSettings;
import de.hdm.KontaktSharing.shared.ReportGeneratorAsync;
import de.hdm.KontaktSharing.shared.bo.Nutzer;
import de.hdm.KontaktSharing.shared.report.AllKontaktByNutzerReport;
import de.hdm.KontaktSharing.shared.report.HTMLReportWriter;



public class AllKontaktByNutzer extends HorizontalPanel {
	

	private static ReportGeneratorAsync reportverwaltung = ClientsideSettings.getReportGenerator();
	
	
	
	Nutzer nutzer1 = new Nutzer();
	
	public void onLoad() {
		
		
		
		super.onLoad();
	
	
		nutzer1.setId(3);
		nutzer1.setEmail("test@example.com");
		
		RootPanel.get("content").clear();
		
		reportverwaltung.createAllKontaktReport(nutzer1, new AsyncCallback<AllKontaktByNutzerReport>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub


				Window.alert("Springt in failure" +caught);
			}

			@Override
			public void onSuccess(AllKontaktByNutzerReport result) {
				// TODO Auto-generated method stub
				
				Window.alert("Springt in succes");
				if (result != null) {
					Window.alert("report ist NICHT  null");
					HTMLReportWriter writer = new HTMLReportWriter();
					writer.process(result);

					RootPanel.get("content").clear();

					RootPanel.get("content").add(new HTML(writer.getReportText()));
				}
			}
		});
	
	
	}
	
	
		
	}
	

