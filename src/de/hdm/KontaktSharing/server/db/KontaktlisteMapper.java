package de.hdm.KontaktSharing.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.KontaktSharing.shared.bo.Eigenschaft;
import de.hdm.KontaktSharing.shared.bo.Kontaktliste;

public class KontaktlisteMapper {

	/**
	 * Die Klasse KontaktlisteMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @see kontaktlisteMapper()
	 */

	private static KontaktlisteMapper kontaktlisteMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	 * neue Instanzen dieser Klasse zu erzeugen.
	 */

	protected KontaktlisteMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>KontaktlisteMapper.kontaktlisteMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
	 * Instanz von <code>KontaktlisteMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> KontaktlisteMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
	 * 
	 * @return DAS <code>KontaktlisteMapper</code>-Objekt.
	 * @see kontaktlisteMapper
	 */

	public static KontaktlisteMapper kontaktlisteMapper() {
		if (kontaktlisteMapper == null) {
			kontaktlisteMapper = new KontaktlisteMapper();
		}

		return kontaktlisteMapper;
	}

	public Kontaktliste findByKey(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery(
					"SELECT idKontaktliste, kontaktlistenname FROM eigenschaft " + "WHERE idEigenschaft=" + id);

			/*
			 * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben werden.
			 * Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				Kontaktliste kl = new Kontaktliste();
				kl.setId(rs.getInt("idKontaktliste"));
				return kl;
			}
		}

		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	public Vector<Kontaktliste> findAll() {
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<Kontaktliste> result = new Vector<Kontaktliste>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idKontaktliste, kontaktlistenname FROM Kontaktliste");

			// Für jeden Eintrag im Suchergebnis wird nun ein Kontaktliste-Objekt
			// erstellt.
			while (rs.next()) {
				Kontaktliste kl = new Kontaktliste();
				kl.setId(rs.getInt("idKontaktliste"));

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.addElement(kl);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

	public Kontaktliste insert(Kontaktliste kl) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// Jetzt erst erfolgt die tatsächliche Einfügeoperation
			stmt.executeUpdate(
					"INSERT INTO kontaktliste (Kontaktlistenname) " + "VALUES (" + kl.getKontaktlistenname() + ")");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return kl;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param kl
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */
	public Kontaktliste update(Kontaktliste kl) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE kontaktliste SET kontaktlistenname ='" + kl.getKontaktlistenname() + ";"
					+ "WHERE idKontaktliste = '" + kl.getId() + "'");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Um Analogie zu insert(Kontaktliste kl) zu wahren, geben wir kl zurück
		return kl;
	}

	/**
	 * Löschen der Daten eines <code>Kontaktlisten</code>-Objekts aus der Datenbank.
	 * 
	 * @param kl
	 *            das aus der DB zu löschende "Objekt"
	 */
	public void delete(Kontaktliste kl) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM kontaktliste " + "WHERE idKontaktliste=" + kl.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

}
