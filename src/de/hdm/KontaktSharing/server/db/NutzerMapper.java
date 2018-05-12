package de.hdm.KontaktSharing.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.KontaktSharing.shared.bo.Nutzer;

public class NutzerMapper {
	/**
	 * Die Klasse NutzerMapper wird nur einmal instantiiert. Man spricht hierbei von
	 * einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal f�r
	 * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @see nutzerMapper()
	 */

	private static NutzerMapper nutzerMapper = null;

	/**
	 * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
	 * neue Instanzen dieser Klasse zu erzeugen.
	 */

	protected NutzerMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>NutzerMapper.nutzerMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine einzige
	 * Instanz von <code>NutzerMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> NutzerMapper sollte nicht mittels <code>new</code> instantiiert
	 * werden, sondern stets durch Aufruf dieser statischen Methode.
	 * 
	 * @return DAS <code>NutzerMapper</code>-Objekt.
	 * @see nutzerMapper
	 */

	public static NutzerMapper nutzerMapper() {
		if (nutzerMapper == null) {
			nutzerMapper = new NutzerMapper();
		}

		return nutzerMapper;
	}

	public Nutzer findByKey(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausf�llen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery("SELECT idNutzer, email FROM nutzer " + "WHERE idNutzer=" + id);

			/*
			 * Da id Prim�rschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben werden.
			 * Pr�fe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// neue Nutzer wird ausgegeben
				return new Nutzer(rs);
			}
		}

		catch (SQLException e2) {
			e2.printStackTrace();
			// Nutzer gefunden, aber zuordnung zum BO resultiert in einem Fehler
			return null;
		}
		// Kein Nutzer gefunden dann null
		return null;
	}

	/**
	 * Auslesen aller Nutzer.
	 *
	 * @return Ein Vektor mit Nutzer-Objekten, die s�mtliche Nutzer repr�sentieren.
	 *         Bei evtl. Exceptions wird ein partiell gef�llter oder ggf. auch
	 *         leerer Vetor zur�ckgeliefert.
	 */
	public Vector<Nutzer> findAll() {
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<Nutzer> result = new Vector<Nutzer>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idNutzer, email FROM nutzer");

			// F�r jeden Eintrag im Suchergebnis wird nun ein Nutzer-Objekt erstellt.
			while (rs.next()) {
				Nutzer n = new Nutzer(rs);

				// Hinzuf�gen des neuen Objekts zum Ergebnisvektor
				result.addElement(n);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnisvektor zur�ckgeben
		return result;
	}

	public Nutzer insert(Nutzer n) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// Jetzt erst erfolgt die tats�chliche Einf�geoperation
			stmt.executeUpdate("INSERT INTO nutzer ( email) " + "VALUES (" + n.getEmail() + ")");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return n;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param n
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter �bergebene Objekt
	 */
	public Nutzer update(Nutzer n) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE nutzer SET email ='" + n.getEmail() + " WHERE idNutzer = '" + n.getId() + "';");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Um Analogie zu insert(Nutzer n) zu wahren, geben wir n zur�ck
		return n;
	}

	/**
	 * L�schen der Daten eines <code>Nutzers</code>-Objekts aus der Datenbank.
	 * 
	 * @param n
	 *            das aus der DB zu l�schende "Objekt"
	 */
	public void delete(Nutzer n) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM nutzer " + "WHERE idNutzer=" + n.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

}
