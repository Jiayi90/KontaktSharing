package de.hdm.KontaktSharing.client.page;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimplePanel;

import de.hdm.KontaktSharing.client.widget.NavigationWidget;
import de.hdm.KontaktSharing.client.widget.SmallButton;
import de.hdm.KontaktSharing.shared.bo.Kontaktliste;
import de.hdm.KontaktSharing.shared.bo.Teilhaberschaft;
import de.hdm.KontaktSharing.shared.bo.TeilhaberschaftKontaktliste;

public class ListContactListPage extends CommonPage {
	FlexTable table;
	Vector<Kontaktliste> myKontakte = new Vector<Kontaktliste>();
	Vector<TeilhaberschaftKontaktliste> sharedListen = new Vector<TeilhaberschaftKontaktliste>();
	SimplePanel panel = new SimplePanel();

	@Override
	protected String getHeadlineText() {
		return "Kontaktlisten";
	}

	@Override
	protected void run() {
		PushButton createContactButton = new PushButton(new Image("icons/icons8-plus-48.png"));
		createContactButton.getElement().setId("createContactButton");
		createContactButton.addClickHandler(new CreateContactListButtonClickHandler());
		this.add(createContactButton);

		this.kontaktSharingAdmin.getAllKontaktlistenWithUserCountForNutzer(getLoggedInId(),
				new AsyncCallback<Vector<Kontaktliste>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("error getAllKontaktlistenWithUserCountForNutzern");
					}

					@Override
					public void onSuccess(Vector<Kontaktliste> result) {
						myKontakte = result;
						renderTable();
					}

				});

		this.kontaktSharingAdmin.getSharedKontaktlistenForUser(this.getLoggedInId(),
				new AsyncCallback<Vector<TeilhaberschaftKontaktliste>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("error getSharedKontaktlistenForUser");
					}

					@Override
					public void onSuccess(Vector<TeilhaberschaftKontaktliste> result) {
						sharedListen = result;
						renderTable();
					}

				});

	}

	private void renderTable() {
		panel.clear();

		panel.getElement().setClassName("scroll-panel");

		table = new FlexTable();
		table.getElement().setId("kontakt-list");
		table.getElement().setClassName("list");
		table.setText(0, 3, "Name");
		table.setText(0, 4, "Anzahl");

		Vector<Kontaktliste> allListen = new Vector<Kontaktliste>();
		allListen.addAll(this.myKontakte);
		for (TeilhaberschaftKontaktliste th : sharedListen) {
			allListen.add(th.getListe());
		}

		for (final Kontaktliste liste : allListen) {
			int row = table.getRowCount();

			SmallButton deleteButton = new SmallButton("icons/delete.png");
			deleteButton.addClickHandler(new DeleteKontaktliste(liste));

			SmallButton editButton = new SmallButton("icons/edit.png");
			editButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					NavigationWidget.navigateTo(new EditContactList(liste));
				}

			});
			if (!liste.isWasShared()) {
				table.setWidget(row, 0, editButton);
				table.setWidget(row, 1, deleteButton);
				if (liste.isShared()) {
					SmallButton shareButton = new SmallButton("icons/shared.png");
					shareButton.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							NavigationWidget.navigateTo(new EditShareContactList(liste.getTeilhaberschaftId()));
						}

					});

					table.setWidget(row, 2, shareButton);

				} else {
					SmallButton shareButton = new SmallButton("icons/share.png");
					shareButton.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							NavigationWidget.navigateTo(new ShareContactList(liste));
						}

					});
					table.setWidget(row, 2, shareButton);

				}
			} else {
				SmallButton shareButton = new SmallButton("icons/unshare.png");
				shareButton.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						NavigationWidget.navigateTo(new ShareContactList(liste));
					}

				});
				table.setWidget(row, 2, shareButton);

			}

			Label nameLabel = new Label(liste.getKontaktlistenname());
			nameLabel.getElement().setAttribute("class", "name-label");
			nameLabel.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					NavigationWidget.navigateTo(new DetailKontaktliste(liste));

				}

			});

			table.setWidget(row, 3, nameLabel);
			table.setText(row, 4, "" + liste.getKontakte().size());
		}

		panel.add(table);
		page.add(panel);
	}

	class CreateContactListButtonClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			NavigationWidget.navigateTo(new CreateContactList());
		}
	}

	class DeleteKontaktliste implements ClickHandler {
		Kontaktliste liste;

		public DeleteKontaktliste(Kontaktliste liste) {
			this.liste = liste;
		}

		@Override
		public void onClick(ClickEvent event) {
			boolean delete = Window.confirm("Soll die Kontaktliste " + liste.getKontaktlistenname()
					+ " geloescht werden? Diese Aktion ist nicht rueckgaengig zu machen");
			if (delete) {
				page.kontaktSharingAdmin.delete(liste, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Beim loeschen ist ein Fehler aufgetretten");
					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Liste mit id " + liste.getId() + " wurde geloescht");
						table.removeAllRows();
						page.kontaktSharingAdmin.getAllKontaktlistenWithUserCountForNutzer(getLoggedInId(),
								new AsyncCallback<Vector<Kontaktliste>>() {

									@Override
									public void onFailure(Throwable caught) {
										Window.alert("error getAllKontaktlistenWithUserCountForNutzer");
									}

									@Override
									public void onSuccess(Vector<Kontaktliste> result) {
										myKontakte = result;
										renderTable();
									}

								});
					}
				});
			}
		}

	}

}