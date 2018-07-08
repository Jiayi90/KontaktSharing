package de.hdm.KontaktSharing.shared.bo;

public class TeilhaberschaftKontaktliste extends Teilhaberschaft {
	private static final long serialVersionUID = 730644685487123901L;
	private Kontaktliste liste;
	
	public TeilhaberschaftKontaktliste() {
		
	}
	/**
	 * teilhabende Kontaktliste setzen
	 * @param th
	 */
	public TeilhaberschaftKontaktliste(Teilhaberschaft th) {
		this.setId(th.getId());
		this.setIdNutzer(th.getIdNutzer());
		this.setName(th.getName());
	}
	/**
	 * kontaktliste auslesen
	 * @return liste
	 */
	public Kontaktliste getListe() {
		return liste;
	}
	/**
	 * kontaktliste auslesen
	 * @param liste
	 */
	public void setListe(Kontaktliste liste) {
		this.liste = liste;
		this.liste.setWasShared(true);
	}
}
