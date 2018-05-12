package de.hdm.KontaktSharing.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.KontaktSharing.shared.bo.Kontakt;

public class KontaktMapper {

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
	 */

	public Kontakt findByKey(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery("SELECT idKontakt, name, erzeugungsdatum, modifikationsdatum FROM kontakt "
					+ "WHERE idKontakt=" + id);

			/*
			 * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben werden.
			 * Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// neue Kontakt wird ausgegeben
				return new Kontakt(rs);
			}
		}

		catch (SQLException e2) {
			e2.printStackTrace();
			// Kontakt gefunden, aber zuordnung zum BO resultiert in einem Fehler
			return null;
		}
		// Kein Kontakt gefunden dann null
		return null;
	}

	/**
	 * Auslesen aller Kontakte.
	 *
	 * @return Ein Vektor mit Kontakt-Objekten, die sämtliche Kontakte
	 *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter oder
	 *         ggf. auch leerer Vetor zurückgeliefert.
	 */
	public Vector<Kontakt> findAll() {
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<Kontakt> result = new Vector<Kontakt>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT idKontakt, name, erzeugungsdatum, modifikationsdatum FROM kontakt" + " ORDER BY idKontakt");

			// Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
			while (rs.next()) {
				Kontakt k = new Kontakt(rs);

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.addElement(k);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

	public Kontakt insert(Kontakt k) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// Jetzt erst erfolgt die tatsächliche Einfügeoperation
			stmt.executeUpdate("INSERT INTO kontakt (name, erzeugungsdatum, modifikationsdatum) " + "VALUES ("
					+ k.getName() + "," + k.getErzeugungsdatum() + "," + k.getModifikationsdatum() + ")");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return k;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param k
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */
	public Kontakt update(Kontakt k) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE kontakt SET name ='" + k.getName() + "', " + "erzeugungsdatum='"
					+ k.getErzeugungsdatum() + "', " + "modifikationsdatum='" + k.getModifikationsdatum() + ";"
					+ "WHERE idKontakt = '" + k.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Um Analogie zu insert(Kontakt k) zu wahren, geben wir k zurück
		return k;
	}

	/**
	 * Löschen der Daten eines <code>Kontakte</code>-Objekts aus der Datenbank.
	 * 
	 * @param k
	 *            das aus der DB zu löschende "Objekt"
	 */
	public void delete(Kontakt k) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM kontakt " + "WHERE idKontakt=" + k.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

}
