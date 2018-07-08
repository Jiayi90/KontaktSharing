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
import de.hdm.KontaktSharing.shared.bo.Teilhaberschaft;
import de.hdm.KontaktSharing.shared.bo.TeilhaberschaftKontaktliste;

@RemoteServiceRelativePath("administrationService")
public interface KontaktSharingAdministration extends RemoteService {
	
	public void init() throws IllegalArgumentException;
	
	public Vector<Kontakt> getAllKontaktByNutzer(int id) throws IllegalArgumentException, Exception;
	
	public Vector<Kontakt> getAllKontaktWithNameByNutzer(int id) throws IllegalArgumentException, Exception;

	/**
	 * Ein Nutzer anlegen
	 */
	
	/**
	 * 
	 * @param n
	 * @throws IllegalArgumentException
	 */
	public void setNutzer(Nutzer n) throws IllegalArgumentException;

	  /**
	   * Suche Nutzer, dessen Email bekannt ist.
	   * 
	   * @param email
	   * @return Email des Nutzers
	   * @throws IllegalArgumentException
	   */
	
	  public Nutzer getNutzerByEmail(String email) throws IllegalArgumentException;
	  
	/**
	 * Einen neuen Kontakt fuer den Nutzer erstellen.
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
	 * Loeschen des Uebergebenen Kontaktes 
	 * @param k das zu loeschende Kontakt
	 * @throws IllegalArgumentException
	 * @throws SQLException 
	 */
	
	public void delete(Kontakt k) throws IllegalArgumentException, Exception;
	
	/**
	 * Eine neue Kontaktliste fuer den Nutzer erstellen.
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
	 * Loeschen der Uebergebenen Kontaktliste 
	 * @param kl das zu loeschende Kontakt
	 * @throws IllegalArgumentException
	 * @throws Exception 
	 */
	
	public void delete(Kontaktliste kl) throws IllegalArgumentException, Exception;
	
	/**
	 * Eine neue Eigenschaft fuer den Kontakt erstellen.
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
	 * Loeschen der Uebergebenen Eigenschaft 
	 * @param e das zu loeschende Eigenschaft
	 * @throws IllegalArgumentException
	 */
	
	public void delete(Eigenschaft e) throws IllegalArgumentException;
	
	/**
	 * Eine neue Eigenschaftschaftauspraegung fuer die Eigenschaft erstellen.
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
	 * Loeschen der Uebergebenen Eigenschaftauspraegung 
	 * @param ea das zu loeschende Eigenschaftauspraegung
	 * @throws IllegalArgumentException
	 * @throws SQLException 
	 */
	
	public void delete(Eigenschaftauspraegung ea) throws IllegalArgumentException, Exception;
	
	/**
	 * Auslesen saemtlicher Kontakte aus einer Kontaktliste
	 * @param Kontaktliste kl
	 * @return Eine Liste aller Kontakte aus der Kontaktliste
	 * @throws Exception 
	 */
	
	public List<Kontakt> getKontaktOf(Kontaktliste kl) throws IllegalArgumentException, Exception;
	
	/**
	 * Auslesen saemtlicher Eigenschaften aus einem Kontakt
	 * @param Kontakt k
	 * @return Eine Liste aller Eigenschaften aus dem Kontakt
	 * @throws SQLException 
	 */
	
	public List<Eigenschaftauspraegung> getEigenschaftOf(Kontakt k) throws IllegalArgumentException, Exception;
	
	/**
	 * Auslesen saemtlicher Eigenschaftauspraegungen aus einer Eigenschaft
	 * @param Eigenschaft e
	 * @return Eine Liste aller Eigenschaftauspraegungen aus einer Eigenschaft
	 */
	
	public ArrayList<Eigenschaftauspraegung> getEigenschaftauspraegungOf(Eigenschaftauspraegung e) throws IllegalArgumentException;

	/**
	 * Auslesen des Nutzers
	 * @return Nutzer
	 */
	
	public Nutzer getNutzer() throws IllegalArgumentException;
	
	/**
	 * alle Eigenschaften
	 * @return
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public Vector<Eigenschaft> getAllEigenschaft() throws IllegalArgumentException, Exception;
	
	/**
	 * alle Eigenschaftauspraegung des Kontakts auslesen
	 * @param kontakt
	 * @return kontakt
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public Vector<Eigenschaftauspraegung> getAllEigenschaftauspraegungByKontakt(Kontakt kontakt) throws IllegalArgumentException, Exception;
	
	/**
	 * Eigenschaftauspraegung anlegen
	 * @param auspraegungen
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public void createEigenschaftauspraegungen(Vector<Eigenschaftauspraegung> auspraegungen) throws IllegalArgumentException, Exception;
	
	/**
	 * alle Kontaktlisten auslesen
	 * @return
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public Vector<Kontaktliste> getAllKontaktlisten() throws IllegalArgumentException, Exception;
	
	/**
	 * Kontaktliste fuer Nutzer anlegen
	 * @param idNutzer
	 * @param name
	 * @param idsKontakte
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public void createKontaktlisteForNutzer(int idNutzer, String name, List<Integer> idsKontakte) throws IllegalArgumentException, Exception;
	
	/**
	 * Kontaktliste aktualisieren
	 * @param id
	 * @param name
	 * @param idsKontakte
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public void updateKontaktliste(int id, String name, List<Integer> idsKontakte) throws IllegalArgumentException, Exception;

	/**
	 * alle Kontaktlisten mit den aktuellen User fuer Nutzer auslesen
	 * @param idNutzer
	 * @return idNutzer
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public Vector<Kontaktliste> getAllKontaktlistenWithUserCountForNutzer(int idNutzer) throws IllegalArgumentException, Exception;

	/**
	 * auslesen der Kontaktliste mit den User Infos
	 * @param idKontaktliste
	 * @return idKontaktliste
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public Kontaktliste getKontaktlisteWithUserinformation(int idKontaktliste) throws IllegalArgumentException, Exception;
	
	/**
	 * aktuellen Nutzer setzen
	 * @param id
	 */
	public void setCurrentNutzerId(int id);
	
	/**
	 * Pruefen des Nutzers
	 * @param email
	 * @return Nutzer
	 */
	public Nutzer checkNutzer(String email) ;
	
	/**
	 * Nutzer anhand der Email auslesen
	 * @param email
	 * @return email
	 * @throws Exception
	 */
	public Nutzer getNutzerByMailOrCreate(String email) throws Exception;
	
	/**
	 * geteilte Liste
	 * @param idNutzer
	 * @param idListe
	 * @param mails
	 * @throws Exception
	 */
	public void shareListe(int idNutzer,int idListe, List<String> mails) throws Exception;
	
	/**
	 * auslesen der geteilte Kontaktlisten fuer User
	 * @param idNutzer
	 * @return idNutzer
	 * @throws Exception
	 */
	public Vector<TeilhaberschaftKontaktliste> getSharedKontaktlistenForUser(int idNutzer) throws Exception;
	
	/**
	 * 
	 * @param idNutzer
	 * @return idNutzer
	 * @throws Exception
	 */
	public Vector<Nutzer> getAllNutzerWithoutCurrent(int idNutzer) throws Exception; 
	
	/**
	 * auslesen aller Nutzer die Teilhaber sind
	 * @param idTeilhaberschaft
	 * @return idTeilhaberschaft
	 * @throws Exception
	 */
	public Vector<Nutzer> getAllNutzerInTeilhaberschaft(int idTeilhaberschaft) throws Exception;
	
	/**
	 * loeschen der Teilhaberschaft
	 * @param idTeilhaberschaft
	 * @throws Exception
	 */
	public void deleteTeilhaberschaft(int idTeilhaberschaft) throws Exception;
	
	/**
	 * aktualiseren der Teilhaberschaftliste
	 * @param idTeilhaberschaft
	 * @param mails
	 * @throws Exception
	 */
	public void updateTeilhaberschaftListe(int idTeilhaberschaft, List<String> mails) throws Exception;
}
