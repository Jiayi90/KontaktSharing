package de.hdm.KontaktSharing.server.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import de.hdm.KontaktSharing.shared.bo.Kontaktliste;
import de.hdm.KontaktSharing.shared.bo.ListenStruktur;

public class ListenstrukturMapper extends CommonMapper<ListenStruktur> {
	
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

	private static ListenstrukturMapper listenstrukturMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	 * neue Instanzen dieser Klasse zu erzeugen.
	 */

	protected ListenstrukturMapper() {
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

	public static ListenstrukturMapper listenstrukturMapper() {
		if (listenstrukturMapper == null) {
			listenstrukturMapper = new ListenstrukturMapper();
		}

		return listenstrukturMapper;
	}

	/**
	 * Suchen eines Kontakts mit vorgegebener Kontaktid. Da diese eindeutig ist,
	 * wird genau ein Objekt zur�ckgegeben.
	 * 
	 * @param id
	 *           PK-Attribut (->DB)
	 * @return Kontakt-Objekt, das dem uebergebenen Schluessel entspricht, null bei
	 *         nicht vorhandenem DB-Tupel.
	 * @throws SQLException 
	 */

	@Override
	protected ListenStruktur createFromResultSet(ResultSet rs) throws SQLException {
		ListenStruktur list = new ListenStruktur();
		list.setIdKontakt(rs.getInt("Kontaktliste_idKontaktliste"));
		list.setIdKontaktliste(rs.getInt("Kontakt_idKontakt"));
		return list;
	}
	/**
	 * Listenstruktur anhand der id auslesen
	 */
	@Override
	public ListenStruktur findByKey(int id) throws SQLException {
		return null;
	}
	/**
	 * alle Listenstrukturen auslesen
	 */
	@Override
	public Vector<ListenStruktur> findAll() throws SQLException {
		return this.findVector("SELECT Kontaktliste_idKontaktliste, Kontakt_idKontakt FROM listenstruktur");
	}
	/**
	 * Listenstruktur anhand der KontaktlisteenId auslesen
	 * @param id
	 * @return id
	 * @throws SQLException
	 */
	public Vector<ListenStruktur> findByKontaktlistId(int id) throws SQLException {
		return this.findVector("SELECT Kontaktliste_idKontaktliste, Kontakt_idKontakt FROM listenstruktur WHERE Kontaktliste_idKontaktliste=%s", id);
	}
	/**
	 * Datensatz in Kontaktlistenid einfuegen
	 * @param idKontaktliste
	 * @param idKontakt
	 * @throws SQLException
	 */
	public void insert(int idKontaktliste, int idKontakt) throws SQLException {
		this.excecute("INSERT INTO listenstruktur (Kontaktliste_idKontaktliste, Kontakt_idKontakt) VALUES (%s, %s)", idKontaktliste, idKontakt);
	}
	/**
	 * Kontaktliste loeschen
	 * @param kl
	 * @throws SQLException
	 */
	public void delete(Kontaktliste kl) throws SQLException {
		this.delete(kl.getId());
	}
	/**
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void delete(int id) throws SQLException {
		this.excecute("DELETE FROM listenstruktur WHERE Kontaktliste_idKontaktliste=%s", id);
	}
	/**
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void deleteByKontaktId(int id) throws SQLException {
		this.excecute("DELETE FROM listenstruktur WHERE Kontakt_idKontakt=%s", id);
	}

}
