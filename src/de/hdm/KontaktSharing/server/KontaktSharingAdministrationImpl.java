package de.hdm.KontaktSharing.server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;

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
	
	/**
	 * Referenz auf den ListenstrukturMapper, der Listenstrukturobjekte mit der
	 * Datenbank abgleicht.
	 */
	
	private ListenstrukturMapper listenstrukturMapper = null;
	
	private int currentUserId = 1;

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
		this.listenstrukturMapper = ListenstrukturMapper.listenstrukturMapper();

	}

	public Nutzer createNutzer(String mail) throws IllegalArgumentException, SQLException {

		Nutzer n = new Nutzer();
		n.setEmail(mail);
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
			throws IllegalArgumentException, SQLException {

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
	 * @throws SQLException 
	 */

	public Eigenschaftauspraegung getEigenschaftauspraegungById(int id) throws IllegalArgumentException, SQLException {
		return this.eigenschaftauspraegungMapper.findByKey(id);
	}

	/**
	 * Auslesen aller Eigenschaftauspraegungen
	 * @throws SQLException 
	 */
	
	public Vector<Eigenschaftauspraegung> getAllEigenschaftauspraegung() throws IllegalArgumentException, SQLException {
		return this.eigenschaftauspraegungMapper.findAll();

	}

	/**
	 * Speichern einer Eigenschaftauspraegung
	 * @throws SQLException 
	 */

	public void save(Eigenschaftauspraegung ea) throws IllegalArgumentException, SQLException {
		eigenschaftauspraegungMapper.update(ea);
	}

	/**
	 * Löschen einer Eigenschaftauspraegung
	 * @throws SQLException 
	 */

	public void delete(Eigenschaftauspraegung ea) throws IllegalArgumentException, SQLException {

		this.eigenschaftauspraegungMapper.delete(ea);

	}

	public Eigenschaft createEigenschaft(String bezeichnung, String typ) throws IllegalArgumentException, SQLException {

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
	 * @throws SQLException 
	 */

	public Eigenschaft getEigenschaftById(int id) throws IllegalArgumentException, SQLException {
		return this.eigenschaftMapper.findByKey(id);
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

	public Kontaktliste createKontaktliste(String kontaktlistenname) throws IllegalArgumentException, Exception {

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

	public Kontaktliste getKontaktlisteById(int id) throws IllegalArgumentException, Exception {
		return this.kontaktlisteMapper.findByKey(id);
	}

	/**
	 * Auslesen aller Kontaktlisten
	 * @throws Exception 
	 */

	public Vector<Kontaktliste> getAllKontaktliste() throws IllegalArgumentException, Exception {
		return this.kontaktlisteMapper.findAll();

	}

	/**
	 * Speichern einer Kontaktliste
	 */

	public void save(Kontaktliste kl) throws IllegalArgumentException, Exception {
		kontaktlisteMapper.update(kl);
	}

	/**
	 * Löschen einer Kontaktliste
	 */

	public void delete(Kontaktliste kl) throws IllegalArgumentException, Exception {

		this.listenstrukturMapper.delete(kl);
		this.kontaktlisteMapper.delete(kl);

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
	public void delete(Kontakt kontakt) throws IllegalArgumentException, SQLException {
		this.listenstrukturMapper.deleteByKontaktId(kontakt.getId());
		this.eigenschaftauspraegungMapper.deleteByKontaktId(kontakt);
		this.kontaktMapper.delete(kontakt);
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
	public Vector<Kontakt> getAllKontaktByNutzer(int id) throws SQLException {
		return this.kontaktMapper.findAllByNutzerId(id);
	}

	@Override
	public Nutzer getNutzerByEmail(String mail) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kontakt createKontakt(Kontakt k) throws IllegalArgumentException, SQLException {
		return kontaktMapper.insert(k);
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
	public Eigenschaftauspraegung createEigenschaftauspraegung(Eigenschaftauspraegung e) throws IllegalArgumentException, SQLException {
		return this.eigenschaftauspraegungMapper.insert(e);
	}

	@Override
	public void update(Eigenschaftauspraegung ea) throws IllegalArgumentException, Exception {
		this.eigenschaftauspraegungMapper.update(ea);		
	}

	@Override
	public List<Kontakt> getKontaktOf(Kontaktliste kl) throws IllegalArgumentException, Exception {
		return this.kontaktMapper.findAllByKontaktlistId(kl.getId());
	}

	@Override
	public List<Eigenschaftauspraegung> getEigenschaftOf(Kontakt k) throws IllegalArgumentException, SQLException {
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

	@Override
	public Vector<Eigenschaft> getAllEigenschaft() throws IllegalArgumentException, Exception {
		return this.eigenschaftMapper.findAll();
	}

	@Override
	public Vector<Eigenschaftauspraegung> getAllEigenschaftauspraegungByKontakt(Kontakt kontakt)
			throws IllegalArgumentException, Exception {
		return this.eigenschaftauspraegungMapper.findAllByIdKontakt(kontakt.getId());
	}

	@Override
	public void createEigenschaftauspraegungen(Vector<Eigenschaftauspraegung> auspraegungen)
			throws IllegalArgumentException, Exception {
		for(Eigenschaftauspraegung ea: auspraegungen) {
			try {
				this.eigenschaftauspraegungMapper.insert(ea);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public Vector<Kontaktliste> getAllKontaktlisten() throws IllegalArgumentException, Exception {
		return this.kontaktlisteMapper.findAll();
	}

	@Override
	public Vector<Kontakt> getAllKontaktWithNameByNutzer(int id) throws IllegalArgumentException, Exception {
		Vector<Kontakt> kontakte = this.getAllKontaktByNutzer(id);
		
		for(Kontakt kontakt: kontakte) {
			try {
				kontakt.setEigenschaftauspraegung(this.getAllEigenschaftauspraegungByKontakt(kontakt));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Collections.sort(kontakte,new Kontakt());
		return kontakte;
	}

	@Override
	public void createKontaktlisteForNutzer(int idNutzer, String name, List<Integer> idsKontakte)
			throws IllegalArgumentException, Exception {
		Kontaktliste kl = this.kontaktlisteMapper.insert(name, idNutzer);
		for(Integer id: idsKontakte) {
			try {
				listenstrukturMapper.insert(kl.getId(), id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public Vector<Kontaktliste>  getAllKontaktlistenWithUserCountForNutzer(int idNutzer) throws IllegalArgumentException, Exception {
		Vector<Kontaktliste> listen = this.kontaktlisteMapper.findAllByNutzer(idNutzer);
		for(Kontaktliste liste: listen) {
			List<Kontakt> kontakte;
			try {
				kontakte = this.getKontaktOf(liste);
				liste.setKontakte(kontakte);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listen;
	}

	@Override
	public Kontaktliste getKontaktlistenWithUserinformationForNutzer(int idNutzer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateKontaktliste(int id, String name, List<Integer> idsKontakte)
			throws IllegalArgumentException, Exception {
		this.kontaktlisteMapper.updateName(id, name);
		this.listenstrukturMapper.delete(id);
		for(Integer idKontakt: idsKontakte) {
			try {
				this.listenstrukturMapper.insert(id, idKontakt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setCurrentNutzerId(int id) {
		this.currentUserId = id;
	}

	public Nutzer checkNutzer(String email) {
		// TODO Auto-generated method stub
		return null;
		}

	@Override
	public Nutzer getNutzerByMailOrCreate(String email) throws Exception {
		Nutzer nutzer = this.nutzerMapper.findByMail(email);
		if(nutzer != null) {
			return nutzer;
		} else {
			Nutzer newNutzer = new Nutzer();
			newNutzer.setEmail(email);
			this.nutzerMapper.insert(newNutzer).getId();
			
			return this.nutzerMapper.findByMail(email);
		}
	}

}



