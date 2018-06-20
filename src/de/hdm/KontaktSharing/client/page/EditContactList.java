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
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.KontaktSharing.client.widget.NavigationWidget;
import de.hdm.KontaktSharing.shared.bo.Kontakt;
import de.hdm.KontaktSharing.shared.bo.Kontaktliste;

public class EditContactList extends CommonPage {
	
	Kontaktliste liste;
	TextBox listNameWidget;
	FlexTable table;
	Vector<CheckBox> checkBoxKontakte;
	public EditContactList(Kontaktliste liste) {
		this.liste = liste;
	}

	@Override
	protected String getHeadlineText() {
		return "Kontaktliste bearbeiten";
	}

	@Override
	protected void run() {
		checkBoxKontakte = new Vector<CheckBox>();
		table = new FlexTable();
		listNameWidget = new TextBox();
		listNameWidget.setValue(liste.getKontaktlistenname());
		table.setText(0, 0, "Name");
		table.setWidget(0, 1, listNameWidget);
		page.add(table);
		
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
					boolean select = liste.getKontakte().stream().filter(usedKontakt -> usedKontakt.getId() == kontakt.getId()).findFirst().isPresent();
					checkBox.setValue(select);
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
				page.kontaktSharingAdmin.updateKontaktlisteForLoggedinNutzer(liste.getId(), name, ids, new AsyncCallback<Void>() {

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