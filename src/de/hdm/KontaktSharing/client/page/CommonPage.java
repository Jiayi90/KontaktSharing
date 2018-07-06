package de.hdm.KontaktSharing.client.page;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.KontaktSharing.client.ClientsideSettings;
import de.hdm.KontaktSharing.shared.KontaktSharingAdministrationAsync;


public abstract class CommonPage extends VerticalPanel {
	KontaktSharingAdministrationAsync kontaktSharingAdmin;
	CommonPage page;
	HTML headline;
	Widget top;


	public void setTop(Widget top) {
		this.top = top;
	}

	@Override
	public void onLoad() {
		kontaktSharingAdmin = ClientsideSettings.getKontaktSharingAdministration();
		page = this;
		/*
		 * Bevor wir unsere eigene Formatierung veranslassen, �berlassen wir es der
		 * Superklasse eine Initialisierung vorzunehmen.
		 */
		super.onLoad();
	

		/*
		 * Als erstes geben wir stets die Headline des Showcase aus. Da
		 * getHeadlineText() als abstrakte Methode bzw. als Einschubmethode realisiert
		 * wird, obliegt es den Subklassen, f�r eine Ausgestaltung also Implementierung
		 * zu sorgen.
		 */
		this.createHeadline(getHeadlineText());
		this.add(headline);
		
		/*
		 * Wenn alles vorbereitet ist, sto�en wir die run()-Methode an. Auch run() ist
		 * als abstrakte Methode bzw. als Einschubmethode realisiert. Auch hier ist es
		 * Aufgabe der Subklassen, f�r deren Implementierung zu sorgen.
		 */
		this.run();

	}
	
	public int getLoggedInId() {
		return Integer.parseInt(Cookies.getCookie("id"));
	}
	
	
	public void updateHeadline(String text) {
		int index = this.getWidgetIndex(headline);
		this.remove(headline);
		createHeadline(text);
		this.insert(headline, index);
	}

	protected void createHeadline(String text) {
		headline = new HTML(text);
		headline.setStylePrimaryName("page-headline");
		this.getElement().setId("content");
	}

	protected abstract String getHeadlineText();

	protected abstract void run();
}