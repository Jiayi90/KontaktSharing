package de.hdm.KontaktSharing.shared.bo;

public class TeilhaberschaftKontakt extends Teilhaberschaft {
	
	private Kontakt kontakt;

	public TeilhaberschaftKontakt() {
		
	}

	public TeilhaberschaftKontakt(Teilhaberschaft th) {
		this.setId(th.getId());
		this.setIdNutzer(th.getIdNutzer());
		this.setName(th.getName());
	}

	public Kontakt getKontakt() {
		return kontakt;
	}

	public void setKontakt(Kontakt kontakt) {
		this.kontakt = kontakt;
	}
}
