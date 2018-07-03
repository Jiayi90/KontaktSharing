package de.hdm.KontaktSharing.client.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import de.hdm.KontaktSharing.client.ClientsideSettings;
import de.hdm.KontaktSharing.client.widget.NavigationWidget;
import de.hdm.KontaktSharing.client.widget.SmallButton;
import de.hdm.KontaktSharing.shared.bo.Eigenschaft;
import de.hdm.KontaktSharing.shared.bo.Eigenschaftauspraegung;
import de.hdm.KontaktSharing.shared.bo.Kontakt;

public class EditContact extends CommonPage {
	
	private Kontakt kontakt;
	private FlexTable table;
	public EditContact(Kontakt kontakt) {
		this.kontakt = kontakt;
	}


	@Override
	protected String getHeadlineText() {
		return "Kontakt bearbeiten";
	}

	@Override
	protected void run() {
		table = new FlexTable();
		this.add(table);
		renderPage();
		
	}
	
	private void renderPage() {
		ClientsideSettings.getKontaktSharingAdministration().getAllEigenschaft(new AsyncCallback<Vector<Eigenschaft>>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(final Vector<Eigenschaft> eigenschaften) {
				ClientsideSettings.getKontaktSharingAdministration().getAllEigenschaftauspraegungByKontakt(kontakt, new AsyncCallback<Vector<Eigenschaftauspraegung>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Vector<Eigenschaftauspraegung> eigenschaftauspraegung) {
						for(final Eigenschaft eigenschaft: eigenschaften) {
							int row = table.getRowCount();
							table.setText(row, 0, eigenschaft.getBezeichnung());
							
							List<Eigenschaftauspraegung> currentAuspraegung = new ArrayList<Eigenschaftauspraegung>();
							for(Eigenschaftauspraegung ausp: eigenschaftauspraegung) {
								if(ausp.getIdEigenschaft() == eigenschaft.getId()) {
									currentAuspraegung.add(ausp);
								}
							}

							 
							for(int i = 0; i < currentAuspraegung.size(); i++) {
								final Eigenschaftauspraegung auspraegung = currentAuspraegung.get(i);
								table.setText(row, 1, auspraegung.getValue(eigenschaft.getTyp()));					
								
								SmallButton deleteButton = new SmallButton("icons/delete.png");
								deleteButton.addClickHandler(new ClickHandler() {

									@Override
									public void onClick(ClickEvent event) {
										ClientsideSettings.getKontaktSharingAdministration().delete(auspraegung, new ReloadPage());
									}
								});
								
								SmallButton editButton = new SmallButton("icons/edit.png");
								editButton.addClickHandler(new ClickHandler() {

									@Override
									public void onClick(ClickEvent event) {
										String newVal = Window.prompt(eigenschaft.getBezeichnung(), auspraegung.getValue(eigenschaft.getTyp()));
										auspraegung.setValue(eigenschaft.getTyp(), newVal);
										ClientsideSettings.getKontaktSharingAdministration().update(auspraegung, new ReloadPage());
									}
									
								});
								
								table.setWidget(row, 3, editButton);
								table.setWidget(row, 2, deleteButton);
								table.setWidget(row, 3, editButton);
								row++;
							}
							
							if(currentAuspraegung.size() == 0 || eigenschaft.isMehrfach()) {
								SmallButton createButton = new SmallButton("icons/icons8-plus-16.png");
								createButton.addClickHandler(new ClickHandler() {

									@Override
									public void onClick(ClickEvent event) {
										String value = Window.prompt("Bitte geben Sie einen Wert fuer "+eigenschaft.getBezeichnung()+" ein", "");
										Eigenschaftauspraegung auspraegung = new Eigenschaftauspraegung();
										auspraegung.setValue(eigenschaft.getTyp(), value);
										auspraegung.setIdKontakt(kontakt.getId());
										auspraegung.setIdEigenschaft(eigenschaft.getId());
										ClientsideSettings.getKontaktSharingAdministration().createEigenschaftauspraegung(auspraegung, new ReloadPageWithResponse());
									}
								});
								table.setWidget(row, 3, createButton);
							}
						}
					}
				});
			}	
		});
	}
	class ReloadPage implements AsyncCallback<Void>{
		
		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Void result) {
			NavigationWidget.navigateTo(new EditContact(kontakt));
			
		}
		
	}
	
class ReloadPageWithResponse implements AsyncCallback<Eigenschaftauspraegung>{
		
		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Eigenschaftauspraegung result) {
			NavigationWidget.navigateTo(new EditContact(kontakt));
			
		}
		
	}
}
