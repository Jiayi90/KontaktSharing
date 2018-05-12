package de.hdm.KontaktSharing.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.KontaktSharing.shared.bo.Eigenschaft;
import de.hdm.KontaktSharing.shared.bo.Eigenschaftausprägung;

public class EigenschaftMapper {

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
	 */

	public Eigenschaft findByKey(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt
					.executeQuery("SELECT idEigenschaft, bezeichnung, typ FROM eigenschaft " + "WHERE idEigenschaft=" + id);

			/*
			 * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben werden.
			 * Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				Eigenschaft e = new Eigenschaft();
				e.setId(rs.getInt("idEigenschaft"));
				return e;
			}
		}

		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	public Vector<Eigenschaft> findAll() {
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<Eigenschaft> result = new Vector<Eigenschaft>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idEigenschaft, Bezeichnung, Typ FROM Eigenschaft");

			// Für jeden Eintrag im Suchergebnis wird nun ein Eigenschaft-Objekt
			// erstellt.
			while (rs.next()) {
				Eigenschaft e = new Eigenschaft();
				e.setId(rs.getInt("idEigenschaft"));

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.addElement(e);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

	public Eigenschaft insert(Eigenschaft e) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// Jetzt erst erfolgt die tatsächliche Einfügeoperation
			stmt.executeUpdate("INSERT INTO Eigenschaft (Bezeichnung, Typ) " + "VALUES (" + e.getBezeichnung() + ","
					+ e.getTyp() + ")");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return e;
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

}
