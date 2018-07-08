package de.hdm.KontaktSharing.shared.bo;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * Realisierung einer Eigenschaftauspr�gung. Eine Eigenschaftsauspr�gung
 * besitzt Text, Datum und Zahl.
 */

public class Eigenschaftauspraegung extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Text von Eigenschaftsauspr�gung
	 */
	private String text = "";

	/**
	 * Zahl von Eigenschaftsauspr�gung
	 */
	private Integer zahl = 0;

	/**
	 * Datum von Eigenschaftsauspr�gung
	 */
	private Date datum;// nochmal nach schauen

	private int idEigenschaft;
	private int idKontakt;
	
	/**
	 * no argument constructor
	 */
	public Eigenschaftauspraegung() {
		super();
	}


	/**
	 * Auslesen des Textes.
	 * 
	 * @return text
	 */
	public String getText() {
		return this.text;

	}

	/**
	 * Auslesen der Zahl
	 * 
	 * @return zahl
	 */
	public Integer getZahl() {
		return this.zahl;
	}
	
	/**
	 * Zahl als String auslesen
	 * @return zahl
	 */
	public String getZahlAsString() {
		return String.valueOf(this.zahl);
	}

	/**
	 * Auslesen vom Datum
	 * 
	 * @return datum
	 */
	public Date getDatum() {
		return this.datum;
	}

	/**
	 * Setzen des Textes der Eigenschaftsauspr�gung
	 * 
	 * @param string
	 */
	public void setText(String string) {
		this.text = string;
	}

	/**
	 * Setzen der Zahl der Eigenschaftsauspr�gung
	 */
	public void setZahl(Integer i) {
		this.zahl = i;
	}

	/**
	 * Setzen des Datums der Eigenschaftsauspr�gung
	 */
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	/**
	 * eigenschaftid auslesen
	 * @return idEigenschaft
	 */
	public int getIdEigenschaft() {
		return idEigenschaft;
	}
	/**
	 * Eigenschaftid als string ausgeben
	 * @return idEigenschaft
	 */
	public String getIdEigenschaftAsString() {
		return String.valueOf(this.idEigenschaft);
	}
	/**
	 * Eigenschaftid setzen
	 * @param idEigenschaft
	 */
	public void setIdEigenschaft(int idEigenschaft) {
		this.idEigenschaft = idEigenschaft;
	}
	/**
	 * Kontaktid auslesen
	 * @return idKontakt
	 */
	public int getIdKontakt() {
		return idKontakt;
	}
	/**
	 * auslesen der kontaktid als string
	 * @return idKontakt
	 */
	public String getIdKontaktAsString() {
		return String.valueOf(this.idKontakt);
	}
	/**
	 * Kontakt setzen
	 * @param idKontakt
	 */
	public void setIdKontakt(int idKontakt) {
		this.idKontakt = idKontakt;
	}
	/**
	 * Wert des Typs auslesen
	 * @param typ
	 * @return typ
	 */
	public String getValue(Typ typ) {
		if (typ == Typ.STRING) {
			return this.getText();
		} else if (typ == Typ.DATE) {
			return this.getDatum().toString();
		} else if (typ == Typ.INT) {
			return String.valueOf(this.getZahl());
		} else {
			return null;
		}
	}
	/**
	 * Wert des Typs setzen
	 * @param typ
	 * @param value
	 */
	public void setValue(Typ typ, String value) {
		if (typ == Typ.STRING) {
			this.setText(value);
		} else if (typ == Typ.DATE) {
			DateTimeFormat simpleDateFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
			this.setDatum(simpleDateFormat.parse(value));
		} else if (typ == Typ.INT) {
			this.setZahl(Integer.valueOf(value));
		}
	}

}
