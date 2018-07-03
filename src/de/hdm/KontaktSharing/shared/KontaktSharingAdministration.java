package de.hdm.KontaktSharing.shared;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


import de.hdm.KontaktSharing.shared.bo.Eigenschaft;
import de.hdm.KontaktSharing.shared.bo.Eigenschaftauspraegung;
import de.hdm.KontaktSharing.shared.bo.Kontakt;
import de.hdm.KontaktSharing.shared.bo.Kontaktliste;
import de.hdm.KontaktSharing.shared.bo.Nutzer;

@RemoteServiceRelativePath("administrationService")
public interface KontaktSharingAdministration extends RemoteService {
	
	public void init() throws IllegalArgumentException;
	
	public Vector<Kontakt> getAllKontaktByNutzer(int id) throws IllegalArgumentException, Exception;
	
	public Vector<Kontakt> getAllKontaktWithNameByNutzer(int id) throws IllegalArgumentException, Exception;

	/**
	 * Ein Nutzer anlegen
	 */
	
	public void setNutzer(Nutzer n) throws IllegalArgumentException;

	  /**
	   * Suche Nutzer, dessen Email bekannt ist.
	   * 
	   * @param Email des Nutzers
	   * @return Email des Nutzers
	   * @throws IllegalArgumentException
	   */
	
	  public Nutzer getNutzerByEmail(String email) throws IllegalArgumentException;
	  
	/**
	 * Einen neuen Kontakt für den Nutzer erstellen.
	 * @param Kontakt k
	 * @return neuen Kontakt erstellt. 
	 * @throws SQLException 
	 */
	
	public Kontakt createKontakt(Kontakt k) throws IllegalArgumentException, Exception;

	/**
	 * Speichern eines Kontakt-Objekts in der Datenbank.
	 * @param k zu sicherndes Objekt
	 * @throws IllegalArgumentException
	 */
	
	public void update(Kontakt k) throws IllegalArgumentException;
	
	/**
	 * Löschen des übergebenen Kontaktes 
	 * @param k das zu löschende Kontakt
	 * @throws IllegalArgumentException
	 * @throws SQLException 
	 */
	
	public void delete(Kontakt k) throws IllegalArgumentException, Exception;
	
	/**
	 * Eine neue Kontaktliste für den Nutzer erstellen.
	 * @param Nutzer n
	 * @return neue Kontaktliste erstellt. 
	 */
	
	public Kontaktliste createKontaktliste(Nutzer n) throws IllegalArgumentException;
	
	/**
	 * Speichern eines Kontaktliste-Objekts in der Datenbank.
	 * @param kl zu sicherndes Objekt
	 * @throws IllegalArgumentException
	 */
	
	public void update(Kontaktliste kl) throws IllegalArgumentException;
	
	/**
	 * Löschen der übergebenen Kontaktliste 
	 * @param kl das zu löschende Kontakt
	 * @throws IllegalArgumentException
	 * @throws Exception 
	 */
	
	public void delete(Kontaktliste kl) throws IllegalArgumentException, Exception;
	
	/**
	 * Eine neue Eigenschaft für den Kontakt erstellen.
	 * @param Kontakt k
	 * @return neue Eigenschaft erstellt. 
	 */
	
	public Eigenschaft createEigenschaft (Kontakt k) throws IllegalArgumentException;
	
	/**
	 * Speichern eines Eigenschaft-Objekts in der Datenbank.
	 * @param e zu sicherndes Objekt
	 * @throws IllegalArgumentException
	 */
	
	public void update(Eigenschaft e) throws IllegalArgumentException;
	
	/**
	 * Löschen der übergebenen Eigenschaft 
	 * @param e das zu löschende Eigenschaft
	 * @throws IllegalArgumentException
	 */
	
	public void delete(Eigenschaft e) throws IllegalArgumentException;
	
	/**
	 * Eine neue Eigenschaftschaftauspraegung für die Eigenschaft erstellen.
	 * @param Eigenschaft e
	 * @return neue Eigenschaftschaftauspraegung erstellt. 
	 * @throws SQLException 
	 */

	public Eigenschaftauspraegung createEigenschaftauspraegung (Eigenschaftauspraegung e) throws IllegalArgumentException, Exception;

	/**
	 * Speichern eines Eigenschaftauspraegung-Objekts in der Datenbank.
	 * @param ea zu sicherndes Objekt
	 * @throws IllegalArgumentException
	 * @throws Exception 
	 */
	
	public void update(Eigenschaftauspraegung ea) throws IllegalArgumentException, Exception;
	
	/**
	 * Löschen der übergebenen Eigenschaftauspraegung 
	 * @param ea das zu löschende Eigenschaftauspraegung
	 * @throws IllegalArgumentException
	 * @throws SQLException 
	 */
	
	public void delete(Eigenschaftauspraegung ea) throws IllegalArgumentException, Exception;
	
	/**
	 * Auslesen sämtlicher Kontakte aus einer Kontaktliste
	 * @param Kontaktliste kl
	 * @return Eine Liste aller Kontakte aus der Kontaktliste
	 * @throws Exception 
	 */
	
	public List<Kontakt> getKontaktOf(Kontaktliste kl) throws IllegalArgumentException, Exception;
	
	/**
	 * Auslesen sämtlicher Eigenschaften aus einem Kontakt
	 * @param Kontakt k
	 * @return Eine Liste aller Eigenschaften aus dem Kontakt
	 * @throws SQLException 
	 */
	
	public List<Eigenschaftauspraegung> getEigenschaftOf(Kontakt k) throws IllegalArgumentException, Exception;
	
	/**
	 * Auslesen sämtlicher Eigenschaftauspraegungen aus einer Eigenschaft
	 * @param Eigenschaft e
	 * @return Eine Liste aller Eigenschaftauspraegungen aus einer Eigenschaft
	 */
	
	public ArrayList<Eigenschaftauspraegung> getEigenschaftauspraegungOf(Eigenschaftauspraegung e) throws IllegalArgumentException;

	/**
	 * Auslesen des Nutzers
	 * @return Nutzer
	 */
	
	public Nutzer getNutzer() throws IllegalArgumentException;
	
	public Vector<Eigenschaft> getAllEigenschaft() throws IllegalArgumentException, Exception;
	
	public Vector<Eigenschaftauspraegung> getAllEigenschaftauspraegungByKontakt(Kontakt kontakt) throws IllegalArgumentException, Exception;
	
	public void createEigenschaftauspraegungen(Vector<Eigenschaftauspraegung> auspraegungen) throws IllegalArgumentException, Exception;
	
	public Vector<Kontaktliste> getAllKontaktlisten() throws IllegalArgumentException, Exception;
	
	public void createKontaktlisteForNutzer(int idNutzer, String name, List<Integer> idsKontakte) throws IllegalArgumentException, Exception;
	
	public void updateKontaktliste(int id, String name, List<Integer> idsKontakte) throws IllegalArgumentException, Exception;

	public Vector<Kontaktliste> getAllKontaktlistenWithUserCountForNutzer(int idNutzer) throws IllegalArgumentException, Exception;

	public Kontaktliste getKontaktlistenWithUserinformationForNutzer(int idNutzer) throws IllegalArgumentException, Exception;;
	
	public void setCurrentNutzerId(int id);
	
	/**
	 * Prüfen des Nutzers
	 * @param email
	 * @return Nutzer
	 */
	public Nutzer checkNutzer(String email) ;
	
	public Nutzer getNutzerByMailOrCreate(String email) throws Exception;
}
