package de.hdm.KontaktSharing.server;

import java.util.logging.Logger;

import de.hdm.KontaktSharing.shared.CommonSettings;

/**
 * 
 * Klasse mit Eigenschaften und Diensten, die für alle Server-seitigen Klassen
 * relevant sind.
 * 
 * @author Vi Quan, Thies
 *
 */

public class ServersideSettings extends CommonSettings {
	private static final String LOGGER_NAME = "KontaktSharing Server";
	private static final Logger log = Logger.getLogger(LOGGER_NAME);

	/**
	 * <p>
	 * Auslesen des applikationsweiten (Server-seitig!) zentralen Loggers.
	 * </p>
	 * 
	 * <h2>Anwendungsbeispiel:</h2> Zugriff auf den Logger herstellen durch:
	 * 
	 * <pre>
	 * Logger logger = ServerSideSettings.getLogger();
	 * </pre>
	 * 
	 * und dann Nachrichten schreiben etwa mittels
	 * 
	 * <pre>
	 * logger.severe(&quot;Sie sind nicht berechtigt, ...&quot;);
	 * </pre>
	 * 
	 * oder
	 * 
	 * <pre>
	 * logger.info(&quot;Lege neuen Nutzer an.&quot;);
	 * </pre>
	 * 
	 * <p>
	 * Bitte auf <em>angemessene Log Levels</em> achten! <em>severe</em> und
	 * <em>info</em> sind nur Beispiele.
	 * </p>
	 * 
	 * Weitere Infos siehe Dokumentation zu Java Logging.
	 * 
	 * @return die Logger-Instanz für die Server-Seite
	 * @author thies
	 */

	public static Logger getLogger() {
		return log;
	}
}
