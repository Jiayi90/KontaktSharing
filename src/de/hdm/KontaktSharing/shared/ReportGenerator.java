package de.hdm.KontaktSharing.shared;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.KontaktSharing.shared.bo.Eigenschaft;
import de.hdm.KontaktSharing.shared.bo.Eigenschaftauspraegung;
import de.hdm.KontaktSharing.shared.bo.Kontakt;
import de.hdm.KontaktSharing.shared.bo.Nutzer;
import de.hdm.KontaktSharing.shared.report.AllKontaktByNutzer;
import de.hdm.KontaktSharing.shared.report.AllKontaktReport;
import de.hdm.KontaktSharing.shared.report.SelectedEigenschaftauspraegungByNutzer;
import de.hdm.KontaktSharing.shared.report.ShareKontaktByNutzer;

/**
 * <p>
 * Synchrone Schnittstelle f�r eine RPC-f�hige Klasse zur Erstellung von
 * Reports. Diese Schnittstelle benutzt die gleiche Realisierungsgrundlage wir
 * das Paar {@link BankAdministration} und {@lBankAdministrationImplImpl}. Zu
 * technischen Erl�uterung etwa bzgl. GWT RPC bzw. {@link RemoteServiceServlet}
 * siehe {@link KontaktSharingAdministration} und KontaktSharingAdministrationImpl}.
 * </p>
 * <p>
 * Ein ReportGenerator bietet die M�glichkeit, eine Menge von Berichten
 * (Reports) zu erstellen, die Menge von Daten bzgl. bestimmter Sachverhalte des
 * Systems zweckspezifisch darstellen.
 * </p>
 * <p>
 * Die Klasse bietet eine Reihe von <code>create...</code>-Methoden, mit deren
 * Hilfe die Reports erstellt werden k�nnen. Jede dieser Methoden besitzt eine
 * dem Anwendungsfall entsprechende Parameterliste. Diese Parameter ben�tigt der
 * der Generator, um den Report erstellen zu k�nnen.
 * </p>
 * <p> 
 * Bei neu hinzukommenden Bedarfen an Berichten, kann diese Klasse auf einfache
 * Weise erweitert werden. Hierzu k�nnen zus�tzliche <code>create...</code>
 * -Methoden implementiert werden. Die bestehenden Methoden bleiben davon
 * unbeeinflusst, so dass bestehende Programmlogik nicht ver�ndert werden muss.
 * </p>
 * 
 * @author thies & jiayi
 */

public interface ReportGenerator extends RemoteService {
	
	 /**
	   * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
	   * RPC zus�tzlich zum No Argument Constructor der implementierenden Klasse
	   *KontaktSharingAdministrationImpl} notwendig. Bitte diese Methode direkt nach der
	   * Instantiierung aufrufen.
	   * 
	   * @throws IllegalArgumentException
	   */
	  public void init() throws IllegalArgumentException;

	  /**
	   * Erstellen eines <code>AllKontaktByNutzer</code>-Reports. Dieser
	   * Report-Typ stellt sämtliche Kontakte eines Nutzers dar.
	   * 
	   * @param Nutzer n und Eigenschaft e
	   * @return das fertige Reportobjekt
	   * @throws IllegalArgumentException
	   */
	  public abstract AllKontaktByNutzer createAllKontaktByNutzer(
	      Nutzer n, Eigenschaft e) throws IllegalArgumentException;

	  /**
	   * Erstellen eines <code>SelectedEigenschaftauspraegungByNutzer</code>-Reports.
	   * Dieser Report-Typ stellt sämtliche ausgewaehlte Eigenschaftauspraegungen eines Nutzers dar.
	   * 
	   * @param Nutzer n und Eigenschaftauspraegung ea
	   * @return das fertige Reportobjekt
	   * @throws IllegalArgumentException
	   */
	  public abstract SelectedEigenschaftauspraegungByNutzer createSelectedEigenschaftauspraegungByNutzer(
		      Nutzer n, Eigenschaftauspraegung ea) throws IllegalArgumentException;
	
	  /**
	   * Erstellen eines <code>SelectedEigenschaftauspraegungByNutzer</code>-Reports.
	   * Dieser Report-Typ stellt sämtliche geteilte Kontakte eines Nutzers dar.
	   * 
	   * @param Nutzer n und Kontakt k
	   * @return das fertige Reportobjekt
	   * @throws IllegalArgumentException
	   */
	  public abstract ShareKontaktByNutzer createShareKontaktByNutzer(
		      Nutzer n, Kontakt k) throws IllegalArgumentException;

	AllKontaktReport createAllKontaktReport(Kontakt k) throws IllegalArgumentException;
	  
}  