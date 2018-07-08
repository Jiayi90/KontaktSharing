package de.hdm.KontaktSharing.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.KontaktSharing.shared.bo.Nutzer;
import de.hdm.KontaktSharing.shared.bo.Teilhaberschaft;


public class TeilhaberschaftMapper extends CommonMapper<Teilhaberschaft> {

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
	
	public Teilhaberschaft findByKey(int id) throws SQLException {
		return this.findObject("SELECT idTeilhaberschaft, Name, Nutzer_idNutzer FROM teilhaberschaft WHERE idTeilhaberschaft=%s", id);
	}

	/**
	 * Auslesen aller Teilhaberschaften.
	 *
	 * @return Ein Vektor mit Teilhaberschaft-Objekten, die sämtliche Teilhaberschaften
	 *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter oder
	 *         ggf. auch leerer Vetor zurückgeliefert.
	 * @throws SQLException 
	 */
	public Vector<Teilhaberschaft> findAll() throws SQLException {
		return this.findVector("SELECT idTeilhaberschaft, Name ,Nutzer_idNutzer FROM teilhaberschaft");
	}

	/**
	 * Fuegt Datensatz in Teilhaberschaft ein
	 * @param th
	 * @return
	 * @throws SQLException
	 */
	public Teilhaberschaft insert(Teilhaberschaft th) throws SQLException {
		return this.insert("INSERT INTO teilhaberschaft (Name, Nutzer_idNutzer) VALUES (%s, %s)", th.getName(), th.getIdNutzer());
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param th
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 * @throws SQLException 
	 */
	public Teilhaberschaft update(Teilhaberschaft th) throws SQLException {
		this.excecute("UPDATE teilhaberschaft SET Name ='" + th.getName() + " WHERE idNutzer = '" + th.getId());
		return th;
	}

	/**
	 * Löschen der Daten eines <code>Teilhaberschaft</code>-Objekts aus der Datenbank.
	 * 
	 * @param th
	 *            das aus der DB zu löschende "Objekt"
	 * @throws SQLException 
	 */
	public void delete(Teilhaberschaft th) throws SQLException {
		this.delete(th.getId());
	}
	
	public void delete(int id) throws SQLException {
		this.excecute("DELETE FROM teilhaberschaft WHERE idTeilhaberschaft=" + id);
	}

	@Override
	protected Teilhaberschaft createFromResultSet(ResultSet rs) throws SQLException {
		Teilhaberschaft th = new Teilhaberschaft();
		th.setId(rs.getInt("idTeilhaberschaft"));
		th.setName(rs.getString("Name"));
		th.setIdNutzer(rs.getInt("Nutzer_idNutzer"));
		return th;
	}

}
