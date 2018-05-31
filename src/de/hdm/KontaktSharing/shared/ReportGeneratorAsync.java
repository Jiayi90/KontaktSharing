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
	void init(AsyncCallback<Void> callback);
	void createAllKontaktByNutzer(Nutzer n, Eigenschaft e, AsyncCallback<AllKontaktByNutzer> callback);
	
	void createSelectedEigenschaftauspraegungByNutzer(Nutzer n, Eigenschaftauspraegung ea, AsyncCallback<SelectedEigenschaftauspraegungByNutzer> callback);

	void createShareKontaktByNutzer(Nutzer n, Kontakt k, AsyncCallback<ShareKontaktByNutzer> callback);
	void createAllKontaktReport(Kontakt k, AsyncCallback<AllKontaktReport> callback);
}
