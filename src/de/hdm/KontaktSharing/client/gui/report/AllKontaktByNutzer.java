package de.hdm.KontaktSharing.client.gui.report;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.KontaktSharing.client.ClientsideSettings;
import de.hdm.KontaktSharing.shared.ReportGeneratorAsync;
import de.hdm.KontaktSharing.shared.bo.Nutzer;



public class AllKontaktByNutzer extends HorizontalPanel {
	
	ReportGeneratorAsync rpverwaltung = ClientsideSettings.getReportGenerator();
	
	private Button btAlleKontakte = new Button("Alle Kontakte anzeigen");
	
	private MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	private SuggestBox box = new SuggestBox(oracle);
	private Nutzer nutzerdb = null;
	
	private Label labelbNutzer = new Label("Kontakte von: ");

	private VerticalPanel vpanel = new VerticalPanel();

	public AllKontaktByNutzer() {
		vpanel.clear();
		Nutzer nutzer = new Nutzer();
		nutzer.setEmail(Cookies.getCookie("email"));
		nutzer.setId(Integer.parseInt(Cookies.getCookie("id")));
		this.add(vpanel);
	}
	
	public class FindNutzerByEmail implements AsyncCallback<Nutzer> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSuccess(Nutzer result) {
			nutzerdb = result;

		}
		
		class AlleKontaktByNutzerReport implements AsyncCallback<Vector<Nutzer>> {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Hoppla!" + caught.getMessage());

			}

			@Override
			public void onSuccess(Vector<Nutzer> result) {
				// TODO Auto-generated method stub
				for (Nutzer nutzer : result) {

					oracle.add(nutzer.getEmail());
				}
			}

		}

		class AllKontakteClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {

	}

		}
	}
}
