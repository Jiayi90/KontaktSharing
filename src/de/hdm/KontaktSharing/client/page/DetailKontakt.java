package de.hdm.KontaktSharing.client.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.KontaktSharing.client.widget.NaviBackWidget;
import de.hdm.KontaktSharing.client.widget.NavigationWidget;
import de.hdm.KontaktSharing.client.widget.SmallButton;
import de.hdm.KontaktSharing.shared.bo.Eigenschaft;
import de.hdm.KontaktSharing.shared.bo.Eigenschaftauspraegung;
import de.hdm.KontaktSharing.shared.bo.Kontakt;
import de.hdm.KontaktSharing.shared.bo.Kontaktliste;

public class DetailKontakt extends CommonPage {
	
	Kontakt kontakt;
	String kontaktName = "";
	Kontaktliste liste;
	
	public DetailKontakt(Kontakt kontakt) {
		this.kontakt = kontakt;
		createBackButton();
	}
	
	public DetailKontakt(Kontakt kontakt, Kontaktliste liste) {
		this.liste = liste;
		this.kontakt = kontakt;
		createBackButton();
	}
	
	private void createBackButton() {
		FocusPanel wrapper = new FocusPanel();
		
		HorizontalPanel panel = new HorizontalPanel();
		
		panel.getElement().setClassName("navibutton");
		SmallButton backButton = new SmallButton("icons/back.png");
		panel.add(backButton);
		panel.add(new Label("Zurueck"));
		wrapper.add(panel);
		
		wrapper.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if(liste != null) {
					NavigationWidget.navigateTo(new DetailKontaktliste(liste));
				} else {
					NavigationWidget.navigateTo(new ListContactsPage());
				}
				
			}
			
		});
		
		this.add(wrapper);
	}

	@Override
	protected String getHeadlineText() {
		return kontaktName;
	}

	@Override
	protected void run() {
	
	
		NaviBackWidget back = new NaviBackWidget("Zurueck", new ListContactsPage());
		this.add(back);
	
		
		this.kontaktSharingAdmin.getAllEigenschaft(new AsyncCallback<Vector<Eigenschaft>>() {

			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(final Vector<Eigenschaft> eigenschaften) {
				if(kontakt.getEigenschaftauspraegung() != null) {
					render(eigenschaften, kontakt.getEigenschaftauspraegung());	
				} else {
					kontaktSharingAdmin.getAllEigenschaftauspraegungByKontakt(kontakt, new AsyncCallback<Vector<Eigenschaftauspraegung>>() {
	
						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}
	
						@Override
						public void onSuccess(Vector<Eigenschaftauspraegung> auspraegungen) {
							render(eigenschaften, auspraegungen);						
						}
					});
				}
			}
			
		});

	}
	
	private void render(Vector<Eigenschaft> eigenschaften, Vector<Eigenschaftauspraegung> auspraegungen) {
		FlexTable table = new FlexTable();
		table.getElement().setClassName("one-line detail-kontakt");
		for(final Eigenschaft eigenschaft: eigenschaften) {
			List<Eigenschaftauspraegung> displayAuspraegungen = new ArrayList<Eigenschaftauspraegung>();
			for(final Eigenschaftauspraegung auspraegung: auspraegungen) {
				if(auspraegung.getIdEigenschaft() == 1) {
					updateHeadline(auspraegung.getText());
				} else if(eigenschaft.getId() == auspraegung.getIdEigenschaft()) {
					displayAuspraegungen.add(auspraegung);
				}	
			}
			printEigenschaft(eigenschaft, displayAuspraegungen, table);
		}
		add(table);
	}
	
	private void printEigenschaft(Eigenschaft eigenschaft, List<Eigenschaftauspraegung> auspraegungen, FlexTable table) {
		if(auspraegungen.size() != 0 && eigenschaft.getId() != 1) {
			int row = table.getRowCount();
			
			table.setText(row, 0, eigenschaft.getBezeichnung());
			
			VerticalPanel panel = new VerticalPanel();
			for(Eigenschaftauspraegung auspraegung: auspraegungen) {
				panel.add(new Label(auspraegung.getValue(eigenschaft.getTyp())));
			}
			table.setWidget(row, 1, panel);
		}

		
	}

}
