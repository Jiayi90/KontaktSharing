package de.hdm.KontaktSharing.shared.bo;

public class TeilhaberschaftKontaktliste extends Teilhaberschaft {
	private static final long serialVersionUID = 730644685487123901L;
	private Kontaktliste liste;
	
	public TeilhaberschaftKontaktliste() {
		
	}

	public TeilhaberschaftKontaktliste(Teilhaberschaft th) {
		this.setId(th.getId());
		this.setIdNutzer(th.getIdNutzer());
		this.setName(th.getName());
	}

	public Kontaktliste getListe() {
		return liste;
	}

	public void setListe(Kontaktliste liste) {
		this.liste = liste;
		this.liste.setWasShared(true);
	}
}
