package de.hdm.KontaktSharing.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.KontaktSharing.shared.bo.Teilhaberschaft;


public class TeilhaberschaftMapper {

	/**
	 * Die Klasse TeilhaberschaftMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @see teilhaberschaftMapper()
	 */

	private static TeilhaberschaftMapper teilhaberschaftMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	 * neue Instanzen dieser Klasse zu erzeugen.
	 */

	protected TeilhaberschaftMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>TeilhaberschaftMapper.teilhaberschaftMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
	 * Instanz von <code>TeilhaberschaftMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> TeilhaberschaftMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
	 * 
	 * @return DAS <code>TeilhaberschaftMapper</code>-Objekt.
	 * @see teilhaberschaftMapper
	 */

	public static TeilhaberschaftMapper teilhaberschaftMapper() {
		if (teilhaberschaftMapper == null) {
			teilhaberschaftMapper = new TeilhaberschaftMapper();
		}

		return teilhaberschaftMapper;
	}
	
	public Teilhaberschaft findByKey(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery("SELECT idTeilhaberschaft FROM teilhaberschaft "
					+ "WHERE idTeilhaberschaft=" + id);

			/*
			 * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben werden.
			 * Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// neue Teilhaberschaft wird ausgegeben
				return new Teilhaberschaft(rs);
			}
		}

		catch (SQLException e2) {
			e2.printStackTrace();
			// Teilhaberschaft gefunden, aber zuordnung zum BO resultiert in einem Fehler
			return null;
		}
		// Kein Teilhaberschaft gefunden dann null
		return null;
	}

	/**
	 * Auslesen aller Teilhaberschaften.
	 *
	 * @return Ein Vektor mit Teilhaberschaft-Objekten, die sämtliche Teilhaberschaften
	 *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter oder
	 *         ggf. auch leerer Vetor zurückgeliefert.
	 */
	public Vector<Teilhaberschaft> findAll() {
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<Teilhaberschaft> result = new Vector<Teilhaberschaft>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT idTeilhaberschaft FROM teilhaberschaft");

			// Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
			while (rs.next()) {
				Teilhaberschaft th = new Teilhaberschaft(rs);

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.addElement(th);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}


	public Teilhaberschaft insert(Teilhaberschaft th) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			
			// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO teilhaberschaft (idTeilhaberschaft) "
						+ "VALUES (" + th.getId() +  ")");
			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return th;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param th
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */
	public Teilhaberschaft update(Teilhaberschaft th) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE teilhaberschaft;");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Um Analogie zu insert(Teilhaberschaft th) zu wahren, geben wir th zurück
		return th;
	}

	/**
	 * Löschen der Daten eines <code>Teilhaberschaft</code>-Objekts aus der Datenbank.
	 * 
	 * @param th
	 *            das aus der DB zu löschende "Objekt"
	 */
	public void delete(Teilhaberschaft th) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM teilhaberschaft " + "WHERE idTeilhaberschaft=" + th.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

}
