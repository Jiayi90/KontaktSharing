package de.hdm.KontaktSharing.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.KontaktSharing.shared.bo.Eigenschaft;
import de.hdm.KontaktSharing.shared.bo.Kontakt;


public class EigenschaftMapper extends CommonMapper<Eigenschaft> {

	/**
	 * Die Klasse EigenschaftMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @see eigenschaftMapper()
	 */
	private static EigenschaftMapper eigenschaftMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	 * neue Instanzen dieser Klasse zu erzeugen.
	 */

	protected EigenschaftMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>EigenschaftMapper.eigenschaftMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
	 * Instanz von <code>EIgenschaftMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> EigenschaftMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
	 * 
	 * @return DAS <code>EigenschaftMapper</code>-Objekt.
	 * @see eigenschaftMapper
	 */
	public static EigenschaftMapper eigenschaftMapper() {
		if (eigenschaftMapper == null) {
			eigenschaftMapper = new EigenschaftMapper();
		}

		return eigenschaftMapper;
	}

	/**
	 * Suchen einer Eigenschaft mit vorgegebener id. Da diese eindeutig ist, wird
	 * genau ein Objekt zur�ckgegeben.
	 * 
	 * @param id
	 *            Primärschlüsselattribut (->DB)
	 * @return Eigenschaft-Objekt, das dem übergebenen Schlüssel entspricht, null
	 *         bei nicht vorhandenem DB-Tupel.
	 * @throws SQLException 
	 */

	public Eigenschaft findByKey(int id) throws SQLException {
		return this.findObject("SELECT idEigenschaft, Bezeichnung, Typ, mehrfach FROM eigenschaft WHERE idEigenschaft=" + id);
	}

	public Vector<Eigenschaft> findAll() throws SQLException {
		return this.findVector("SELECT idEigenschaft, Bezeichnung, Typ, mehrfach FROM eigenschaft");
	}

	public Eigenschaft insert(Eigenschaft e) throws SQLException {
		return this.insert("INSERT INTO Eigenschaft (Bezeichnung, Typ, mehrfach) " + 
				"VALUES (" + e.getBezeichnung() + ","+ e.getTyp() + ","+ e.isMehrfach() +")");
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param e
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */
	public Eigenschaft update(Eigenschaft e) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE eigenschaft SET Bezeichnung ='" + e.getBezeichnung() + "', " + "Typ='"
					+ e.getTyp() + ";" + "WHERE idEigenschaft = '" + e.getId() + "'");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Um Analogie zu insert(Eigenschaft e) zu wahren, geben wir e zurück
		return e;
	}

	/**
	 * Löschen der Daten eines <code>Eigenschaft</code>-Objekts aus der Datenbank.
	 * 
	 * @param e
	 *            das aus der DB zu löschende "Objekt"
	 */
	public void delete(Eigenschaft e) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM eigenschaft " + "WHERE idEigenschaft=" + e.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	@Override
	protected Eigenschaft createFromResultSet(ResultSet rs) throws SQLException {
		Eigenschaft eigenschaft = new Eigenschaft();
		eigenschaft.setId(rs.getInt("idEigenschaft"));
		eigenschaft.setBezeichnung(rs.getString("Bezeichnung"));
		eigenschaft.setTyp(rs.getString("Typ"));
		eigenschaft.setMehrfach(rs.getBoolean("mehrfach"));
		return eigenschaft;
	}

}
