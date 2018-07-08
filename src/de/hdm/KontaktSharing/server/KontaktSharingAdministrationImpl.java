package de.hdm.KontaktSharing.server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
	private TeilhaberschaftNutzerMapper teilhaberschaftNutzerMapper = null;
	private TeilbaresObjektMapper teilbaresObjektMapper = null;
	
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
		this.teilhaberschaftMapper = TeilhaberschaftMapper.teilhaberschaftMapper();
		this.teilhaberschaftNutzerMapper = TeilhaberschaftNutzerMapper.teilhaberschaftNutzerMapper();
		this.teilbaresObjektMapper = TeilbaresObjektMapper.teilbaresObjektMapper();

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
	 * Loeschen eines Nutzers
	 * 
	 * @throws SQLException
	 */

	public void delete(Nutzer n) throws IllegalArgumentException, SQLException {

		this.nutzerMapper.delete(n);

	}
	/**
	 * Eigenschaftauspraegung anlegen
	 * @param text
	 * @param zahl
	 * @param datum
	 * @return eigenschaftauspraegungMapper
	 * @throws IllegalArgumentException
	 * @throws SQLException
	 */
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
	 * Loeschen einer Eigenschaftauspraegung
	 * @throws SQLException 
	 */

	public void delete(Eigenschaftauspraegung ea) throws IllegalArgumentException, SQLException {
		this.teilbaresObjektMapper.deleteForEigenschaftsauspraegung(ea.getId());
		this.eigenschaftauspraegungMapper.delete(ea);

	}
	/**
	 * Eigenschaft anlegen
	 * @param bezeichnung
	 * @param typ
	 * @return eigenschaftMapper
	 * @throws IllegalArgumentException
	 * @throws SQLException
	 */
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
	 * 
	 * @param e
	 * @throws IllegalArgumentException
	 */

	public void save(Eigenschaft e) throws IllegalArgumentException {
		eigenschaftMapper.update(e);
	}

	/**
	 *Loeschen einer Eigenschaft
	 * 
	 */

	public void delete(Eigenschaft e) throws IllegalArgumentException {

		this.eigenschaftMapper.delete(e);

	}
	/**
	 * Kontaktliste einfuegen
	 * @param kontaktlistenname
	 * @return kontaktlisteMapper
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public Kontaktliste createKontaktliste(String kontaktlistenname) throws IllegalArgumentException, Exception {

		Kontaktliste kl = new Kontaktliste();
		kl.setKontaktlistenname(kontaktlistenname);

		/**
		 * Setzen eines vorlaeufigen Kontaktliste-ID.
		 */

		kl.setId(1);

		// Objekt in der DB speichern.

		return this.kontaktlisteMapper.insert(kl);

	}

	/**
	 * Auslesen einer Kontaktliste anhand seiner ID
	 * 
	 * @param id
	 * @return kontaktlisteMapper
	 * @throws IllegalArgumentException
	 * @throws Exception
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
	 * 
	 * @param kl
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public void save(Kontaktliste kl) throws IllegalArgumentException, Exception {
		kontaktlisteMapper.update(kl);
	}

	/**
	 * Loeschen einer Kontaktliste
	 * 
	 */

	public void delete(Kontaktliste kl) throws IllegalArgumentException, Exception {

		this.teilbaresObjektMapper.deleteForKontaktliste(kl.getId());
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
	 * @throws Exception 
	 */

	@Override
	public void delete(Kontakt kontakt) throws Exception {
		this.deleteTeilhaberschaftForKontakt(kontakt);
		this.listenstrukturMapper.deleteByKontaktId(kontakt.getId());
		this.eigenschaftauspraegungMapper.deleteByKontaktId(kontakt);
		this.kontaktMapper.delete(kontakt);
	}

	private void deleteTeilhaberschaftForKontakt(Kontakt kontakt) throws Exception {
		TeilbaresObjekt to = this.teilbaresObjektMapper.findByKontakt(kontakt);
		if(to != null) { 
			int id = to.getIdTeilhaberschaft();
			this.teilbaresObjektMapper.deleteForTeilhaberschaft(id);
			this.teilhaberschaftNutzerMapper.deleteByTeilhaberschaft(id);
			this.teilhaberschaftMapper.delete(id);
		}
	}
	/**
	 * 
	 * @return teilhaberschaftMapper
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public Teilhaberschaft createTeilhaberschaft() throws IllegalArgumentException, Exception {

		Teilhaberschaft t = new Teilhaberschaft();

		/**
		 * Setzen eines vorlaeufigen Teilhaberschaft-ID.
		 */

		t.setId(1);

		// Objekt in der DB speichern.

		return this.teilhaberschaftMapper.insert(t);

	}

	/**
	 * Auslesen einer Teilhaberschaft anhand seiner ID
	 * 
	 * @param id
	 * @return teilhaberschaftMapper
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */

	public Teilhaberschaft getTeilhaberschaftById(int id) throws IllegalArgumentException, Exception {
		return this.teilhaberschaftMapper.findByKey(id);
	}

	/**
	 * Auslesen aller Teilhaberschaft
	 * 
	 * @return teilhaberschaftMapper
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */

	public Vector<Teilhaberschaft> getAllTeilhaberschaft() throws IllegalArgumentException, Exception {
		return this.teilhaberschaftMapper.findAll();

	}

	/**
	 * Speichern einer Teilhaberschaft
	 * 
	 * @param t
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */

	public void save(Teilhaberschaft t) throws IllegalArgumentException, Exception {
		teilhaberschaftMapper.update(t);
	}

	/**
	 * Loeschen einer Teilhaberschaft
	 */

	public void delete(Teilhaberschaft t) throws IllegalArgumentException, Exception {
		this.teilbaresObjektMapper.deleteForTeilhaberschaft(t.getId());
		this.teilhaberschaftNutzerMapper.deleteByTeilhaberschaft(t.getId());
		this.teilhaberschaftMapper.delete(t.getId());

	}

	
	@Override
	public void setNutzer(Nutzer n) {
//		this.nutzer = n;
	}

	/**
	 * Alle Kontakte des Nutzers auslesen
	 */
	@Override
	public Vector<Kontakt> getAllKontaktByNutzer(int id) throws IllegalArgumentException, Exception {
		Vector<Kontakt> kontakte = this.kontaktMapper.findAllByNutzerId(id);
		for(Kontakt kontakt: kontakte) {
			kontakt.setEigenschaftauspraegung(this.getAllEigenschaftauspraegungByKontakt(kontakt));
			TeilbaresObjekt to = this.teilbaresObjektMapper.findByKontakt(kontakt);
			if(to != null) {
				kontakt.setTeilhaberschaftId(to.getIdTeilhaberschaft());
			}
		}
		return kontakte;
	}
	
	/**
	 * Nutzer anhand der Email auslesen
	 */
	@Override
	public Nutzer getNutzerByEmail(String mail) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Kontakt anlegen
	 */
	@Override
	public Kontakt createKontakt(Kontakt k) throws IllegalArgumentException, Exception {
		return kontaktMapper.insert(k);
	}

	/**
	 * Kontakt aktualisieren
	 */
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

	/**
	 * Kontaktliste anlegen
	 */
	@Override
	public Kontaktliste createKontaktliste(Nutzer n) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Kontaktliste aktualisieren
	 */
	@Override
	public void update(Kontaktliste kl) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Eigenschaft anlegen
	 */
	@Override
	public Eigenschaft createEigenschaft(Kontakt k) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Eigenschaft aktualisieren
	 */
	@Override
	public void update(Eigenschaft e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Eigenschaftauspraegung anlegen
	 */
	@Override
	public Eigenschaftauspraegung createEigenschaftauspraegung(Eigenschaftauspraegung e) throws IllegalArgumentException, SQLException {
		return this.eigenschaftauspraegungMapper.insert(e);
	}
	
	/**
	 * Eigenschaftauspraegung aktualisieren
	 */
	@Override
	public void update(Eigenschaftauspraegung ea) throws IllegalArgumentException, Exception {
		this.eigenschaftauspraegungMapper.update(ea);		
	}
	/**
	 * alle Kontakte von der Kontaktliste auslesen
	 */
	@Override
	public List<Kontakt> getKontaktOf(Kontaktliste kl) throws IllegalArgumentException, Exception {
		return this.kontaktMapper.findAllByKontaktlistId(kl.getId());
	}
	/**
	 * Eigenschaft vom Kontakt auslesen (mit der Eigenschaftauspraegung)
	 */
	@Override
	public List<Eigenschaftauspraegung> getEigenschaftOf(Kontakt k) throws IllegalArgumentException, SQLException {
		// TODO Auto-generated method stub
		return this.eigenschaftauspraegungMapper.findAll();
	}
	/**
	 * Eigenschaftauspraegung von Eigenschaft auslesen
	 */
	@Override
	public ArrayList<Eigenschaftauspraegung> getEigenschaftauspraegungOf(Eigenschaftauspraegung e)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Nutzer auslesen
	 */
	@Override
	public Nutzer getNutzer() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * alle Eigenschaften auslesen
	 */
	@Override
	public Vector<Eigenschaft> getAllEigenschaft() throws IllegalArgumentException, Exception {
		return this.eigenschaftMapper.findAll();
	}
	/**
	 * Eigenschaftauspraegung von Kontakt auslesen
	 */
	@Override
	public Vector<Eigenschaftauspraegung> getAllEigenschaftauspraegungByKontakt(Kontakt kontakt)
			throws IllegalArgumentException, Exception {
		return this.eigenschaftauspraegungMapper.findAllByIdKontakt(kontakt.getId());
	}
	/**
	 * Eigenschaftauspraegung anlegen
	 */
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
	/**
	 * alle Kontaktlisten auslesen
	 */
	@Override
	public Vector<Kontaktliste> getAllKontaktlisten() throws IllegalArgumentException, Exception {
		return this.kontaktlisteMapper.findAll();
	}
	/**
	 * Alle Kontakte mit Namen auslesen
	 */
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
	/**
	 * Kontaktliste anlegen f�r Nutzer
	 */
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
	/**
	 * Alle Kontaktlsten mit User f�r Nutzer auslesen
	 */
	@Override
	public Vector<Kontaktliste>  getAllKontaktlistenWithUserCountForNutzer(int idNutzer) throws IllegalArgumentException, Exception {
		Vector<Kontaktliste> listen = this.kontaktlisteMapper.findAllByNutzer(idNutzer);
		for(Kontaktliste liste: listen) {
			List<Kontakt> kontakte;
			kontakte = this.getKontaktOf(liste);
			liste.setKontakte(kontakte);
			TeilbaresObjekt to = this.teilbaresObjektMapper.findFromListe(liste.getId());
			if(to != null) {
				liste.setTeilhaberschaftId(to.getIdTeilhaberschaft());
			}
		}
		return listen;
	}
	/**
	 * Kontaktliste mit User information auslesen
	 */
	@Override
	public Kontaktliste getKontaktlisteWithUserinformation(int idKontaktliste) throws Exception {
		Kontaktliste liste = this.kontaktlisteMapper.findByKey(idKontaktliste);
		
		Vector<Kontakt> kontakte = this.kontaktMapper.findAllByKontaktlistId(idKontaktliste);
		for(Kontakt kontakt: kontakte) {
			Vector<Eigenschaftauspraegung> auspraegung = this.eigenschaftauspraegungMapper.findAllByIdKontakt(kontakt.getId());
			kontakt.setEigenschaftauspraegung(auspraegung);
		}
		liste.setKontakte(kontakte);
		
		return liste;
	}
	/**
	 * Kontaktliste aktualisieren
	 */
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
	/**
	 * aktuellen Nutzer setzen
	 */
	@Override
	public void setCurrentNutzerId(int id) {
		this.currentUserId = id;
	}
	/**
	 * aktuellen Nutzer ueberpruefen
	 */
	public Nutzer checkNutzer(String email) {
		// TODO Auto-generated method stub
		return null;
		}
	/**
	 * Nutzer anhand der mail auslesen oder anlegen
	 */
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
	/**
	 * Liste teilen
	 */
	@Override
	public void shareListe(int idNutzer,int idListe, List<String> mails) throws Exception {
		Teilhaberschaft th = new Teilhaberschaft();
		th.setIdNutzer(idNutzer);
		th = this.teilhaberschaftMapper.insert(th);
		
		this.teilbaresObjektMapper.insertKontaktliste(idListe, th.getId());
		
		for(String mail: mails) {
			Nutzer nutzer = this.nutzerMapper.findByMail(mail);
			TeilhaberschaftNutzer thn = new TeilhaberschaftNutzer();
			thn.setIdNutzer(nutzer.getId());
			thn.setIdTeilhaberschaft(th.getId());
			this.teilhaberschaftNutzerMapper.insert(thn);
		}
	}
	/**
	 * ausgabe der geteilten Kontaktlisten f�r den User
	 */
	@Override
	public Vector<TeilhaberschaftKontaktliste> getSharedKontaktlistenForUser(int idNutzer) throws Exception {
		Vector<TeilhaberschaftKontaktliste> thkls = new Vector<TeilhaberschaftKontaktliste>();
		
		Vector<TeilhaberschaftNutzer> thns = this.teilhaberschaftNutzerMapper.findAllByNutzer(idNutzer);
		for(TeilhaberschaftNutzer thn: thns) {
			Vector<TeilbaresObjekt> tos = this.teilbaresObjektMapper.findAllKontaktlisteByTeilhaberschaft(thn.getIdTeilhaberschaft());
			Vector<Teilhaberschaft> ths = getTeilhaberschaftenFromTeilbaresObject(tos);
			for(Teilhaberschaft th: ths) {
				TeilhaberschaftKontaktliste thkl = new TeilhaberschaftKontaktliste(th);
				Kontaktliste liste = filterKontaktlisteWithUserinformationByTeilhaberschaft(th.getId(), tos);
				thkl.setListe(liste);
				thkls.add(thkl);
			}
		}
		return thkls;
	}
	/**
	 * Teilhaberschaft vom teilbarenobjekt ausgeben
	 * @param tos
	 * @return ths
	 * @throws Exception
	 */
	private Vector<Teilhaberschaft> getTeilhaberschaftenFromTeilbaresObject(Vector<TeilbaresObjekt> tos) throws Exception {
		Vector<Teilhaberschaft> ths = new Vector<Teilhaberschaft>();
		for(TeilbaresObjekt to: tos) {
			int idTeilhaberschaft = to.getIdTeilhaberschaft();
			Teilhaberschaft th = this.teilhaberschaftMapper.findByKey(idTeilhaberschaft);
			ths.add(th);
		}
		
		return ths;
	}
	/**
	 * Kontaktliste mit User Inforation anhand der teilhaberschaft auslesen
	 * @param idTeilhaberschaft
	 * @param tos
	 * @return null
	 * @throws Exception
	 */
	private Kontaktliste filterKontaktlisteWithUserinformationByTeilhaberschaft(int idTeilhaberschaft, Vector<TeilbaresObjekt> tos) throws Exception {
		for(TeilbaresObjekt to: tos) {
			if(to.getIdTeilhaberschaft() == idTeilhaberschaft) {
				return this.getKontaktlisteWithUserinformation(to.getIdKontaktliste());
			}
		}
		return null;
	}
	/**
	 * Alle aktuellen Nutzer auslesen
	 */
	@Override
	public Vector<Nutzer> getAllNutzerWithoutCurrent(int idNutzer) throws Exception {
		Vector<Nutzer> allNutzer = new Vector<Nutzer>();
		for(Nutzer nutzer: this.nutzerMapper.findAll()) {
			if(nutzer.getId() != idNutzer) {
				allNutzer.add(nutzer);
			}
		}
		return allNutzer;
	}
	/**
	 * Alle Nutzer die sich in einer Teilhabeschaft befinden auslesen
	 */
	@Override
	public Vector<Nutzer> getAllNutzerInTeilhaberschaft(int idTeilhaberschaft) throws Exception {
		Vector<Nutzer> allNutzer = new Vector<Nutzer>();
		Vector<TeilhaberschaftNutzer> thns = this.teilhaberschaftNutzerMapper.findAllTeilhaberschaft(idTeilhaberschaft);
		for(TeilhaberschaftNutzer thn: thns) {
			Nutzer nutzer = this.getNutzerById(thn.getIdNutzer());
			allNutzer.add(nutzer);
		}
		return allNutzer;
	}
	/**
	 * Teilhaberschaft auslesen
	 */
	@Override
	public void deleteTeilhaberschaft(int idTeilhaberschaft) throws Exception {
		this.teilbaresObjektMapper.deleteForTeilhaberschaft(idTeilhaberschaft);
		this.teilhaberschaftNutzerMapper.deleteByTeilhaberschaft(idTeilhaberschaft);
		this.teilhaberschaftMapper.delete(idTeilhaberschaft);
	}
	/**
	 * Teilhaberschaftliste aktualisieren
	 */
	@Override
	public void updateTeilhaberschaftListe(int idTeilhaberschaft, List<String> mails) throws Exception {
		this.teilhaberschaftNutzerMapper.deleteByTeilhaberschaft(idTeilhaberschaft);
		for(String mail: mails) {
			Nutzer nutzer = this.nutzerMapper.findByMail(mail);
			TeilhaberschaftNutzer thn = new TeilhaberschaftNutzer();
			thn.setIdNutzer(nutzer.getId());
			thn.setIdTeilhaberschaft(idTeilhaberschaft);
			this.teilhaberschaftNutzerMapper.insert(thn);
		}
	}

	@Override
	public void shareKontakt(int idNutzer, List<Integer> ids, List<String> mails) throws Exception {
		Teilhaberschaft th = new Teilhaberschaft();
		th.setIdNutzer(idNutzer);
		th = this.teilhaberschaftMapper.insert(th);
		
		for(Integer id: ids) {
			this.teilbaresObjektMapper.insertEigenschaftauspraegung(id, th.getId());
		}
		
		for(String mail: mails) {
			Nutzer nutzer = this.nutzerMapper.findByMail(mail);
			TeilhaberschaftNutzer thn = new TeilhaberschaftNutzer();
			thn.setIdNutzer(nutzer.getId());
			thn.setIdTeilhaberschaft(th.getId());
			this.teilhaberschaftNutzerMapper.insert(thn);
		}
	}

	@Override
	public Vector<TeilhaberschaftKontakt> getSharedKontakteForUser(int idNutzer) throws Exception {
		Map<Integer, TeilhaberschaftKontakt> thks = new HashMap<Integer, TeilhaberschaftKontakt>();
		
		Vector<TeilhaberschaftNutzer> thns = this.teilhaberschaftNutzerMapper.findAllByNutzer(idNutzer);
		for(TeilhaberschaftNutzer thn: thns) {
			Vector<TeilbaresObjekt> tos = this.teilbaresObjektMapper.findAllKontakteByTeilhaberschaft(thn.getIdTeilhaberschaft());
			Vector<Teilhaberschaft> ths = getTeilhaberschaftenFromTeilbaresObject(tos);
				
				for(TeilbaresObjekt to: tos) {
					Eigenschaftauspraegung ep = this.eigenschaftauspraegungMapper.findByKey(to.getIdEigenschaftsauspraegung());

					if(thks.get(ep.getIdKontakt()) == null) {
						Kontakt kontakt = this.kontaktMapper.findByKey(ep.getIdKontakt());
						TeilhaberschaftKontakt thkl = new TeilhaberschaftKontakt();
						thkl.setId(to.getIdTeilhaberschaft());
						thkl.setIdNutzer(idNutzer);
						thkl.setKontakt(kontakt);
						kontakt.setEigenschaftauspraegung(new Vector());
						kontakt.getEigenschaftauspraegung().add(ep);
						kontakt.setWasShared(true);
						thkl.setKontakt(kontakt);
						thks.put(ep.getIdKontakt(), thkl);	
					} else {
						thks.get(ep.getIdKontakt()).getKontakt().getEigenschaftauspraegung().add(ep);
					}
				}
		}
		return new Vector<TeilhaberschaftKontakt>(thks.values());
	}

	@Override
	public void deleteTeilhaberschaftForUser(int idNutzer, Kontakt kontakt) throws Exception {
		TeilbaresObjekt to = this.teilbaresObjektMapper.findByKontakt(kontakt);
		this.teilhaberschaftNutzerMapper.delete(idNutzer, to.getIdTeilhaberschaft());
		
		if(this.teilhaberschaftNutzerMapper.findAllTeilhaberschaft(to.getIdTeilhaberschaft()).size() == 0) {
			Teilhaberschaft th = new Teilhaberschaft();
			th.setId(to.getIdTeilhaberschaft());
			this.delete(th);
		}
	}

	@Override
	public void deleteTeilhaberschaftForUser(int idNutzer, Kontaktliste kontakt) throws Exception {
		TeilbaresObjekt to = this.teilbaresObjektMapper.findByKontakt(kontakt);
		this.teilhaberschaftNutzerMapper.delete(idNutzer, to.getIdTeilhaberschaft());
		
		if(this.teilhaberschaftNutzerMapper.findAllTeilhaberschaft(to.getIdTeilhaberschaft()).size() == 0) {
			Teilhaberschaft th = new Teilhaberschaft();
			th.setId(to.getIdTeilhaberschaft());
			this.delete(th);
		}
		
	}
	

}



