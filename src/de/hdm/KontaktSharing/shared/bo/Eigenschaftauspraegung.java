package de.hdm.KontaktSharing.shared.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.TextBox;

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

	public Eigenschaftauspraegung() {
		super();
	}


	/**
	 * Auslesen des Textes.
	 */
	public String getText() {
		return this.text;

	}

	/**
	 * Auslesen der Zahl
	 */

	public Integer getZahl() {
		return this.zahl;
	}

	/**
	 * Auslesen vom Datum
	 */

	public Date getDatum() {
		return this.datum;
	}

	/**
	 * Setzen des Textes der Eigenschaftsauspr�gung
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

	public int getIdEigenschaft() {
		return idEigenschaft;
	}

	public void setIdEigenschaft(int idEigenschaft) {
		this.idEigenschaft = idEigenschaft;
	}

	public int getIdKontakt() {
		return idKontakt;
	}

	public void setIdKontakt(int idKontakt) {
		this.idKontakt = idKontakt;
	}

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
