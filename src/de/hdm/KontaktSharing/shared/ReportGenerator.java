package de.hdm.KontaktSharing.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.KontaktSharing.shared.bo.Eigenschaftauspraegung;
import de.hdm.KontaktSharing.shared.bo.Kontakt;
import de.hdm.KontaktSharing.shared.bo.Nutzer;
import de.hdm.KontaktSharing.shared.report.AllKontaktByNutzerReport;
import de.hdm.KontaktSharing.shared.report.SelectedEigenschaftauspraegungByNutzerReport;
import de.hdm.KontaktSharing.shared.report.ShareKontaktByNutzerReport;

/**
 * <p>
 * Synchrone Schnittstelle fuer eine RPC-faehige Klasse zur Erstellung von
 * Reports. Diese Schnittstelle benutzt die gleiche Realisierungsgrundlage wir
 * das Paar {@link KontaktSharingAdministration} und
 * {@KontaktSharingAdministrationImpl}. Zu technischen Erlaeuterung etwa bzgl.
 * GWT RPC bzw. {@link RemoteServiceServlet} siehe
 * {@link KontaktSharingAdministration} und KontaktSharingAdministrationImpl}.
 * </p>
 * <p>
 * Ein ReportGenerator bietet die Moeglichkeit, eine Menge von Berichten
 * (Reports) zu erstellen, die Menge von Daten bzgl. bestimmter Sachverhalte des
 * Systems zweckspezifisch darstellen.
 * </p>
 * <p>
 * Die Klasse bietet eine Reihe von <code>create...</code>-Methoden, mit deren
 * Hilfe die Reports erstellt werden koennen. Jede dieser Methoden besitzt eine
 * dem Anwendungsfall entsprechende Parameterliste. Diese Parameter benoetigt
 * der der Generator, um den Report erstellen zu koennen.
 * </p>
 * <p>
 * Bei neu hinzukommenden Bedarfen an Berichten, kann diese Klasse auf einfache
 * Weise erweitert werden. Hierzu k�nnen zus�tzliche <code>create...</code>
 * -Methoden implementiert werden. Die bestehenden Methoden bleiben davon
 * unbeeinflusst, so dass bestehende Programmlogik nicht ver�ndert werden
 * muss.
 * </p>
 * 
 * @author thies & jiayi
 */
@RemoteServiceRelativePath("reportgenerator")
public interface ReportGenerator extends RemoteService {
	/**
	 * Erstellen eines <code>AllKontaktByNutzer</code>-Reports. Dieser
	 * Report-Typ stellt saemtliche Kontakte eines Nutzers dar.
	 * 
	 * @param Nutzer
	 *            n und Eigenschaft e
	 * @return das fertige Reportobjekt
	 * @throws IllegalArgumentException
	 */
	public abstract AllKontaktByNutzerReport createAllKontaktByNutzer(Nutzer n) throws IllegalArgumentException;
	
	/**
	 * alle Kontakte des Nutzers im Report anlegen
	 * @param nutzer
	 * @return
	 * @throws IllegalArgumentException
	 */
	public AllKontaktByNutzerReport createAllKontaktReport(Nutzer nutzer) throws IllegalArgumentException;
	
	/**
	 * ausgewaehlte Eigenschaftauspraegungen des Nutzers im Report anlegen
	 * @param n
	 * @param ea
	 * @return
	 */
	public SelectedEigenschaftauspraegungByNutzerReport createSelectedEigenschaftauspraegungByNutzer(Nutzer n,
			Eigenschaftauspraegung ea);

	/**
	 * geteilte Kontakte des Nutzers im Report anlegen
	 * @param n
	 * @param k
	 * @return
	 */
	public ShareKontaktByNutzerReport createShareKontaktByNutzer(Nutzer n, Kontakt k);

}
