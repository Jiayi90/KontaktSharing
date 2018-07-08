package de.hdm.KontaktSharing.client.page;

import com.google.gwt.user.client.ui.Label;

public class EditShareContakt extends CommonPage {
	
	int idTh;

	public EditShareContakt(Integer teilhaberschaftId) {
		idTh = teilhaberschaftId; 
	}

	@Override
	protected String getHeadlineText() {
		return "Teilhaberschaft bearbeiten";
	}

	@Override
	protected void run() {
		this.add(new Label("Not implemented yet"));
	}

}
