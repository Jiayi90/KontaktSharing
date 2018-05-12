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
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal f�r
	 * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @see kontaktlisteMapper()
	 */

	private static KontaktlisteMapper kontaktlisteMapper = null;

	/**
	 * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
	 * neue Instanzen dieser Klasse zu erzeugen.
	 */

	protected KontaktlisteMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>KontaktlisteMapper.kontaktlisteMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine einzige
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

			// Statement ausf�llen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery(
					"SELECT idKontaktliste, kontaktlistenname FROM eigenschaft " + "WHERE idEigenschaft=" + id);

			/*
			 * Da id Prim�rschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben werden.
			 * Pr�fe, ob ein Ergebnis vorliegt.
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

			// F�r jeden Eintrag im Suchergebnis wird nun ein Kontaktliste-Objekt
			// erstellt.
			while (rs.next()) {
				Kontaktliste kl = new Kontaktliste();
				kl.setId(rs.getInt("idKontaktliste"));

				// Hinzuf�gen des neuen Objekts zum Ergebnisvektor
				result.addElement(kl);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnisvektor zur�ckgeben
		return result;
	}

	public Kontaktliste insert(Kontaktliste kl) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// Jetzt erst erfolgt die tats�chliche Einf�geoperation
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
	 * @return das als Parameter �bergebene Objekt
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

		// Um Analogie zu insert(Kontaktliste kl) zu wahren, geben wir kl zur�ck
		return kl;
	}

	/**
	 * L�schen der Daten eines <code>Kontaktlisten</code>-Objekts aus der Datenbank.
	 * 
	 * @param kl
	 *            das aus der DB zu l�schende "Objekt"
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
