package de.hdm.KontaktSharing.shared.report;

import java.io.Serializable;
import java.util.Vector;
/**
 * Report, der alle geteilte Kontakte des Nutzers darstellt.
 * Die Klasse traegt keine weiteren Attribute- und Methoden-Implementierungen,
 * da alles Notwendige schon in den Superklassen vorliegt. Ihre Existenz ist 
 * dennoch wichtig, um bestimmte Typen von Reports deklarieren und mit ihnen 
 * objektorientiert umgehen zu können.
 * 
 * @author Thies, Tran
 */

public class ShareKontaktByNutzerReport extends CompositeReport implements Serializable{

	  private static final long serialVersionUID = 1L;

	public Vector<Row> getRows() {
		
		return null;
	}

}
