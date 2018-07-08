package de.hdm.KontaktSharing.shared.report;

import java.io.Serializable;
import java.util.Vector;

import de.hdm.KontaktSharing.shared.report.Paragraph;
import de.hdm.KontaktSharing.shared.report.SimpleParagraph;

/**
 * Diese Klasse stellt eine Menge einzelner Absätze dar. 
 * Diese werden als Unterabschnitte in einem Vector abgelegt verwaltet.
 * @author Samina, Thies
 *
 */

public class CompositeParagraph extends Paragraph implements Serializable{
	
	 /**
	   * 
	   */
	  private static final long serialVersionUID = 1L;

	  /**
	   * Speicherort der Unterabschnitte.
	   */
	  private Vector<SimpleParagraph> subParagraphs = new Vector<SimpleParagraph>();

	  /**
	   * Einen Unterabschnitt hinzufügen.
	   * 
	   * @param p der hinzuzufügende Unterabschnitt.
	   */
	  public void addSubParagraph(SimpleParagraph p) {
	    this.subParagraphs.addElement(p);
	  }

	  /**
	   * Einen Unterabschnitt entfernen.
	   * 
	   * @param p der zu entfernende Unterabschnitt.
	   */
	  public void removeSubParagraph(SimpleParagraph p) {
	    this.subParagraphs.removeElement(p);
	  }

	  /**
	   * Auslesen sämtlicher Unterabschnitte.
	   * 
	   * @return <code>Vector</code>, der sämtliche Unterabschnitte enthält.
	   */
	  public Vector<SimpleParagraph> getSubParagraphs() {
	    return this.subParagraphs;
	  }

	  /**
	   * Auslesen der Anzahl der Unterabschnitte.
	   * 
	   * @return Anzahl der Unterabschnitte
	   */
	  public int getNumParagraphs() {
	    return this.subParagraphs.size();
	  }

	  /**
	   * Auslesen eines einzelnen Unterabschnitts.
	   * 
	   * @param i der Index des gewuenschten Unterabschnitts 
	   * 
	   * @return der gewuennschte Unterabschnitt.
	   */
	  public SimpleParagraph getParagraphAt(int i) {
	    return this.subParagraphs.elementAt(i);
	  }

	  /**
	   * Umwandeln eines <code>CompositeParagraph</code> in einen
	   * <code>String</code>.
	   */
	  @Override
	public String toString() {
	    /*
	     * Wir legen einen leeren Buffer an, in den wir sukzessive sämtliche
	     * String-Repräsentationen der Unterabschnitte eintragen.
	     */
	    StringBuffer result = new StringBuffer();

	    // Schleife über alle Unterabschnitte
	    for (int i = 0; i < this.subParagraphs.size(); i++) {
	      SimpleParagraph p = this.subParagraphs.elementAt(i);

	      /*
	       * den jew. Unterabschnitt in einen String wandeln und an den Buffer hängen.
	       */
	      result.append(p.toString() + "\n");
	    }

	    /*
	     * Schlie�lichh wird der Buffer in einen String umgewandelt und
	     * zurueckgegeben.
	     */
	    return result.toString();
	  }


}
