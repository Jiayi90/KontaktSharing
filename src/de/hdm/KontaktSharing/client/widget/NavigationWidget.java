package de.hdm.KontaktSharing.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.KontaktSharing.client.page.CommonPage;
import de.hdm.KontaktSharing.client.page.ListContactsPage;

public class NavigationWidget extends VerticalPanel{
	public NavigationWidget(Panel container, CommonPage contentPanel) {
		this.getElement().setId("navigator");
		final Button contact = new Button("Kontakt", new ClickDisplayContactCallback(container, contentPanel));
		final Button contactList = new Button("Kontaktliste");
		this.add(contact);
		this.add(contactList);
	}
	
	class ClickDisplayContactCallback implements ClickHandler {
		Panel container;
		CommonPage contentPanel;
		public ClickDisplayContactCallback(Panel container, CommonPage contentPanel) {
			this.container = container;
			this.contentPanel = contentPanel;
		}
		@Override
		public void onClick(ClickEvent event) {
			container.remove(contentPanel);
			contentPanel = new ListContactsPage();
			container.add(contentPanel);
		}	
	}
}
