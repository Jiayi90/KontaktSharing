package de.hdm.KontaktSharing.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.KontaktSharing.shared.bo.*;

/**
 * 
 * 
 *
 */
public interface KontaktSharingAdministrationAsync {
	/**
	 * Nutzer setzen
	 * @param nutzer
	 * @param callback
	 */
	void setNutzer(Nutzer nutzer, AsyncCallback<Void> callback);

	/**
	 * 
	 * @param callback
	 */
	void init(AsyncCallback<Void> callback);

	/**
	 * alle Kontakte des Nutzers auslesen
	 * @param id
	 * @param callback
	 */
	void getAllKontaktByNutzer(int id, AsyncCallback<Vector<Kontakt>> callback);

	/**
	 * Kontakt anlegen
	 * @param k
	 * @param callback
	 */
	void createKontakt(Kontakt k, AsyncCallback<Kontakt> callback);

	/**
	 * Eigenschaft anelegen
	 * @param k
	 * @param callback
	 */
	void createEigenschaft(Kontakt k, AsyncCallback<Eigenschaft> callback);

	/**
	 * Eigenschaftauspraegung anlegen
	 * @param e
	 * @param callback
	 */
	void createEigenschaftauspraegung(Eigenschaftauspraegung e, AsyncCallback<Eigenschaftauspraegung> callback);

	/**
	 * Kontaktliste anlegen
	 * @param n
	 * @param callback
	 */
	void createKontaktliste(Nutzer n, AsyncCallback<Kontaktliste> callback);

	/**
	 * Kontakt l�schen
	 * @param k
	 * @param callback
	 */
	void delete(Kontakt k, AsyncCallback<Void> callback);

	/**
	 * Eigenschaft loeschen
	 * @param e
	 * @param callback
	 */
	void delete(Eigenschaft e, AsyncCallback<Void> callback);

	/**
	 * Kontaktliste loeschen
	 * @param kl
	 * @param callback
	 */
	void delete(Kontaktliste kl, AsyncCallback<Void> callback);

	/**
	 * Eigenschaftauspraegung loeschen
	 * @param ea
	 * @param callback
	 */
	void delete(Eigenschaftauspraegung ea, AsyncCallback<Void> callback);

	/**
	 * Eigenschaft aktualisieren
	 * @param e
	 * @param callback
	 */
	void update(Eigenschaft e, AsyncCallback<Void> callback);

	/**
	 * Eigenschaft aktualisieren
	 * @param ea
	 * @param callback
	 */
	void update(Eigenschaftauspraegung ea, AsyncCallback<Void> callback);

	/**
	 * Kontaktliste aktualisieren
	 * @param kl
	 * @param callback
	 */
	void update(Kontaktliste kl, AsyncCallback<Void> callback);

	/**
	 * Kontakt aktualisieren
	 * @param k
	 * @param callback
	 */
	void update(Kontakt k, AsyncCallback<Void> callback);

	/**
	 * Eigenschaftauspraegung auslesen
	 * @param e
	 * @param callback
	 */
	void getEigenschaftauspraegungOf(Eigenschaftauspraegung e,
			AsyncCallback<ArrayList<Eigenschaftauspraegung>> callback);

	/**
	 * Eigenschaft von Kontakt auslesen
	 * @param k
	 * @param callback
	 */
	void getEigenschaftOf(Kontakt k, AsyncCallback<List<Eigenschaftauspraegung>> callback);

	/**
	 * Kontakt aus der Kontaktliste auslesen
	 * @param kl
	 * @param callback
	 */
	void getKontaktOf(Kontaktliste kl, AsyncCallback<List<Kontakt>> callback);
	
	/**
	 * Nutzer anhand der Email auslesen
	 * @param mail
	 * @param callback
	 */
	void getNutzerByEmail(String mail, AsyncCallback<Nutzer> callback);
	
	/**
	 * Nutzer auslesen
	 * @param callback
	 */
	void getNutzer(AsyncCallback<Nutzer> callback);
	
	/**
	 * Alle Eigenschaften auslesen
	 * @param callback
	 */
	void getAllEigenschaft(AsyncCallback<Vector<Eigenschaft>> callback);
	
	/**
	 * alle Eigenschaftauspraegung des Kontakts auslesen
	 * @param kontakt
	 * @param callback
	 */
	void getAllEigenschaftauspraegungByKontakt(Kontakt kontakt, AsyncCallback<Vector<Eigenschaftauspraegung>> callback);

	/**
	 * Eigenschaftauspraegung anlegen
	 * @param auspraegungen
	 * @param callback
	 */
	void createEigenschaftauspraegungen(Vector<Eigenschaftauspraegung> auspraegungen, AsyncCallback<Void> callback);

	/**
	 * alle Kontaktlisten auslesen
	 * @param callback
	 */
	void getAllKontaktlisten(AsyncCallback<Vector<Kontaktliste>> callback);

	/**
	 * alle Kontaktlisten mit User f�r Nutzer auslesen
	 * @param idNutzer
	 * @param callback
	 */
	void getAllKontaktlistenWithUserCountForNutzer(int idNutzer, AsyncCallback<Vector<Kontaktliste>> callback);

	/**
	 * Kontaktliste mit User Info auslesen
	 * @param idKontaktliste
	 * @param callback
	 */
	void getKontaktlisteWithUserinformation(int idKontaktliste, AsyncCallback<Kontaktliste> callback);

	/**
	 * alle Kontakte mit name anhand des Nutzers auslesen
	 * @param id
	 * @param callback
	 */
	void getAllKontaktWithNameByNutzer(int id, AsyncCallback<Vector<Kontakt>> callback);

	/**
	 * Kontaktliste fuer Nutzer anlegen
	 * @param idNutzer
	 * @param name
	 * @param idsKontakte
	 * @param callback
	 */
	void createKontaktlisteForNutzer(int idNutzer, String name, List<Integer> idsKontakte,
			AsyncCallback<Void> callback);

	/**
	 * Kontaktliste aktualisieren
	 * @param id
	 * @param name
	 * @param idsKontakte
	 * @param callback
	 */
	void updateKontaktliste(int id, String name, List<Integer> idsKontakte, AsyncCallback<Void> callback);

	/**
	 * aktuellen Nutzer setzen
	 * @param id
	 * @param callback
	 */
	void setCurrentNutzerId(int id, AsyncCallback<Void> callback);

	/**
	 * Nutzer ueberpruefen
	 * @param mail
	 * @param callback
	 */
	void checkNutzer(String mail, AsyncCallback<Nutzer> callback);

	/**
	 * Nutzer anhand der mail auslesen oder anlegen
	 * @param email
	 * @param callback
	 */
	void getNutzerByMailOrCreate(String email, AsyncCallback<Nutzer> callback);
	
	void shareKontakt(int idNutzer, List<Integer> ids, List<String> mails, AsyncCallback<Void> callback);

	void getSharedKontakteForUser(int idNutzer, AsyncCallback<Vector<TeilhaberschaftKontakt>> callback);


	/**
	 * geteilte Liste abrufen
	 * @param idNutzer
	 * @param idListe
	 * @param mails
	 * @param callback
	 */
	void shareListe(int idNutzer, int idListe, List<String> mails, AsyncCallback<Void> callback);

	/**
	 * auslesen der geteilten Kontaktliste f�r User
	 * @param idNutzer
	 * @param callback
	 */
	void getSharedKontaktlistenForUser(int idNutzer, AsyncCallback<Vector<TeilhaberschaftKontaktliste>> callback);

	/**
	 * 
	 * @param idNutzer
	 * @param callback
	 */
	void getAllNutzerWithoutCurrent(int idNutzer, AsyncCallback<Vector<Nutzer>> callback);

	/**
	 * alle Nutzer in Teilhaberschaft auslesen
	 * @param idTeilhaberschaft
	 * @param callback
	 */
	void getAllNutzerInTeilhaberschaft(int idTeilhaberschaft, AsyncCallback<Vector<Nutzer>> callback);

	/**
	 * loeschen der teilhaberschaft
	 * @param idTeilhaberschaft
	 * @param callback
	 */
	void deleteTeilhaberschaft(int idTeilhaberschaft, AsyncCallback<Void> callback);
	/**
	 * Teilhabende Liste aktualisieren
	 * @param idTeilhaberschaft
	 * @param mails
	 * @param callback
	 */
	void updateTeilhaberschaftListe(int idTeilhaberschaft, List<String> mails, AsyncCallback<Void> callback);

	void deleteTeilhaberschaftForUser(int idNutzer, Kontakt kontakt, AsyncCallback<Void> callback);

	void deleteTeilhaberschaftForUser(int loggedInId, Kontaktliste liste, AsyncCallback<Void> callback);
}
