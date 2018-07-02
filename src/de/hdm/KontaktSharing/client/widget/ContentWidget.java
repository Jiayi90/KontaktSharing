package de.hdm.KontaktSharing.client.widget;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.KontaktSharing.shared.bo.Nutzer;

public class ContentWidget extends VerticalPanel {
	private VerticalPanel vpanel = new VerticalPanel();
	private FlexTable ft1 = new FlexTable();
	private HTML html1 = new HTML(
			"<article> <h2>System um Kontakte zu teilen</h2> " + "<section>" + "<h3>Teilbare Kontakt</h3> " + "<p>"
					+ "Um was geht es konkret? </br>" + "Dieses System ermöglicht das Teilen</br>"
					+ "der eigenen Kontakte und/oder deren Eigenschaftsausprägungen,</br>" + "von Kontaktlisten</br> "
					+ "<p>" + " </p>" + "	</section>" + "</article>");

	public ContentWidget(final Nutzer nutzer) {

		ft1.setWidget(0, 1, html1);

		vpanel.add(ft1);
		this.add(vpanel);

	}
}
