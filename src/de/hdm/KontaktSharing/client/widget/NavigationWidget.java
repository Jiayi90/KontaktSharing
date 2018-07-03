package de.hdm.KontaktSharing.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.SimplePanel;

import de.hdm.KontaktSharing.client.page.CommonPage;
import de.hdm.KontaktSharing.client.page.ListContactListPage;
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

	public static NavigationWidget getNavigationWidget(Panel newContainer, CommonPage newContentPanel, boolean show) {
		singelton = new NavigationWidget(container, contentPanel, show);
		container = newContainer;
		contentPanel = newContentPanel;
		return singelton;
	}
	
	public static void navigateTo(CommonPage newContentPanel) {
		container.remove(contentPanel);
		contentPanel = newContentPanel;
		container.add(newContentPanel);
	}
	
	private NavigationWidget(Panel container, CommonPage contentPanel, boolean show) {
		this.getElement().setId("navigator");
		if(show) {
			
			final Button contact = new Button("Kontakt", new ClickDisplayContactCallback());
			contact.getElement().setClassName("navi-button");
			
			final Button contactList = new Button("Kontaktliste", new ClickDisplayContactListCallback());
			contactList.getElement().setClassName("navi-button");
			
			this.add(contact);
			this.add(contactList);
			
		}
		
		final SimplePanel panel = new SimplePanel();
		panel.getElement().setId("expander");
		

		this.add(panel);
	}

	
	class ClickDisplayContactCallback implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			NavigationWidget.navigateTo(new ListContactsPage());
		}	
	}
	
	class ClickDisplayContactListCallback implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			NavigationWidget.navigateTo(new ListContactListPage());
		}
		
	}
}
