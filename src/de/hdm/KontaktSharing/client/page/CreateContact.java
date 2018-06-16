package de.hdm.KontaktSharing.client.page;

import java.text.ParseException;
import com.google.gwt.i18n.client.DateTimeFormat;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import de.hdm.KontaktSharing.client.ClientsideSettings;
import de.hdm.KontaktSharing.client.widget.NavigationWidget;
import de.hdm.KontaktSharing.client.widget.SmallButton;
import de.hdm.KontaktSharing.shared.bo.Eigenschaft;
import de.hdm.KontaktSharing.shared.bo.Eigenschaftauspraegung;
import de.hdm.KontaktSharing.shared.bo.Kontakt;
import de.hdm.KontaktSharing.shared.bo.Typ;

public class CreateContact extends CommonPage {


	@Override
	protected String getHeadlineText() {
		return "Neuer Kontakt erstellen";
	}

	@Override
	protected void run() {
		FlexTable table = new FlexTable();
		ClientsideSettings.getKontaktSharingAdministration().getAllEigenschaft(new RenderForm(table));
		this.add(table);
		Button saveButton = new Button("Neuen Kontakt erstellen");
		saveButton.addClickHandler(new SaveEigenschaftenauspraegung(table));
		this.add(saveButton);
	}

	class RenderForm implements AsyncCallback<Vector<Eigenschaft>> {
		FlexTable table;

		RenderForm(FlexTable table) {
			this.table = table;
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSuccess(Vector<Eigenschaft> eigenschaften) {
			eigenschaften.stream().forEach(eigenschaft -> createRow(eigenschaft));
		}

		private void createRow(Eigenschaft eigenschaft) {
			if (eigenschaft.isMehrfach()) {
				createMultipleRow(eigenschaft);
			} else {
				createSimpleRow(eigenschaft);
			}
		}

		private void createSimpleRow(Eigenschaft eigenschaft) {
			int row = table.getRowCount();
			table.setText(row, 0, eigenschaft.getBezeichnung());
			table.getCellFormatter().getElement(row, 0).setAttribute("data-eigenschaftid", String.valueOf(eigenschaft.getId()));
			table.getCellFormatter().getElement(row, 0).setAttribute("data-eigenschafttyp", String.valueOf(eigenschaft.getTyp()));
			table.setWidget(row, 1, getWidgetForTyp(eigenschaft.getTyp()));
		}

		private void createMultipleRow(Eigenschaft eigenschaft) {
			int row = table.getRowCount();
			table.setText(row, 0, eigenschaft.getBezeichnung());
			table.getCellFormatter().getElement(row, 0).setAttribute("data-eigenschaftid", String.valueOf(eigenschaft.getId()));
			table.getCellFormatter().getElement(row, 0).setAttribute("data-eigenschafttyp", String.valueOf(eigenschaft.getTyp()));
			table.setWidget(row, 1, getWidgetForTyp(eigenschaft.getTyp()));
			SmallButton addButton = new SmallButton("icons/plus-small.png");
			addButton.addClickHandler(new AddMultiple(table, eigenschaft));
			table.setWidget(row, 2, addButton);
		}

		class AddMultiple implements ClickHandler {
			private FlexTable table;
			private Eigenschaft eigenschaft;

			AddMultiple(FlexTable table, Eigenschaft eigenschaft) {
				this.table = table;
				this.eigenschaft = eigenschaft;
			}

			@Override
			public void onClick(ClickEvent event) {
				boolean foundFlag = false;
				for (int row = 0; row < table.getRowCount(); row++) {
					String bezeichnung = table.getText(row, 0);
					if (bezeichnung != null && !bezeichnung.isEmpty()) {
						if (bezeichnung.equals(eigenschaft.getBezeichnung())) {
							foundFlag = true;
						} else if (foundFlag) {
							table.insertRow(row);
							insertRow(row);
							return;
						}
					}
				}
				insertRow(table.getRowCount());
			}

			private void insertRow(int row) {
				table.setWidget(row, 1, getWidgetForTyp(eigenschaft.getTyp()));
				SmallButton removeButton = new SmallButton("icons/minus-small.png");
				removeButton.addClickHandler(new RemoveMultiple(table, removeButton));
				table.setWidget(row, 2, removeButton);
			}

		}
		
		class RemoveMultiple implements ClickHandler {
			FlexTable table;
			SmallButton button;
			RemoveMultiple(FlexTable table, SmallButton button) {
				this.table = table;
				this.button = button;
			}
			
			@Override
			public void onClick(ClickEvent event) {
				int index = this.table.getCellForEvent(event).getRowIndex();
				table.removeRow(index);
			}
			
		}

		private Widget getWidgetForTyp(Typ typ) {
			if (typ == Typ.STRING) {
				return new TextBox();
			} else if (typ == Typ.DATE) {
				return new TextBox();
			} else if (typ == Typ.INT) {
				return new TextBox();
			} else {
				return new TextBox();
			}
		}

	}
	
	
	class SaveEigenschaftenauspraegung implements ClickHandler {
		FlexTable table;
		SaveEigenschaftenauspraegung(FlexTable table) {
			this.table = table;
		}

		@Override
		public void onClick(ClickEvent event) {
			NavigableMap<Integer, Eigenschaft> eigenschaften= new TreeMap<Integer, Eigenschaft>();
			for(int row = 0; row < table.getRowCount(); row ++) {
				for(int col = 0; col < table.getCellCount(row); col++) {
					String idRaw = table.getCellFormatter().getElement(row, 0).getAttribute("data-eigenschaftid");
					String typRaw = table.getCellFormatter().getElement(row, 0).getAttribute("data-eigenschafttyp");
					if(idRaw != null && !idRaw.isEmpty()) {
						int id = Integer.parseInt(idRaw);
						Eigenschaft eigenschaft = new Eigenschaft();
						eigenschaft.setId(id);
						eigenschaft.setTyp(typRaw);
						eigenschaften.put(row, eigenschaft);
					}
				}
			}
			
			Vector<Eigenschaftauspraegung> auspraegungen = new Vector<Eigenschaftauspraegung>();
			for(Map.Entry<Integer, Eigenschaft> current :eigenschaften.entrySet()) {
				int fromRow = current.getKey();
				Eigenschaft eigenschaft = current.getValue();
			    Map.Entry<Integer, Eigenschaft> next = eigenschaften.higherEntry(current.getKey());
			    
			    int toRow = (next == null) ? table.getRowCount() : next.getKey();
			    
			    for(int row = fromRow; row < toRow; row ++) {
			    	Widget widget = table.getWidget(row, 1);
			    	Typ typ = eigenschaft.getTyp();
					Eigenschaftauspraegung eigausp = new Eigenschaftauspraegung();
					eigausp.setIdEigenschaft(eigenschaft.getId());
			    	if(typ == Typ.DATE) {
			    		String value = ((TextBox) widget).getValue();		    			
			    		if(value == null || value.isEmpty())
			    			continue;
			    		DateTimeFormat simpleDateFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
	
						eigausp.setDatum(simpleDateFormat.parse(value));
			    	} else if (typ == Typ.INT) {
			    		String value = ((TextBox) widget).getValue();
			    		if(value == null || value.isEmpty())
			    			continue;
			    		eigausp.setZahl(Integer.valueOf(value));
			    	} else {
			    		String value = ((TextBox) widget).getValue();
			    		if(value == null || value.isEmpty())
			    			continue;
			    		eigausp.setText(value);
			    	}
			    	auspraegungen.add(eigausp);
			    }
			}
			if(auspraegungen.stream().filter(auspraegung -> auspraegung.getIdEigenschaft() == 1).findFirst().isPresent()) {
				Kontakt kontakt = new Kontakt();
				kontakt.setIdNutzer(1);
				ClientsideSettings.getKontaktSharingAdministration().createKontakt(kontakt, new AsyncCallback<Kontakt>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Kontakt newKontakt) {
						auspraegungen.stream().forEach(auspraegung -> auspraegung.setIdKontakt(newKontakt.getId()));
						ClientsideSettings.getKontaktSharingAdministration().createEigenschaftauspraegungen(auspraegungen, new KontaktCreated());	
					}
				});	
			} else {
				Window.alert("Bitte geben Sie dem Kontakt einen Name");
			}
		} 
	}
}
class KontaktCreated implements AsyncCallback<Void> {

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSuccess(Void result) {
		NavigationWidget.navigateTo(new ListContactsPage());
	}

}
