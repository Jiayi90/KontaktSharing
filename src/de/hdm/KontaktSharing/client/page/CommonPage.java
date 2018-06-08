package de.hdm.KontaktSharing.client.page;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.KontaktSharing.client.ClientsideSettings;
import de.hdm.KontaktSharing.shared.KontaktSharingAdministrationAsync;


public abstract class CommonPage extends VerticalPanel {
	KontaktSharingAdministrationAsync kontaktSharingAdmin;

	@Override
	public void onLoad() {
		kontaktSharingAdmin = ClientsideSettings.getKontaktSharingAdministration();
		/*
		 * Bevor wir unsere eigene Formatierung veranslassen, überlassen wir es der
		 * Superklasse eine Initialisierung vorzunehmen.
		 */
		super.onLoad();

		/*
		 * Als erstes geben wir stets die Headline des Showcase aus. Da
		 * getHeadlineText() als abstrakte Methode bzw. als Einschubmethode realisiert
		 * wird, obliegt es den Subklassen, für eine Ausgestaltung also Implementierung
		 * zu sorgen.
		 */
		this.add(this.createHeadline(getHeadlineText()));

		/*
		 * Wenn alles vorbereitet ist, stoßen wir die run()-Methode an. Auch run() ist
		 * als abstrakte Methode bzw. als Einschubmethode realisiert. Auch hier ist es
		 * Aufgabe der Subklassen, für deren Implementierung zu sorgen.
		 */
		this.run();
	}

	protected HTML createHeadline(String text) {
		HTML content = new HTML(text);
		content.setStylePrimaryName("page-headline");
		this.getElement().setId("content");
		return content;
	}

	protected abstract String getHeadlineText();

	protected abstract void run();
}
