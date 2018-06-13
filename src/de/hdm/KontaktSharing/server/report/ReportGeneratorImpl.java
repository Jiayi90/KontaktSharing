package de.hdm.KontaktSharing.server.report;

import java.util.Date;
import java.util.Vector;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hdm.KontaktSharing.server.KontaktSharingAdministrationImpl;
import de.hdm.KontaktSharing.shared.*;
import de.hdm.KontaktSharing.shared.bo.*;
import de.hdm.KontaktSharing.shared.report.*;


/**
 * Implementierung des <code>ReportGenerator</code>-Interface. Die technische
 * Realisierung bzgl. <code>RemoteServiceServlet</code> bzw. GWT RPC erfolgt
 * analog zu {@KontaktSharingAdministrationImpl}. Für Details zu GWT RPC siehe dort.
 * 
 * @see ReportGenerator
 * @author thies , Vi Quan
 */

@SuppressWarnings("serial")

public class ReportGeneratorImpl extends RemoteServiceServlet
implements ReportGenerator {
	
	/**
	   * Ein ReportGenerator benötigt Zugriff auf die KontaktSharing, da diese die
	   * essentiellen Methoden für die Koexistenz von Datenobjekten (vgl.
	   * bo-Package) bietet.
	   */
	
	private KontaktSharingAdministration administration = null;
	
	 /**
	   * <p>
	   * Ein <code>RemoteServiceServlet</code> wird unter GWT mittels
	   * <code>GWT.create(Klassenname.class)</code> Client-seitig erzeugt. Hierzu
	   * ist ein solcher No-Argument-Konstruktor anzulegen. Ein Aufruf eines anderen
	   * Konstruktors ist durch die Client-seitige Instantiierung durch
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
	   * Initialsierungsmethode. Siehe dazu Anmerkungen zum No-Argument-Konstruktor.
	   * 
	   * @see #ReportGeneratorImpl()
	   */
	
	@Override
	
	public void init() throws IllegalArgumentException {
		
	    /*
	     * Ein ReportGeneratorImpl-Objekt instantiiert für seinen Eigenbedarf eine
	     * KontaktSharingImpl-Instanz.
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
	  * Erstellen von <code>AllKontaktReport<code>-Objekten.
	  * 
	  * @param k das Kontaktobjekt bzgl. dessen Report erstellt werden soll. 
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
		 
		 return result;
	 }

	@Override
	public AllKontaktByNutzerReport createAllKontaktByNutzer(Nutzer n, Eigenschaft e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SelectedEigenschaftauspraegungByNutzer createSelectedEigenschaftauspraegungByNutzer(Nutzer n,
			Eigenschaftauspraegung ea) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShareKontaktByNutzer createShareKontaktByNutzer(Nutzer n, Kontakt k) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
}
