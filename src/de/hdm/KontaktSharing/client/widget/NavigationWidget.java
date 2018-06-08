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

/**
 * Formular für die Darstellung des selektierten Kontakt
 * @author jiayi.li
 *
 */

public class NavigationWidget extends VerticalPanel{
	private static NavigationWidget singelton;
	private static Panel container;
	private static CommonPage contentPanel;
	
	public static NavigationWidget getNavigationWidget(Panel newContainer, CommonPage newContentPanel) {
		singelton = new NavigationWidget(container, contentPanel);
		container = newContainer;
		contentPanel = newContentPanel;
		return singelton;
	}
	
	public static void navigateTo(CommonPage newContentPanel) {
		container.remove(contentPanel);
		contentPanel = newContentPanel;
		container.add(newContentPanel);
	}
	
	private NavigationWidget(Panel container, CommonPage contentPanel) {
		this.getElement().setId("navigator");
		final Button contact = new Button("Kontakt", new ClickDisplayContactCallback());
		final Button contactList = new Button("Kontaktliste");
		this.add(contact);
		this.add(contactList);
	}
	
	class ClickDisplayContactCallback implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			NavigationWidget.navigateTo(new ListContactsPage());
		}	
	}
}
