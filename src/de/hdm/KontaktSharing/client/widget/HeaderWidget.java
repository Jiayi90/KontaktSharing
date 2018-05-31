package de.hdm.KontaktSharing.client.widget;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class HeaderWidget extends HorizontalPanel{
	public HeaderWidget() {
		this.getElement().setId("header");
		this.add(new HTMLPanel("h1", "Kontakt Sharing"));
	}
}
