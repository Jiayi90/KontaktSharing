package de.hdm.KontaktSharing.shared.report;

/**
 * <p>
 * Diese Klasse wird benötigt, um auf dem Client die ihm vom Server zur
 * Verfügung gestellten <code>Report</code>-Objekte in ein menschenlesbares
 * Format zu überführen.
 * </p>
 * <p>
 * Das Zielformat kann prinzipiell beliebig sein. Methoden zum Auslesen der in
 * das Zielformat überführten Information wird den Subklassen überlassen. In
 * dieser Klasse werden die Signaturen der Methoden deklariert, die für die
 * Prozessierung der Quellinformation zuständig sind.
 * </p>
 * 
 * @author Thies, Ahmed
 */

public abstract class ReportWriter {
	
	/**
	   * Übersetzen eines <code>AllKontaktByNutzer</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu übersetzende Report
	   */

	public abstract void process(AllKontaktByNutzer r);

	/**
	   * Übersetzen eines <code>AllKontaktReprt</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu übersetzende Report
	   */
	public abstract void process(AllKontaktReport r);
	
	
	/**
	   * Übersetzen eines <code>ShareKontaktByNutzer</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu übersetzende Report
	   */
	
	public abstract void process(ShareKontaktByNutzer r); 
		
	
	/**
	   * Übersetzen eines <code>SelectedEigenschaftauspraegungByNutzer</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu übersetzende Report
	   */
	public abstract void process(SelectedEigenschaftauspraegungByNutzer r);
	

}
