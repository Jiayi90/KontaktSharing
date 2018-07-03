package de.hdm.KontaktSharing.server.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import de.hdm.KontaktSharing.shared.bo.Kontaktliste;

public class KontaktlisteMapper extends CommonMapper<Kontaktliste>  {

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

	@Override
	public Kontaktliste findByKey(int id) throws SQLException {
		return this.findObject("SELECT idKontaktliste, Kontaktlistenname, nutzer_idNutzer FROM kontaktliste WHERE idKontaktliste=" + id);
	}

	@Override
	public Vector<Kontaktliste> findAll() throws SQLException {
		return this.findVector("SELECT idKontaktliste, Kontaktlistenname, nutzer_idNutzer FROM kontaktliste");
	}
	
	public Vector<Kontaktliste> findAllByNutzer(int idNutzer) throws SQLException {
		return this.findVector("SELECT idKontaktliste, Kontaktlistenname, nutzer_idNutzer FROM kontaktliste WHERE nutzer_idNutzer=" + idNutzer);
	}

	public Kontaktliste insert(String name, int idNutzer) throws SQLException {
		return this.insert("INSERT INTO kontaktliste (Kontaktlistenname, nutzer_idNutzer) VALUES (%s, %s)", name, idNutzer);
	}
	
	public Kontaktliste insert(Kontaktliste kl) throws SQLException {
		return this.insert(kl.getKontaktlistenname(), kl.getNutzerId());
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param kl
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 * @throws SQLException 
	 */
	public Kontaktliste update(Kontaktliste kl) throws SQLException {
		this.excecute("UPDATE kontaktliste SET Kontaktlistenname=%s, nutzer_idNutzer=%s WHERE idKontaktliste=%s", kl.getKontaktlistenname(), kl.getId());
		return this.findByKey(kl.getId());		
	}

	/**
	 * Löschen der Daten eines <code>Kontaktlisten</code>-Objekts aus der Datenbank.
	 * 
	 * @param kl
	 *            das aus der DB zu löschende "Objekt"
	 * @throws SQLException 
	 */
	public void delete(Kontaktliste kl) throws SQLException {
		this.excecute("DELETE FROM kontaktliste WHERE idKontaktliste=%s", kl.getId());
	}
	
	public void updateName(int id, String name) throws SQLException {
		this.excecute("UPDATE kontaktliste SET Kontaktlistenname=%s WHERE idKontaktliste=%s", name, id);
	}

	@Override
	protected Kontaktliste createFromResultSet(ResultSet rs) throws SQLException {
		Kontaktliste liste = new Kontaktliste();
		liste.setId(rs.getInt("idKontaktliste"));
		liste.setKontaktlistenname(rs.getString("Kontaktlistenname"));
		liste.setNutzerId(rs.getInt("nutzer_idNutzer"));
		return liste;
	}

}
