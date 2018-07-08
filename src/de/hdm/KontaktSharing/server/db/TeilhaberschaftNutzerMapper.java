package de.hdm.KontaktSharing.server.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import de.hdm.KontaktSharing.shared.bo.Teilhaberschaft;
import de.hdm.KontaktSharing.shared.bo.TeilhaberschaftNutzer;

public class TeilhaberschaftNutzerMapper extends CommonMapper<TeilhaberschaftNutzer> {

	
	private static TeilhaberschaftNutzerMapper teilhaberschaftNutzerMapper = null;
	
	protected TeilhaberschaftNutzerMapper() {
	}
	public static TeilhaberschaftNutzerMapper teilhaberschaftNutzerMapper() {
		if (teilhaberschaftNutzerMapper == null) {
			teilhaberschaftNutzerMapper = new TeilhaberschaftNutzerMapper();
		}

		return teilhaberschaftNutzerMapper;
	}
	/**
	 * Teilhabenden anhand der id auslesen
	 */
	@Override
	public TeilhaberschaftNutzer findByKey(int id) throws SQLException {
		return null;
	}
	/**
	 * alle Teilhaber auslesen
	 */
	@Override
	public Vector<TeilhaberschaftNutzer> findAll() throws SQLException {
		return this.findVector("SELECT Teilhaberschaft_idTeilhaberschaft, Nutzer_idNutzer FROM teilhaberschaft_nutzer");
	}
	/**
	 * alle Teilhaberschaften des Nutzers auslesen
	 * @param id
	 * @return id
	 * @throws SQLException
	 */
	public Vector<TeilhaberschaftNutzer> findAllByNutzer(int id) throws SQLException {
		return this.findVector("SELECT Teilhaberschaft_idTeilhaberschaft, Nutzer_idNutzer FROM teilhaberschaft_nutzer WHERE Nutzer_idNutzer=%s", id);
	}
	/**
	 * alle Teilhaberschaften auslesen
	 * @param id
	 * @return id
	 * @throws SQLException
	 */
	public Vector<TeilhaberschaftNutzer> findAllTeilhaberschaft(int id) throws SQLException {
		return this.findVector("SELECT Teilhaberschaft_idTeilhaberschaft, Nutzer_idNutzer FROM teilhaberschaft_nutzer WHERE Teilhaberschaft_idTeilhaberschaft=%s", id);
	}
	/**
	 * Datensatz in TeilhaberschaftNutzer einfuegen
	 * @param thn
	 * @throws SQLException
	 */
	public void insert(TeilhaberschaftNutzer thn) throws SQLException {
		this.insert("INSERT INTO teilhaberschaft_nutzer (Teilhaberschaft_idTeilhaberschaft, Nutzer_idNutzer) VALUES (%s, %s)", thn.getIdTeilhaberschaft(), thn.getIdNutzer());
	}
	/**
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void deleteByTeilhaberschaft(int id) throws SQLException {
		this.excecute("DELETE FROM teilhaberschaft_nutzer WHERE Teilhaberschaft_idTeilhaberschaft=" + id);
	}
	/**
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void deleteByNutzer(int id) throws SQLException {
		this.excecute("DELETE FROM teilhaberschaft_nutzer WHERE Nutzer_idNutzer=" + id);
	}

	@Override
	protected TeilhaberschaftNutzer createFromResultSet(ResultSet rs) throws SQLException {
		TeilhaberschaftNutzer thn = new TeilhaberschaftNutzer();
		thn.setIdTeilhaberschaft(rs.getInt("Teilhaberschaft_idTeilhaberschaft"));
		thn.setIdNutzer(rs.getInt("Nutzer_idNutzer"));
		return thn;
	}


}
