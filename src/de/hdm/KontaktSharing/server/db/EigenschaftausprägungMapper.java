package de.hdm.KontaktSharing.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import de.hdm.KontaktSharing.shared.bo.Eigenschaftausprägung;

public class EigenschaftausprägungMapper {

	/**
	 * Die Klasse EigenschaftausprägungMapper wird nur einmal instantiiert. Man
	 * spricht hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @see eigenschaftausprägungMapper()
	 */

	private static EigenschaftausprägungMapper eigenschaftausprägungMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	 * neue Instanzen dieser Klasse zu erzeugen.
	 */

	protected EigenschaftausprägungMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>EigenschaftausprägungMapper.eigenschaftausMapper()</code>. Sie stellt
	 * die Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>EigenschaftausprägungMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> EigenschaftausprägungMapper sollte nicht mittels
	 * <code>new</code> instantiiert werden, sondern stets durch Aufruf dieser
	 * statischen Methode.
	 * 
	 * @return DAS <code>EigenschaftausprägungMapper</code>-Objekt.
	 * @see eigenschaftausMapper
	 */

	public static EigenschaftausprägungMapper eigenschaftausprägungMapper() {
		if (eigenschaftausprägungMapper == null) {
			eigenschaftausprägungMapper = new EigenschaftausprägungMapper();
		}

		return eigenschaftausprägungMapper;
	}

	/**
	 * Suchen einer Eigenschaftausprägung mit vorgegebener id. Da diese eindeutig
	 * ist, wird genau ein Objekt zur�ckgegeben.
	 * 
	 * @param id
	 *            Primärschlüsselattribut (->DB)
	 * @return Eigenschaftausprägung-Objekt, das dem übergebenen Schlüssel
	 *         entspricht, null bei nicht vorhandenem DB-Tupel.
	 */

	public Eigenschaftausprägung findByKey(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery("SELECT idEigenschaftausprägung, text, zahl, datum FROM eigenschaftausprägung "
					+ "WHERE idEigenschaftausprägung=" + id);

			/*
			 * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben werden.
			 * Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				Eigenschaftausprägung ea = new Eigenschaftausprägung();
				ea.setId(rs.getInt("idEigenschaftausprägung"));
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
	 * @return Ein Vektor mit Eigenschaftsausprägung-Objekten, die sämtliche
	 *         Eigenschaftausprägungen repräsentieren. Bei evtl. Exceptions wird ein
	 *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
	 */
	public Vector<Eigenschaftausprägung> findAll() {
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<Eigenschaftausprägung> result = new Vector<Eigenschaftausprägung>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idEigenschaftausprägung, Zahl, Datum FROM Eigenschaftausprägung");

			// Für jeden Eintrag im Suchergebnis wird nun ein Eigenschaftausprägung-Objekt
			// erstellt.
			while (rs.next()) {
				Eigenschaftausprägung ea = new Eigenschaftausprägung();
				ea.setId(rs.getInt("idEigenschaftausprägung"));

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.addElement(ea);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

	public Eigenschaftausprägung insert(Eigenschaftausprägung ea) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			
			// Jetzt erst erfolgt die tatsächliche Einfügeoperation
			stmt.executeUpdate("INSERT INTO Eigenschaftausprägung (Text, Zahl, Datum) " + "VALUES (" + ea.getText()
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
	public Eigenschaftausprägung update(Eigenschaftausprägung ea) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE eigenschaftausprägung SET text ='" + ea.getText() + "', " + "zahl='"
					+ ea.getZahl() + "', " + "datum='" + ea.getDatum() + "" + "WHERE idEigenschaftsausprägung = '"
					+ ea.getId() + "';");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Um Analogie zu insert(Eigenschaftausprägung ea) zu wahren, geben wir ea
		// zurück
		return ea;
	}

	/**
	 * Löschen der Daten einer <code>Eigenschaftausprägung</code>-Objekts aus der
	 * Datenbank.
	 * 
	 * @param ea
	 *            das aus der DB zu löschende "Objekt"
	 */
	public void delete(Eigenschaftausprägung ea) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM eigenschaftausprägung " + "WHERE idEigenschaftausprägung=" + ea.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

}
