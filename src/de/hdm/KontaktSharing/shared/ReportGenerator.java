package de.hdm.KontaktSharing.shared;

import de.hdm.KontaktSharing.shared.bo.*;
import de.hdm.KontaktSharing.shared.report.*;

public interface ReportGenerator {

	  public void init() throws IllegalArgumentException;

	  /**
	   * Erstellen eines <code>AllKontaktByNutzer</code>-Reports. Dieser
	   * Report-Typ stellt sämtliche Kontakte eines Nutzers dar.
	   * 
	   * @param Nutzer n und Eigenschaft e
	   * @return das fertige Reportobjekt
	   * @throws IllegalArgumentException
	   */
	  public abstract AllKontaktByNutzer createAllKontaktByNutzer(
	      Nutzer n, Eigenschaft e) throws IllegalArgumentException;

	  /**
	   * Erstellen eines <code>SelectedEigenschaftauspraegungByNutzer</code>-Reports.
	   * Dieser Report-Typ stellt sämtliche ausgewaehlte Eigenschaftauspraegungen eines Nutzers dar.
	   * 
	   * @param Nutzer n und Eigenschaftauspraegung ea
	   * @return das fertige Reportobjekt
	   * @throws IllegalArgumentException
	   */
	  public abstract SelectedEigenschaftauspraegungByNutzer createSelectedEigenschaftauspraegungByNutzer(
		      Nutzer n, Eigenschaftauspraegung ea) throws IllegalArgumentException;
	
	  /**
	   * Erstellen eines <code>SelectedEigenschaftauspraegungByNutzer</code>-Reports.
	   * Dieser Report-Typ stellt sämtliche geteilte Kontakte eines Nutzers dar.
	   * 
	   * @param Nutzer n und Kontakt k
	   * @return das fertige Reportobjekt
	   * @throws IllegalArgumentException
	   */
	  public abstract ShareKontaktByNutzer createShareKontaktByNutzer(
		      Nutzer n, Kontakt k) throws IllegalArgumentException;
	  
	  
}
