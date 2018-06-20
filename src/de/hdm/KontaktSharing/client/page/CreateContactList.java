package de.hdm.KontaktSharing.client.page;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.KontaktSharing.client.widget.NavigationWidget;
import de.hdm.KontaktSharing.shared.bo.Kontakt;

public class CreateContactList extends CommonPage {

	@Override
	protected String getHeadlineText() {
		return "Kontaktliste erstellen";
	}

	@Override
	protected void run() {
		TextBox listNameWidget = new TextBox();
		Vector<CheckBox> checkBoxKontakte = new Vector<CheckBox>();
		
		FlexTable table = new FlexTable();
		table.setText(0, 0, "Name der Liste");
		table.setWidget(0, 1, listNameWidget);
		this.add(table);
		FlexTable panel = new FlexTable();
		this.add(panel);
		this.kontaktSharingAdmin.getAllKontaktWithNameByLoggedInNutzer(new AsyncCallback<Vector<Kontakt>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vector<Kontakt> konakte) {
				konakte.stream().forEach(kontakt -> {
					CheckBox checkBox = new CheckBox(kontakt.getName());
					checkBox.getElement().setAttribute("data-idKontakt", String.valueOf(kontakt.getId()));
					panel.setWidget(panel.getRowCount(), 0, checkBox);
					checkBoxKontakte.add(checkBox);
				});
			}
		});
		Button saveButton = new Button("Neue Kontaktliste erstellen");
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				List<Integer> ids = checkBoxKontakte.stream()
				.filter(box -> box.getValue())
				.map(box -> box.getElement().getAttribute("data-idKontakt"))
				.map(id -> Integer.parseInt(id))
				.collect(Collectors.toList());
				String name = listNameWidget.getValue();
				page.kontaktSharingAdmin.createKontaktlisteForLoggedinNutzer(name, ids, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Es ist ein Fehler aufgetretten");
					}

					@Override
					public void onSuccess(Void result) {
						NavigationWidget.navigateTo(new ListContactListPage());
					}
					
				});
			}
		});
		this.add(saveButton);
	}

}
