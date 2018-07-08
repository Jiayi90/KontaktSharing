package de.hdm.KontaktSharing.client.widget;

import java.awt.Checkbox;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.KontaktSharing.shared.bo.Eigenschaft;
import de.hdm.KontaktSharing.shared.bo.Eigenschaftauspraegung;

public class ChooseEigenschaftsauspraegungenWidget extends VerticalPanel {
	
	List<CheckBox> all = new ArrayList<CheckBox>();
	List<Integer> selected = new ArrayList<Integer>();
	
	public void run(final Vector<Eigenschaftauspraegung> eps, Vector<Eigenschaft> es) {
		FlexTable table = new FlexTable();
		table.getElement().addClassName("detail-kontakt auspraegungen");
		CheckBox chooseAll = new CheckBox("Alles auswaehlen");
		chooseAll.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				boolean checked = ((CheckBox) event.getSource()).getValue();
				for(CheckBox box: all) {
					box.setValue(checked);
				}
				
				if(checked) {
					for(Eigenschaftauspraegung ep: eps) {
						selected.add(ep.getId());
					}
				} else {
					selected.clear();
				}
			}
		});
		
		table.setWidget(0, 1, chooseAll);
		for(Eigenschaft e: es) {
			int row = table.getRowCount();
			
			VerticalPanel panel = new VerticalPanel();
			boolean addEigenschaft = false;
			for(final Eigenschaftauspraegung ep: eps) {
				if(ep.getIdEigenschaft() == e.getId()) {
					addEigenschaft = true;
					CheckBox box = new CheckBox(ep.getValue(e.getTyp()));
					box.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							boolean checked = ((CheckBox) event.getSource()).getValue();
							if(checked) {
								selected.add(ep.getId());
							} else {
								selected.remove(new Integer(ep.getId()));
							}
						}
					});
					all.add(box);
					panel.add(box);
				}
			}
			if(addEigenschaft) {
				table.setText(row, 0, e.getBezeichnung());
				table.setWidget(row, 1, panel);
			}
		}
		Label headline = new Label("Eigenschaften auswaehlen");
		headline.getElement().setClassName("widget-headline");
		this.add(headline);
		this.add(table);
	}
	
	public List<Integer> getSelected() {
		return selected;
	}
}
