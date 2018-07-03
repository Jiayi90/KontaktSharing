package de.hdm.KontaktSharing.client.gui.report;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;

import de.hdm.KontaktSharing.client.ClientsideSettings;
import de.hdm.KontaktSharing.shared.ReportGeneratorAsync;

public class ShareKontaktByNutzer extends HorizontalPanel {

	ReportGeneratorAsync reportverwaltung = ClientsideSettings.getReportGenerator();
	
	private Button thbutton = new Button("Teilhaber anzeigen");
	
}
