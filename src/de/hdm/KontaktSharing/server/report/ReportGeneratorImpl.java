package de.hdm.KontaktSharing.server.report;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.KontaktSharing.client.KontaktSharing;
import de.hdm.KontaktSharing.server.KontaktSharingAdministrationImpl;
import de.hdm.KontaktSharing.shared.*;
import de.hdm.KontaktSharing.shared.bo.*;
import de.hdm.KontaktSharing.shared.report.*;

/**
 * Implementierung des <code>ReportGenerator</code>-Interface. Die technische
 * Realisierung bzgl. <code>RemoteServiceServlet</code> bzw. GWT RPC erfolgt
 * analog zu {@KontaktSharingAdministrationImpl}. Für Details zu GWT RPC siehe
 * dort.
 * 
 * @see ReportGenerator
 * @author thies , Vi Quan
 */

@SuppressWarnings("serial")

public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {
	private static final long serialVersionUID =1L;
	

	private KontaktSharingAdministrationImpl administration = null;

	public ReportGeneratorImpl() throws IllegalArgumentException {
	}

	/*
	  ************************************************************************
	 * ABSCHNITT BEGINN: INITIALISIERUNG
	  ************************************************************************
	 */
	public void init() throws IllegalArgumentException {

		KontaktSharingAdministrationImpl impl = new KontaktSharingAdministrationImpl();
		impl.init();
		this.administration = impl;
	}

	protected KontaktSharingAdministration getAdministration() {
		return this.administration;
	}
	@Override
	public AllKontaktByNutzerReport createAllKontaktByNutzer(Nutzer n) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	public AllKontaktByNutzerReport createAllKontaktReport(Nutzer nutzer) throws IllegalArgumentException {
		if (this.getAdministration() == null) {
			return null;
		}
//		Nutzer n = new Nutzer();
//		n.setId(Integer.valueOf(Cookies.getCookie("id")));
//		n.setEmail(Cookies.getCookie("email"));
		// Die Erstellung einer Instanz dieses Reports
		AllKontaktByNutzerReport report = new AllKontaktByNutzerReport();
 
		// Festlegung des Titels und des Generierungsdatums dieses Reports
		report.setTitle("Alle Kontakte");
		report.setCreated(new Date());

		// Kopfzeile der Reporttabelle; mit den Ueberschriften der einzelnen Spalten
		Row head = new Row();
		head.addColumn(new Column("Name"));


		// Kopfzeile dem Report hinzufuegen
		report.addRow(head);

		// Angeforderte Kontaktdaten in den Vektor laden und dem Report hinzufuegen
		Vector<Kontakt> kontakt = new Vector<Kontakt>();

		// Kontakte der folgenden Vektoren aufrufen und dem ersten Vector hinzufuegen
		
			//this.getAdministration().getAllKontaktByNutzer(n.getId());
		

		// Die Kontakte des gespeicherten Vectors, pro Zeile der Reporttabelle
		// hinzufuegen
		for (int i = 0; i < kontakt.size(); i++) {

			Row kon = new Row();
			kon.addColumn(new Column(kontakt.elementAt(i).getName()));
		

			// Eine Zeile dem Report uebergeben
			report.addRow(kon);
		

		}

		// Rueckgabe der Reportausgabe
		return report;
	}

	@Override
	public SelectedEigenschaftauspraegungByNutzerReport createSelectedEigenschaftauspraegungByNutzer(Nutzer n,
			Eigenschaftauspraegung ea) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShareKontaktByNutzerReport createShareKontaktByNutzer(Nutzer n, Kontakt k) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
