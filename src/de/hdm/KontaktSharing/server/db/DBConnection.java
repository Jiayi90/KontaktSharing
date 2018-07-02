package de.hdm.KontaktSharing.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.appengine.api.utils.SystemProperty;


/**
 * Verwalten einer Verbindung zur Datenbank.
 * <p>
 * <b>Vorteil:</b> Sehr einfacher Verbindungsaufbau zur Datenbank.
 * <p>
 * <b>Nachteil:</b> Durch die Singleton-Eigenschaft der Klasse kann nur auf eine
 * fest vorgegebene Datenbank zugegriffen werden.
 * <p>
 * In der Praxis kommen die meisten Anwendungen mit einer einzigen Datenbank
 * aus. Eine flexiblere Variante f�r mehrere gleichzeitige
 * Datenbank-Verbindungen w�re sicherlich leistungsf�higer. Dies w�rde
 * allerdings den Rahmen dieses Projekts sprengen bzw. die Software unn�tig
 * verkomplizieren, da dies f�r diesen Anwendungsfall nicht erforderlich ist.
 * 
 * @author Thies
 */

public class DBConnection {
	
	/**
     * Die Klasse DBConnection wird nur einmal instantiiert. Man spricht hierbei
     * von einem sogenannten <b>Singleton</b>.
     * <p>
     * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
     * f�r s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
     * speichert die einzige Instanz dieser Klasse.
     * 
     */
    private static Connection con = null;

    /**
     * Die URL, mit deren Hilfe die Datenbank angesprochen wird. In einer
     * professionellen Applikation w�rde diese Zeichenkette aus einer
     * Konfigurationsdatei eingelesen oder �ber einen Parameter von au�en
     * mitgegeben, um bei einer Ver�nderung dieser URL nicht die gesamte
     * Software neu komilieren zu m�ssen.
     */
    private static String googleUrl = "jdbc:google:mysql://bankproject-154007:bankproject/bankproject?user=root&password=password";
    private static String localUrl = "jdbc:mysql://localhost:3306/kontaktsharing?user=root&password=password";

    /**
     * Diese statische Methode kann aufgrufen werden durch
     * <code>DBConnection.connection()</code>. Sie stellt die
     * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine
     * einzige Instanz von <code>DBConnection</code> existiert.
     * <p>
     * 
     * <b>Fazit:</b> DBConnection sollte nicht mittels <code>new</code>
     * instantiiert werden, sondern stets durch Aufruf dieser statischen
     * Methode.
     * <p>
     * 
     * <b>Nachteil:</b> Bei Zusammenbruch der Verbindung zur Datenbank - dies
     * kann z.B. durch ein unbeabsichtigtes Herunterfahren der Datenbank
     * ausgel�st werden - wird keine neue Verbindung aufgebaut, so dass die in
     * einem solchen Fall die gesamte Software neu zu starten ist. In einer
     * robusten L�sung w�rde man hier die Klasse dahingehend modifizieren, dass
     * bei einer nicht mehr funktionsf�higen Verbindung stets versucht w�rde,
     * eine neue Verbindung aufzubauen. Dies w�rde allerdings ebenfalls den
     * Rahmen dieses Projekts sprengen.
     * 
     * @return DAS <code>DBConncetion</code>-Objekt.
     * @see con
     */
    public static Connection connection() {
        // Wenn es bisher keine Conncetion zur DB gab, ...
        if (con == null) {
            String url = null;
                if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
                    // Load the class that provides the new
                    // "jdbc:google:mysql://" prefix.
                    try {
						Class.forName("com.mysql.jdbc.GoogleDriver");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new RuntimeException("Eins");
					}
                    url = googleUrl;
                } else {
                    // Local MySQL instance to use during development.
                    try {
						Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new RuntimeException("zwei");
					}
                    url = localUrl;
                }
                /*
                 * Dann erst kann uns der DriverManager eine Verbindung mit den
                 * oben in der Variable url angegebenen Verbindungsinformationen
                 * aufbauen.
                 * 
                 * Diese Verbindung wird dann in der statischen Variable con
                 * abgespeichert und fortan verwendet.
                 */
                try {
					con = DriverManager.getConnection(url);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException(e);
				}

        }

        // Zur�ckgegeben der Verbindung
        return con;
    }

}



