package de.hdm.KontaktSharing.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.KontaktSharing.shared.bo.*;
import de.hdm.KontaktSharing.shared.report.*;

/**
 * Das asynchrone Gegenstück des Interface {@link ReportGenerator}. Es wird
 * semiautomatisch durch das Google Plugin erstellt und gepflegt. Daher erfolgt
 * hier keine weitere Dokumentation. Für weitere Informationen siehe das
 * synchrone Interface {@link ReportGenerator}.
 * 
 * @author thies
 */

public interface ReportGeneratorAsync {

	void createAllKontaktByNutzer(Nutzer n, AsyncCallback<AllKontaktByNutzerReport> callback);

	
	
	void createSelectedEigenschaftauspraegungByNutzer(Nutzer n, Eigenschaftauspraegung ea, AsyncCallback<SelectedEigenschaftauspraegungByNutzerReport> callback);

	void createShareKontaktByNutzer(Nutzer n, Kontakt k, AsyncCallback<ShareKontaktByNutzerReport> callback);
	void createAllKontaktReport(Nutzer n, AsyncCallback<AllKontaktByNutzerReport> callback);

	

}
