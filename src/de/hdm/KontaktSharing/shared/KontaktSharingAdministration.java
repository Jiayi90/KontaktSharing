package de.hdm.KontaktSharing.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.KontaktSharing.shared.bo.*;

@RemoteServiceRelativePath("overview")
public interface KontaktSharingAdministration extends RemoteService {
	
	public void init() throws IllegalArgumentException;

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
	 * @param Nutzer n
	 * @return neuen Kontakt erstellt. 
	 */
	
	public Kontakt createKontakt(Nutzer n) throws IllegalArgumentException;

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
	 */
	
	public void delete(Kontakt k) throws IllegalArgumentException;
	
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
	 */
	
	public void delete(Kontaktliste kl) throws IllegalArgumentException;
	
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
	 */

	public Eigenschaftauspraegung createEigenschaftauspraegung (Eigenschaft e) throws IllegalArgumentException;

	/**
	 * Speichern eines Eigenschaftauspraegung-Objekts in der Datenbank.
	 * @param ea zu sicherndes Objekt
	 * @throws IllegalArgumentException
	 */
	
	public void update(Eigenschaftauspraegung ea) throws IllegalArgumentException;
	
	/**
	 * Löschen der übergebenen Eigenschaftauspraegung 
	 * @param ea das zu löschende Eigenschaftauspraegung
	 * @throws IllegalArgumentException
	 */
	
	public void delete(Eigenschaftauspraegung ea) throws IllegalArgumentException;
	
	/**
	 * Auslesen sämtlicher Kontakte aus einer Kontaktliste
	 * @param Kontaktliste kl
	 * @return Eine Liste aller Kontakte aus der Kontaktliste
	 */
	
	public ArrayList<Kontakt> getKontaktOf(Kontaktliste kl) throws IllegalArgumentException;
	
	/**
	 * Auslesen sämtlicher Eigenschaften aus einem Kontakt
	 * @param Kontakt k
	 * @return Eine Liste aller Eigenschaften aus dem Kontakt
	 */
	
	public ArrayList<Eigenschaft> getEigenschaftOf(Kontakt k) throws IllegalArgumentException;
	
	/**
	 * Auslesen sämtlicher Eigenschaftauspraegungen aus einer Eigenschaft
	 * @param Eigenschaft e
	 * @return Eine Liste aller Eigenschaftauspraegungen aus einer Eigenschaft
	 */
	
	public ArrayList<Eigenschaftauspraegung> getEigenschaftauspraegungOf(Eigenschaft e) throws IllegalArgumentException;

	/**
	 * Auslesen des Nutzers
	 * @return Nutzer
	 */
	
	public Nutzer getNutzer() throws IllegalArgumentException;
	
	
}
