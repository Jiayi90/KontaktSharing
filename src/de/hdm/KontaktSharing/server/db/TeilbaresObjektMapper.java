package de.hdm.KontaktSharing.server.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import de.hdm.KontaktSharing.shared.bo.TeilbaresObjekt;

public class TeilbaresObjektMapper extends CommonMapper<TeilbaresObjekt> {
	
	private static TeilbaresObjektMapper teilbaresObjektMapper = null;
	public static TeilbaresObjektMapper teilbaresObjektMapper() {
		if (teilbaresObjektMapper == null) {
			teilbaresObjektMapper = new TeilbaresObjektMapper();
		}

		return teilbaresObjektMapper;
	}
	/**
	 * TeilbaresObejekt anhand der id auslesen
	 */
	@Override
	public TeilbaresObjekt findByKey(int id) throws SQLException {
		return this.findObject(
				"SELECT idTeilbaresObjekt, Kontaktliste_idKontaktliste, Eigenschaftausprägung_idEigenschaftauspraegung, Teilhaberschaft_idTeilhaberschaft FROM teilbaresobjekt WHERE idTeilbaresObjekt=%s",
				id);
	}
	/**
	 * alle TeilbareObjekte auslesen
	 */
	@Override
	public Vector<TeilbaresObjekt> findAll() throws SQLException {
		return this.findVector(
				"SELECT idTeilbaresObjekt, Kontaktliste_idKontaktliste, Eigenschaftausprägung_idEigenschaftauspraegung, Teilhaberschaft_idTeilhaberschaft FROM teilbaresobjekt");
	}
	/**
	 * Id aus der Liste auslesen lassen
	 * @param idListe
	 * @return to
	 * @throws SQLException
	 */
	public Integer findIdFromListe(int idListe) throws SQLException {
		TeilbaresObjekt to = this.findObject("SELECT idTeilbaresObjekt, Kontaktliste_idKontaktliste, Eigenschaftausprägung_idEigenschaftauspraegung, Teilhaberschaft_idTeilhaberschaft FROM teilbaresobjekt WHERE Kontaktliste_idKontaktliste=%s", idListe);
		if(to == null) {
			return null;
		}
		return to.getId();
	}
	/**
	 * alle Kontaktlisten anhand der Teilhaberschaft auslesen
	 * @param idTeilhaberschaft
	 * @return idTeilhaberschaft
	 * @throws SQLException
	 */
	public Vector<TeilbaresObjekt> findAllKontaktlisteByTeilhaberschaft(int idTeilhaberschaft) throws SQLException {
		return this.findVector("SELECT idTeilbaresObjekt, Kontaktliste_idKontaktliste, Eigenschaftausprägung_idEigenschaftauspraegung, Teilhaberschaft_idTeilhaberschaft FROM teilbaresobjekt WHERE Teilhaberschaft_idTeilhaberschaft=%s AND Kontaktliste_idKontaktliste IS NOT NULL", idTeilhaberschaft);
	}
	/**
	 * Datensatz in TeilbaresObjekt auslesen
	 * @param to
	 * @return to
	 * @throws SQLException
	 */
	public TeilbaresObjekt insert(TeilbaresObjekt to) throws SQLException {
		return this.insert("INSERT INTO teilbaresobjekt (Kontaktliste_idKontaktliste, Eigenschaftausprägung_idEigenschaftauspraegung, Teilhaberschaft_idTeilhaberschaft) VALUES (%s, %s, %s)", to.getIdKontaktliste(), to.getIdEigenschaftsauspraegung(), to.getIdTeilhaberschaft());
	}
	/**
	 * 
	 * @param idEigenschaftauspraegung
	 * @param idTeilhaberschaft, idTeilhaberschaft
	 * @return idEigenschaftauspraegung
	 * @throws SQLException
	 */
	public TeilbaresObjekt insertEigenschaftauspraegung(int idEigenschaftauspraegung, int idTeilhaberschaft) throws SQLException {
		return this.insert("INSERT INTO teilbaresobjekt (Kontaktliste_idKontaktliste, Eigenschaftausprägung_idEigenschaftauspraegung, Teilhaberschaft_idTeilhaberschaft) VALUES (null, %s, %s)", idEigenschaftauspraegung, idTeilhaberschaft);
	}
	/**
	 * 
	 * @param idKontaktliste
	 * @param idTeilhaberschaft
	 * @return idKontaktliste, idTeilhaberschaft
	 * @throws SQLException
	 */
	public TeilbaresObjekt insertKontaktliste(int idKontaktliste, int idTeilhaberschaft) throws SQLException {
		return this.insert("INSERT INTO teilbaresobjekt (Kontaktliste_idKontaktliste, Eigenschaftausprägung_idEigenschaftauspraegung, Teilhaberschaft_idTeilhaberschaft) VALUES (%s, null, %s)", idKontaktliste, idTeilhaberschaft);
	}
	/**
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void delete(int id) throws SQLException {
		this.excecute("DELETE FROM teilbaresobjekt WHERE idTeilbaresObjekt=" + id);
	}
	/**
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void deleteForKontaktliste(int id) throws SQLException {
		this.excecute("DELETE FROM teilbaresobjekt WHERE Kontaktliste_idKontaktliste=" + id);
	}
	/**
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void deleteForEigenschaftsauspraegung(int id) throws SQLException {
		this.excecute("DELETE FROM teilbaresobjekt WHERE Eigenschaftausprägung_idEigenschaftauspraegung=" + id);
	}
	/**
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void deleteForTeilhaberschaft(int id) throws SQLException {
		this.excecute("DELETE FROM teilbaresobjekt WHERE Teilhaberschaft_idTeilhaberschaft=" + id);
	}
	
	

	@Override
	protected TeilbaresObjekt createFromResultSet(ResultSet rs) throws SQLException {
		TeilbaresObjekt to = new TeilbaresObjekt();
		to.setId(rs.getInt("idTeilbaresObjekt"));
		to.setIdKontaktliste(rs.getInt("Kontaktliste_idKontaktliste"));
		to.setIdEigenschaftsauspraegung(rs.getInt("Eigenschaftausprägung_idEigenschaftauspraegung"));
		to.setIdTeilhaberschaft(rs.getInt("Teilhaberschaft_idTeilhaberschaft"));
		return to;
	}

}
