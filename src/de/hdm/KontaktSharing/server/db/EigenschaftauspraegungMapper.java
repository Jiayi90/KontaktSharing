package de.hdm.KontaktSharing.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import de.hdm.KontaktSharing.shared.bo.Eigenschaftauspraegung;

public class EigenschaftauspraegungMapper {

	/**
	 * Die Klasse EigenschaftauspraegungMapper wird nur einmal instantiiert. Man
	 * spricht hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @see eigenschaftauspraegungMapper()
	 */

	private static EigenschaftauspraegungMapper eigenschaftauspraegungMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	 * neue Instanzen dieser Klasse zu erzeugen.
	 */

	protected EigenschaftauspraegungMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>EigenschaftauspraegungMapper.eigenschaftausMapper()</code>. Sie stellt
	 * die Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>EigenschaftauspraegungMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> EigenschaftauspraegungMapper sollte nicht mittels
	 * <code>new</code> instantiiert werden, sondern stets durch Aufruf dieser
	 * statischen Methode.
	 * 
	 * @return DAS <code>EigenschaftauspraegungMapper</code>-Objekt.
	 * @see eigenschaftausMapper
	 */

	public static EigenschaftauspraegungMapper eigenschaftauspraegungMapper() {
		if (eigenschaftauspraegungMapper == null) {
			eigenschaftauspraegungMapper = new EigenschaftauspraegungMapper();
		}

		return eigenschaftauspraegungMapper;
	}

	/**
	 * Suchen einer Eigenschaftauspraegung mit vorgegebener id. Da diese eindeutig
	 * ist, wird genau ein Objekt zur�ckgegeben.
	 * 
	 * @param id
	 *            Primärschlüsselattribut (->DB)
	 * @return Eigenschaftauspraegung-Objekt, das dem übergebenen Schlüssel
	 *         entspricht, null bei nicht vorhandenem DB-Tupel.
	 */

	public Eigenschaftauspraegung findByKey(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery("SELECT idEigenschaftauspraegung, text, zahl, datum FROM eigenschaftauspraegung "
					+ "WHERE idEigenschaftauspraegung=" + id);

			/*
			 * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben werden.
			 * Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				Eigenschaftauspraegung ea = new Eigenschaftauspraegung();
				ea.setId(rs.getInt("idEigenschaftauspraegung"));
				return ea;
			}
		}

		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Auslesen aller Eigenschaftsausprägung.
	 * 
	 * @return Ein ArrayList mit Eigenschaftsausprägung-Objekten, die sämtliche
	 *         Eigenschaftauspraegungen repräsentieren. Bei evtl. Exceptions wird ein
	 *         partiell gefüllter oder ggf. auch leerer ArrayList zurückgeliefert.
	 */
	public ArrayList<Eigenschaftauspraegung> findAll() {
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten
		ArrayList<Eigenschaftauspraegung> result = new ArrayList<Eigenschaftauspraegung>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idEigenschaftauspraegung, Zahl, Datum FROM Eigenschaftauspraegung");

			// Für jeden Eintrag im Suchergebnis wird nun ein Eigenschaftauspraegung-Objekt
			// erstellt.
			while (rs.next()) {
				Eigenschaftauspraegung ea = new Eigenschaftauspraegung();
				ea.setId(rs.getInt("idEigenschaftauspraegung"));

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.add(ea);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

	public Eigenschaftauspraegung insert(Eigenschaftauspraegung ea) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			
			// Jetzt erst erfolgt die tatsächliche Einfügeoperation
			stmt.executeUpdate("INSERT INTO Eigenschaftauspraegung (Text, Zahl, Datum) " + "VALUES (" + ea.getText()
					+ "," + ea.getZahl() + "," + ea.getDatum() + ")");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return ea;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param ea
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */
	public Eigenschaftauspraegung update(Eigenschaftauspraegung ea) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE eigenschaftauspraegung SET text ='" + ea.getText() + "', " + "zahl='"
					+ ea.getZahl() + "', " + "datum='" + ea.getDatum() + "" + "WHERE idEigenschaftsausprägung = '"
					+ ea.getId() + "';");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Um Analogie zu insert(Eigenschaftauspraegung ea) zu wahren, geben wir ea
		// zurück
		return ea;
	}

	/**
	 * Löschen der Daten einer <code>Eigenschaftauspraegung</code>-Objekts aus der
	 * Datenbank.
	 * 
	 * @param ea
	 *            das aus der DB zu löschende "Objekt"
	 */
	public void delete(Eigenschaftauspraegung ea) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM eigenschaftauspraegung " + "WHERE idEigenschaftauspraegung=" + ea.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

}
