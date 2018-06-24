package de.hdm.KontaktSharing.server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.KontaktSharing.shared.KontaktSharingAdministration;
import de.hdm.KontaktSharing.server.db.*;
import de.hdm.KontaktSharing.shared.bo.*;

@SuppressWarnings("serial")
public class KontaktSharingAdministrationImpl extends RemoteServiceServlet implements KontaktSharingAdministration {

	/**
	 * Referenz auf das zugehörige Nutzer-Objekt.
	 */

	private Nutzer getEigenschaftOfnutzer = null;

	/**
	 * Referenz auf den NutzerMapper, der Nutzerobjekte mit der Datenbank abgleicht.
	 */

	private NutzerMapper nutzerMapper = null;

	/**
	 * Referenz auf den EigenschaftauspraegungMapper, der
	 * Eigenschaftauspraegungsobjekte mit der Datenbank abgleicht.
	 */

	private EigenschaftauspraegungMapper eigenschaftauspraegungMapper = null;

	/**
	 * Referenz auf den EigenschaftMapper, der Eigenschaftobjekte mit der Datenbank
	 * abgleicht.
	 */

	private EigenschaftMapper eigenschaftMapper = null;

	/**
	 * Referenz auf den KontaktlisteMapper, der Kontaktlisteobjekte mit der
	 * Datenbank abgleicht.
	 */

	private KontaktlisteMapper kontaktlisteMapper = null;

	/**
	 * Referenz auf den KontaktMapper, der Kontaktobjekte mit der Datenbank
	 * abgleicht.
	 */

	private KontaktMapper kontaktMapper = null;

	/**
	 * Referenz auf den TeilhaberschaftMapper, der Teilhaberschaftobjekte mit der
	 * Datenbank abgleicht.
	 */

	private TeilhaberschaftMapper teilhaberschaftMapper = null;

	public KontaktSharingAdministrationImpl() throws IllegalArgumentException {

		/**
		 * Es ist ein No-Argument-Konstruktor
		 */

	}

	@Override
	public void init() throws IllegalArgumentException {

		/**
		 * Die KontaktsharingAdministration muss einen vollständigen Satz von Mappern
		 * besitzen, mit deren Hilfe sie mit der Datenbank kommunizieren kann.
		 */

		this.nutzerMapper = NutzerMapper.nutzerMapper();
		this.eigenschaftauspraegungMapper = EigenschaftauspraegungMapper.eigenschaftauspraegungMapper();
		this.eigenschaftMapper = EigenschaftMapper.eigenschaftMapper();
		this.kontaktlisteMapper = KontaktlisteMapper.kontaktlisteMapper();
		this.kontaktMapper = KontaktMapper.kontaktMapper();
		this.teilhaberschaftMapper = TeilhaberschaftMapper.teilhaberschaftMapper();

	}

	public Nutzer createNutzer(String email) throws IllegalArgumentException, SQLException {

		Nutzer n = new Nutzer();
		n.setEmail(email);
		return this.nutzerMapper.insert(n);

	}

	/**
	 * Auslesen eines Nutzers anhand seiner ID
	 * 
	 * @throws SQLException
	 */

	public Nutzer getNutzerById(int id) throws IllegalArgumentException, SQLException {
		return this.nutzerMapper.findByKey(id);
	}

	/**
	 * Auslesen aller Nutzer
	 * 
	 * @throws SQLException
	 */

	public Vector<Nutzer> getAllNutzer() throws IllegalArgumentException, SQLException {
		return this.nutzerMapper.findAll();

	}

	/**
	 * Speichern eines Nutzers
	 * 
	 * @throws SQLException
	 */

	public void save(Nutzer n) throws IllegalArgumentException, SQLException {
		nutzerMapper.update(n);
	}

	/**
	 * Löschen eines Nutzers
	 * 
	 * @throws SQLException
	 */

	public void delete(Nutzer n) throws IllegalArgumentException, SQLException {

		this.nutzerMapper.delete(n);

	}

	public Eigenschaftauspraegung createEigenschaftauspraegung(String text, int zahl, Date datum)
			throws IllegalArgumentException {

		Eigenschaftauspraegung ea = new Eigenschaftauspraegung();
		ea.setText(text);
		ea.setZahl(zahl);
		ea.setDatum(datum);

		/**
		 * Setzen eines vorläufigen Eigenschaftauspraegung-ID.
		 */

		ea.setId(1);

		// Objekt in der DB speichern.

		return this.eigenschaftauspraegungMapper.insert(ea);

	}

	/**
	 * Auslesen einer Eigenschaftauspraegung anhand seiner ID
	 */

	public Eigenschaftauspraegung getEigenschaftauspraegungById(int id) throws IllegalArgumentException {
		return this.eigenschaftauspraegungMapper.findByKey(id);
	}

	/**
	 * Auslesen aller Eigenschaftauspraegungen
	 */

	public Vector<Eigenschaftauspraegung> getAllEigenschaftauspraegung() throws IllegalArgumentException {
//		return this.eigenschaftauspraegungMapper.findAll();
		return null;
	}

	/**
	 * Speichern einer Eigenschaftauspraegung
	 */

	public void save(Eigenschaftauspraegung ea) throws IllegalArgumentException {
		eigenschaftauspraegungMapper.update(ea);
	}

	/**
	 * Löschen einer Eigenschaftauspraegung
	 */

	public void delete(Eigenschaftauspraegung ea) throws IllegalArgumentException {

		this.eigenschaftauspraegungMapper.delete(ea);

	}

	public Eigenschaft createEigenschaft(String bezeichnung, String typ) throws IllegalArgumentException {

		Eigenschaft e = new Eigenschaft();
		e.setBezeichnung(bezeichnung);
		e.setTyp(typ);

		/**
		 * Setzen eines vorläufigen Eigenschaft-ID.
		 */

		e.setId(1);

		// Objekt in der DB speichern.

		return this.eigenschaftMapper.insert(e);

	}

	/**
	 * Auslesen einer Eigenschaft anhand seiner ID
	 */

	public Eigenschaft getEigenschaftById(int id) throws IllegalArgumentException {
		return this.eigenschaftMapper.findByKey(id);
	}

	/**
	 * Auslesen aller Eigenschaften
	 */

	public Vector<Eigenschaft> getAllEigenschaft() throws IllegalArgumentException {
		return this.eigenschaftMapper.findAll();

	}

	/**
	 * Speichern einer Eigenschaft
	 */

	public void save(Eigenschaft e) throws IllegalArgumentException {
		eigenschaftMapper.update(e);
	}

	/**
	 * Löschen einer Eigenschaft
	 */

	public void delete(Eigenschaft e) throws IllegalArgumentException {

		this.eigenschaftMapper.delete(e);

	}

	public Kontaktliste createKontaktliste(String kontaktlistenname) throws IllegalArgumentException {

		Kontaktliste kl = new Kontaktliste();
		kl.setKontaktlistenname(kontaktlistenname);

		/**
		 * Setzen eines vorläufigen Kontaktliste-ID.
		 */

		kl.setId(1);

		// Objekt in der DB speichern.

		return this.kontaktlisteMapper.insert(kl);

	}

	/**
	 * Auslesen einer Kontaktliste anhand seiner ID
	 */

	public Kontaktliste getKontaktlisteById(int id) throws IllegalArgumentException {
		return this.kontaktlisteMapper.findByKey(id);
	}

	/**
	 * Auslesen aller Kontaktlisten
	 */

	public Vector<Kontaktliste> getAllKontaktliste() throws IllegalArgumentException {
		return this.kontaktlisteMapper.findAll();

	}

	/**
	 * Speichern einer Kontaktliste
	 */

	public void save(Kontaktliste kl) throws IllegalArgumentException {
		kontaktlisteMapper.update(kl);
	}

	/**
	 * Löschen einer Kontaktliste
	 */

	public void delete(Kontaktliste kl) throws IllegalArgumentException {

		this.kontaktlisteMapper.delete(kl);

	}

	public Kontakt createKontakt(String name, Date erzeugungsdatum, Date modifikationsdatum)
			throws IllegalArgumentException, SQLException {

		Kontakt k = new Kontakt();
		k.setErzeugungsdatum(erzeugungsdatum);
		k.setModifikationsdatum(modifikationsdatum);

		/**
		 * Setzen eines vorläufigen Kontakt-ID.
		 */

		k.setId(1);

		// Objekt in der DB speichern.

		return this.kontaktMapper.insert(k);

	}

	/**
	 * Auslesen eines Kontakts anhand seiner ID
	 * 
	 * @throws SQLException
	 */

	public Kontakt getKontaktById(int id) throws IllegalArgumentException, SQLException {
		return this.kontaktMapper.findByKey(id);
	}

	/**
	 * Auslesen aller Kontakte
	 * 
	 * @throws SQLException
	 */

	public Vector<Kontakt> getAllKontakt() throws IllegalArgumentException, SQLException {
		return this.kontaktMapper.findAll();

	}

	/**
	 * Auslesen aller Kontakte
	 * 
	 * @throws SQLException
	 */

	public Vector<Kontakt> getAllKontaktForNutzer() throws IllegalArgumentException, SQLException {
		return this.kontaktMapper.findAll();

	}

	/**
	 * Speichern eines Kontakts
	 * 
	 * @throws SQLException
	 */

	public void save(Kontakt k) throws IllegalArgumentException, SQLException {
		kontaktMapper.update(k);
	}

	/**
	 * Löschen eines Kontakts
	 * 
	 * @throws SQLException
	 */

	@Override
	public void delete(Kontakt k) throws IllegalArgumentException, SQLException {

		this.kontaktMapper.delete(k);

	}

	public Teilhaberschaft createTeilhaberschaft() throws IllegalArgumentException {

		Teilhaberschaft t = new Teilhaberschaft();

		/**
		 * Setzen eines vorläufigen Teilhaberschaft-ID.
		 */

		t.setId(1);

		// Objekt in der DB speichern.

		return this.teilhaberschaftMapper.insert(t);

	}

	/**
	 * Auslesen einer Teilhaberschaft anhand seiner ID
	 */

	public Teilhaberschaft getTeilhaberschaftById(int id) throws IllegalArgumentException {
		return this.teilhaberschaftMapper.findByKey(id);
	}

	/**
	 * Auslesen aller Teilhaberschaft
	 */

	public Vector<Teilhaberschaft> getAllTeilhaberschaft() throws IllegalArgumentException {
		return this.teilhaberschaftMapper.findAll();

	}

	/**
	 * Speichern einer Teilhaberschaft
	 */

	public void save(Teilhaberschaft t) throws IllegalArgumentException {
		teilhaberschaftMapper.update(t);
	}

	/**
	 * Löschen einer Teilhaberschaft
	 */

	public void delete(Teilhaberschaft t) throws IllegalArgumentException {

		this.teilhaberschaftMapper.delete(t);

	}

	@Override
	public void setNutzer(Nutzer n) {
//		this.nutzer = n;
	}

	@Override
	public Vector<Kontakt> getAllKontaktByLoggedInNutzer() throws SQLException {
		return this.kontaktMapper.findAllByNutzerId(1);
	}
	
	private Integer getLoggedInNutzerId() {
		return this.getLoggedInNutzer().map(nutzer -> nutzer.getId()).orElse(new Integer(0));
	}

	private Optional<Nutzer> getLoggedInNutzer() {
		HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
		HttpSession session = httpServletRequest.getSession();
		Object userObj = session.getAttribute("user");
		if (userObj != null && userObj instanceof Nutzer) {
			return Optional.of((Nutzer) userObj);
		}
		return Optional.empty();
	}

	@Override
	public Nutzer getNutzerByEmail(String email) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kontakt createKontakt(Nutzer n) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Kontakt k) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		try {
			kontaktMapper.update(k);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Kontaktliste createKontaktliste(Nutzer n) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Kontaktliste kl) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Eigenschaft createEigenschaft(Kontakt k) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Eigenschaft e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Eigenschaftauspraegung createEigenschaftauspraegung(Eigenschaft e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Eigenschaftauspraegung ea) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Kontakt> getKontaktOf(Kontaktliste kl) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Eigenschaftauspraegung> getEigenschaftOf(Kontakt k) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return this.eigenschaftauspraegungMapper.findAll();
	}

	@Override
	public ArrayList<Eigenschaftauspraegung> getEigenschaftauspraegungOf(Eigenschaftauspraegung e)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Nutzer getNutzer() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
