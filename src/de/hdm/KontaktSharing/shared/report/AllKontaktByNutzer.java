package de.hdm.KontaktSharing.shared.report;

import java.io.Serializable;
import java.util.Vector;

/**
 * Report, der alle Kontakte des Nutzers darstellt.
 * Die Klasse traegt keine weiteren Attribute- und Methoden-Implementierungen,
 * da alles Notwendige schon in den Superklassen vorliegt. Ihre Existenz ist 
 * dennoch wichtig, um bestimmte Typen von Reports deklarieren und mit ihnen 
 * objektorientiert umgehen zu können.
 * 
 * @author Thies, Tran
 */

public class AllKontaktByNutzer extends CompositeReport implements Serializable{

	private static final long serialVersionUID = 1L;

	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	public Paragraph getHeaderData() {
		// TODO Auto-generated method stub
		return null;
	}

	public Paragraph getImprint() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getCreated() {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<Row> getRows() {
		// TODO Auto-generated method stub
		return null;
	}
}
