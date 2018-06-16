package de.hdm.KontaktSharing.server.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import de.hdm.KontaktSharing.shared.bo.Kontakt;

public class KontaktMapper extends CommonMapper<Kontakt> {

	/**
	 * Die Klasse KontaktMapper wird nur einmal instantiiert. Man spricht hierbei
	 * von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @see kontaktMapper()
	 */

	private static KontaktMapper kontaktMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	 * neue Instanzen dieser Klasse zu erzeugen.
	 */

	protected KontaktMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>KontaktMapper.kontaktMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
	 * Instanz von <code>KontaktMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> KontaktMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
	 * 
	 * @return DAS <code>KontaktMapper</code>-Objekt.
	 * @see kontaktMapper
	 */

	public static KontaktMapper kontaktMapper() {
		if (kontaktMapper == null) {
			kontaktMapper = new KontaktMapper();
		}

		return kontaktMapper;
	}

	/**
	 * Suchen eines Kontakts mit vorgegebener Kontaktid. Da diese eindeutig ist,
	 * wird genau ein Objekt zur�ckgegeben.
	 * 
	 * @param id
	 *            Primärschlüsselattribut (->DB)
	 * @return Kontakt-Objekt, das dem übergebenen Schlüssel entspricht, null bei
	 *         nicht vorhandenem DB-Tupel.
	 * @throws SQLException 
	 */

	public Kontakt findByKey(int id) throws SQLException {
		return this.findObject("SELECT idKontakt, erzeugungsdatum, modifikationsdatum, nutzer_idNutzer FROM kontakt "
					+ "WHERE idKontakt=" + id);
	}

	/**
	 * Auslesen aller Kontakte.
	 *
	 * @return Ein Vektor mit Kontakt-Objekten, die sämtliche Kontakte
	 *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
	 *         oder ggf. auch leerer Vetor zurückgeliefert.
	 * @throws SQLException 
	 */
	public Vector<Kontakt> findAll() throws SQLException {
		return this.findVector("SELECT idKontakt, erzeugungsdatum, modifikationsdatum, nutzer_idNutzer FROM kontakt ORDER BY idKontakt");
	}
	/**
	 * Diese Methode liefert alle Kontakte, die eine Nutzer erstellt hat
	 * @param id
	 * @return findVector
	 * @throws SQLException
	 */
	public Vector<Kontakt> findAllByNutzerId(int id) throws SQLException {
		return this.findVector("SELECT idKontakt, erzeugungsdatum, modifikationsdatum, nutzer_idNutzer FROM kontakt WHERE nutzer_idNutzer=" + id +" ORDER BY idKontakt");
	}
	
	/**
	 * Einfügen eines Kontaktes in der DB
	 * @param k
	 * @return k, der Kotakt wird ausgegeben der in der DB reingeschrieben worden ist
	 * @throws SQLException
	 */
	public Kontakt insert(Kontakt k) throws SQLException {
		String sql = String.format("INSERT INTO kontakt (erzeugungsdatum, modifikationsdatum, nutzer_idNutzer) VALUES "
				+ "('%s', '%s', %o)", toSqlDate(k.getErzeugungsdatum()), toSqlDate(k.getModifikationsdatum()), k.getIdNutzer());
		return this.insert(sql);

	}
	
	private String toSqlDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param k
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 * @throws SQLException 
	 */
	public Kontakt update(Kontakt k) throws SQLException {
		this.excecute(String.format("UPDATE kontakt SET "
				+ "erzeugungsdatum='%erzeugungsdatum', "
				+ "modifikationsdatum='%modifikationsdatum', "
				+ "nutzer_idNutzer='%idNutzer' "
				+ "WHERE idKontakt='%idKontakt'", k.getErzeugungsdatum(), k.getModifikationsdatum(),  k.getId()));
		return k;
	}

	/**
	 * Löschen der Daten eines <code>Kontakte</code>-Objekts aus der Datenbank.
	 * 
	 * @param k
	 *            das aus der DB zu löschende "Objekt"
	 * @throws SQLException 
	 */
	public void delete(Kontakt k) throws SQLException {
		this.excecute("DELETE FROM kontakt WHERE idKontakt=" + k.getId());
	}
	
	@Override
	protected Kontakt createFromResultSet(ResultSet rs) throws SQLException {
		Kontakt kontakt = new Kontakt();
		kontakt.setId((rs.getInt("idKontakt")));;
		kontakt.setErzeugungsdatum(rs.getDate("erzeugungsdatum"));
		kontakt.setModifikationsdatum(rs.getDate("modifikationsdatum"));
		kontakt.setIdNutzer(rs.getInt("nutzer_idNutzer"));
		return kontakt;
	}

}
