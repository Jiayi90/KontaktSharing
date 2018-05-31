package de.hdm.KontaktSharing.server.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import de.hdm.KontaktSharing.shared.bo.Nutzer;

public class NutzerMapper extends CommonMapper<Nutzer> {
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
	/**
	 * Findet einen Nutzer anhand seiner id
	 * @param id
	 * @return findObject
	 * @throws SQLException
	 */
	public Nutzer findByKey(int id) throws SQLException {
		return this.findObject("SELECT idNutzer, email FROM nutzer " + "WHERE idNutzer=" + id);
	}

	/**
	 * Auslesen aller Nutzer.
	 *
	 * @return Ein Vektor mit Nutzer-Objekten, die s�mtliche Nutzer repr�sentieren.
	 *         Bei evtl. Exceptions wird ein partiell gef�llter oder ggf. auch
	 *         leerer Vetor zur�ckgeliefert.
	 * @throws SQLException 
	 */
	public Vector<Nutzer> findAll() throws SQLException {
		return this.findVector("SELECT idNutzer, email FROM nutzer");
	}
	
	
	public Nutzer findByMail(String mail) throws SQLException {
		return this.findObject("SELECT idNutzer, Email FROM nutzer WHERE Email='" + mail +"'");
	}

	public Nutzer insert(Nutzer n) throws SQLException {
		this.excecute("INSERT INTO nutzer ( email) " + "VALUES (" + n.getEmail() + ")");
		return n;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param n
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter �bergebene Objekt
	 * @throws SQLException 
	 */
	public Nutzer update(Nutzer n) throws SQLException {
		this.excecute("UPDATE nutzer SET email ='" + n.getEmail() + " WHERE idNutzer = '" + n.getId() + "';");
		return n;
	}

	/**
	 * L�schen der Daten eines <code>Nutzers</code>-Objekts aus der Datenbank.
	 * 
	 * @param n
	 *            das aus der DB zu l�schende "Objekt"
	 * @throws SQLException 
	 */
	public void delete(Nutzer n) throws SQLException {
		this.excecute("DELETE FROM nutzer " + "WHERE idNutzer=" + n.getId());
	}

	@Override
	protected Nutzer createFromResultSet(ResultSet rs) throws SQLException {
		Nutzer nutzer = new Nutzer();
		nutzer.setId(rs.getInt("idNutzer"));
		nutzer.setEmail(rs.getString("Email"));
		return nutzer;
	}


}
