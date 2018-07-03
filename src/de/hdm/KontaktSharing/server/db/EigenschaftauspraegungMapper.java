package de.hdm.KontaktSharing.server.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import de.hdm.KontaktSharing.shared.bo.Eigenschaftauspraegung;
import de.hdm.KontaktSharing.shared.bo.Kontakt;

public class EigenschaftauspraegungMapper extends CommonMapper<Eigenschaftauspraegung> {

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
	 * @throws SQLException 
	 */

	public Eigenschaftauspraegung findByKey(int id) throws SQLException {
		return this.findObject("SELECT idEigenschaftauspraegung, Eigenschaft_idEigenschaft, Kontakt_idKontakt, Text, Zahl, Datum FROM eigenschaftauspraegung "
					+ "WHERE idEigenschaftauspraegung=" + id);
	}
	
	public Vector<Eigenschaftauspraegung> findAllByIdKontakt(int id) throws SQLException {
		return this.findVector("SELECT idEigenschaftauspraegung, Eigenschaft_idEigenschaft, Kontakt_idKontakt, Text, Zahl, Datum FROM eigenschaftauspraegung "
				+ "WHERE Kontakt_idKontakt=" + id);
	}

	/**
	 * Auslesen aller Eigenschaftsausprägung.
	 * 
	 * @return Ein ArrayList mit Eigenschaftsausprägung-Objekten, die sämtliche
	 *         Eigenschaftauspraegungen repräsentieren. Bei evtl. Exceptions wird ein
	 *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
	 * @throws SQLException 
	 */
	public Vector<Eigenschaftauspraegung> findAll() throws SQLException {
		return this.findVector("SELECT idEigenschaftauspraegung, Eigenschaft_idEigenschaft, Kontakt_idKontakt, Text, Zahl, Datum FROM Eigenschaftauspraegung");
	}

	public Eigenschaftauspraegung insert(Eigenschaftauspraegung ea) throws SQLException {
		return this.insert("INSERT INTO Eigenschaftauspraegung (Text, Zahl, Datum, Eigenschaft_idEigenschaft, Kontakt_idKontakt) VALUES (%s, %s, %s, %s, %s)", ea.getText(), ea.getZahlAsString(), toSqlDate(ea.getDatum()), ea.getIdEigenschaftAsString(), ea.getIdKontaktAsString());
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param ea
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 * @throws SQLException 
	 */
	public Eigenschaftauspraegung update(Eigenschaftauspraegung ea) throws SQLException {
		if(ea.getText() != null) {
			this.excecute(String.format("UPDATE eigenschaftauspraegung SET Text='%s' WHERE idEigenschaftauspraegung='%s';", ea.getText(), String.valueOf(ea.getId())));	
		} else if(ea.getDatum() != null) {
			this.excecute(String.format("UPDATE eigenschaftauspraegung SET Datum='%s WHERE idEigenschaftauspraegung=%s;", ea.getDatum().toString(), ea.getId()));
		} else if(ea.getZahlAsString() != null) {
			this.excecute(String.format("UPDATE eigenschaftauspraegung SET Zahl=%s WHERE idEigenschaftauspraegung=%s;", ea.getZahlAsString(), ea.getId()));
		}
		return this.findByKey(ea.getId());
	}
	
	private String toSqlDate(Date date) {
		if(date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * Löschen der Daten einer <code>Eigenschaftauspraegung</code>-Objekts aus der
	 * Datenbank.
	 * 
	 * @param ea
	 *            das aus der DB zu löschende "Objekt"
	 * @throws SQLException 
	 */
	public void delete(Eigenschaftauspraegung ea) throws SQLException {
		this.excecute("DELETE FROM eigenschaftauspraegung WHERE idEigenschaftauspraegung=" + ea.getId());
	}
	
	public void deleteByKontaktId(Kontakt kontakt) throws SQLException {
		this.excecute("DELETE FROM eigenschaftauspraegung WHERE Kontakt_idKontakt=" + kontakt.getId());
	}

	@Override
	protected Eigenschaftauspraegung createFromResultSet(ResultSet rs) throws SQLException {
		Eigenschaftauspraegung auspraegung = new Eigenschaftauspraegung();
		auspraegung.setId(rs.getInt("idEigenschaftauspraegung"));
		auspraegung.setDatum(rs.getDate("Datum"));
		auspraegung.setIdEigenschaft(rs.getInt("Eigenschaft_idEigenschaft"));
		auspraegung.setIdKontakt(rs.getInt("Kontakt_idKontakt"));
		auspraegung.setText(rs.getString("Text"));
		auspraegung.setZahl(rs.getInt("Zahl"));
		return auspraegung;
	}

}
