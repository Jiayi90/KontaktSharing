package de.hdm.KontaktSharing.server.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
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

	/**
	 * Ein ReportGenerator benötigt Zugriff auf die KontaktSharing, da diese
	 * die essentiellen Methoden für die Koexistenz von Datenobjekten (vgl.
	 * bo-Package) bietet.
	 */

	private KontaktSharingAdministration administration = null;

	KontaktSharing ks = GWT.create(KontaktSharing.class);

	/**
	 * <p>
	 * Ein <code>RemoteServiceServlet</code> wird unter GWT mittels
	 * <code>GWT.create(Klassenname.class)</code> Client-seitig erzeugt. Hierzu
	 * ist ein solcher No-Argument-Konstruktor anzulegen. Ein Aufruf eines
	 * anderen Konstruktors ist durch die Client-seitige Instantiierung durch
	 * <code>GWT.create(Klassenname.class)</code> nach derzeitigem Stand nicht
	 * möglich.
	 * </p>
	 * <p>
	 * Es bietet sich also an, eine separate Instanzenmethode zu erstellen, die
	 * Client-seitig direkt nach <code>GWT.create(Klassenname.class)</code>
	 * aufgerufen wird, um eine Initialisierung der Instanz vorzunehmen.
	 * </p>
	 */

	public ReportGeneratorImpl() throws IllegalArgumentException {

	}

	/**
	 * Initialsierungsmethode. Siehe dazu Anmerkungen zum
	 * No-Argument-Konstruktor.
	 * 
	 * @see #ReportGeneratorImpl()
	 */

	@Override

	public void init() throws IllegalArgumentException {

		/*
		 * Ein ReportGeneratorImpl-Objekt instantiiert für seinen Eigenbedarf
		 * eine KontaktSharingImpl-Instanz.
		 */

		KontaktSharingAdministrationImpl a = new KontaktSharingAdministrationImpl();
		a.init();
		this.administration = a;

	}

	/**
	 * Auslesen der zugehörigen KontaktSharing (interner Gebrauch).
	 * 
	 * @return das KontaktSharingobjekt
	 */

	protected KontaktSharingAdministration getKontaktSharing() {
		return this.administration;
	}

	/**
	 * Setzen des zugehörigen Nutzer-Objekts.
	 */

	public void setNutzer(Nutzer n) {
		this.administration.setNutzer(n);
	}

	/**
	 * Hinzufügen des Report-Impressum. Diese Methode ist aus den
	 * <code>create...</code>-Methoden ausgegliedert, da jede dieser Methoden
	 * diese Tätigkeiten redundant auszuführen hätte. Stattdessen rufen die
	 * <code>create...</code>-Methoden diese Methode auf.
	 * 
	 * @param r
	 *            der um das Impressum zu erweiterende Report.
	 */

	protected void addImprint(Report r) {

		// Das Impressum soll wesentliche Informationen über den Nutzer
		// enthalten.

		Nutzer n = this.administration.getNutzer();

		// Das Impressum soll mehrzeilig sein.

		CompositeParagraph imprint = new CompositeParagraph();

		imprint.addSubParagraph(new SimpleParagraph(n.getEmail()));

		// Das eigentliche Hinzufügen des Impressums zum Report.
		r.setImprint(imprint);
	}

	/**
	 * Erstellen von <code>AllKontaktReport<code>-Objekten.
	 * 
	 * @param k
	 *            das Kontaktobjekt bzgl. dessen Report erstellt werden soll.
	 * @return der fertige Report
	 */

	@Override
	public AllKontaktByNutzerReport createAllKontaktReport(Kontakt k) throws IllegalArgumentException {

		if (this.getKontaktSharing() == null)
			return null;

		// Zunächst wird ein leerer Report angelegt.

		AllKontaktByNutzerReport result = new AllKontaktByNutzerReport();

		// Jeder Report hat einen Titel

		result.setTitle("Alle Kontakte des Nutzers");

		// Impressum hinzufügen

		this.addImprint(result);

		/*
		 * Das Datum der erstellung des Reports hinzufügen. Automatische
		 * Instantizierung des Date-Objekts new Date().
		 */

		result.setCreated(new Date());

		/*
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die
		 * oben auf dem Report stehen) des Reports. Die Kopfdaten sind
		 * mehrzeilig, daher die Verwendung von CompositeParagraph.
		 */

		CompositeParagraph header = new CompositeParagraph();

		// KontaktId aufnehmen

		header.addSubParagraph(new SimpleParagraph("Kontakt-Id: " + k.getId()));

		result.setHeaderData(header);

		Row headline = new Row();

		headline.addColumn(new Column("Kontakt"));

		// result.addRow(headline);

		List<Eigenschaftauspraegung> kontakte;
		try {
			kontakte = this.administration.getEigenschaftOf(k);

			for (Eigenschaftauspraegung kontakt : kontakte) {

				Row accountRow = new Row();

				accountRow.addColumn(new Column(String.valueOf(k.getId())));
				accountRow.addColumn(
						new Column(String.valueOf(this.administration.getEigenschaftauspraegungOf(kontakt))));

				// result.addRow(accountRow);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	private void addImprint(AllKontaktReport result) {
		// TODO Auto-generated method stub

	}

	@Override
	public AllKontaktByNutzerReport createAllKontaktByNutzer(Nutzer n) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SelectedEigenschaftauspraegungByNutzerReport createSelectedEigenschaftauspraegungByNutzer(Nutzer n,
			Eigenschaftauspraegung ea) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShareKontaktByNutzerReport createShareKontaktByNutzer(Nutzer n, Kontakt k) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Nutzer getNutzerByMail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
}
