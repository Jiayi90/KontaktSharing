package de.hdm.KontaktSharing.client.page;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Vector;

import com.google.appengine.repackaged.com.google.api.client.util.ArrayMap;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.KontaktSharing.client.ClientsideSettings;
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
		saveButton.addClickHandler(new SaveKontakt(table));
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
				return new DatePicker();
			} else if (typ == Typ.INT) {
				return new TextBox();
			} else {
				return new TextBox();
			}
		}

	}
	
	class SaveKontakt implements ClickHandler {
		FlexTable table;
		public SaveKontakt(FlexTable table) {
			this.table = table;
		}
		@Override
		public void onClick(ClickEvent event) {
			Kontakt kontakt = new Kontakt();
			kontakt.setIdNutzer(1);
			ClientsideSettings.getKontaktSharingAdministration().createKontakt(kontakt, new SaveEigenschaftenauspraegung(table));
		}
	}
	
	class SaveEigenschaftenauspraegung implements AsyncCallback<Kontakt> {
		FlexTable table;
		SaveEigenschaftenauspraegung(FlexTable table) {
			this.table = table;
		}
		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Kontakt kontakt) {
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
					eigausp.setIdKontakt(kontakt.getId());
			    	if(typ == Typ.DATE) {
			    		eigausp.setDatum(((DatePicker) widget).getValue());
			    	} else if (typ == Typ.INT) {
			    		eigausp.setZahl(Integer.valueOf(((TextBox) widget).getValue()));
			    	} else {
			    		eigausp.setText(((TextBox) widget).getValue());
			    	}
			    	ClientsideSettings.getKontaktSharingAdministration().createEigenschaftauspraegung(eigausp, new KontaktCreated());
			    }
			}
		}
	}
}
class KontaktCreated implements AsyncCallback<Eigenschaftauspraegung> {

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSuccess(Eigenschaftauspraegung result) {
		// TODO Auto-generated method stub
		
	}
	
}
