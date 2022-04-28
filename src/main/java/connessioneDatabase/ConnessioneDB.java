package connessioneDatabase;

import prenotazione.Prenotazione;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.TreeSet;

import javax.swing.JOptionPane;

/**
 * Classe che permette il collegamento ad database MySql.
 * Al suo interno sono presenti metodi per scrivere e recuperare dati dal database.
 */
public class ConnessioneDB {
    // nome utente per acccedere al database;
    static private String user = "root";
    // password utente per acccedere al database;
    static private String password = "root";
    // nome del database a cui ci si deve collegare;
    static private String dbName = "JADEPROVA";
    // nome dell'host a cui ci si collega ( di solito "localhost" è uguale per tutti)
    static private String host = "localhost";
    // numero della porta (di solito 2206 è uguale per tutti)
    static private int port = 3306;
    // composizione della stringa per la connesione al database
    static private String url = "jdbc:mysql://" + host + ":" + port + "/" +dbName;
    // Oggetto connesione che verrà inizializzato con la connessione ad database
    static private Connection conn = null;

    /**
     * Metodo che permette di stabilire la connesione con il database.
     * @return conn oggetto di tipo Connection contenente la connesione con il database.
     */
    public static Connection connectToDB() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);

        } catch(Exception e) {
            System.out.println("database.LoadDB.connectToDB - Eccezione : " + e.toString());
        }

        return conn;
    }

    /**
     * Metodo che permette di recuperare tutte le prenotazioni dal database.
     * @param conn parametro contenente la connessione ottenuta con il database.
     * @return listaPrenotazioni la lista contenente tutte le prenotazioni recuperate dal database.
     */
    public static TreeSet<Prenotazione> LoadPrenotazioni(Connection conn) {

        // Inizializzo un TreeSet che verrà popolato con le prenotazioni presenti nel database.
        TreeSet<Prenotazione> listaPrenotazioni = new TreeSet<Prenotazione>();
        // Inizializzo un oggetto di tipo Prenotazione che conterrà i dati della singola prenotazione da aggiungere alla lista.
        Prenotazione pr;

        try {
            // Genero la query per recuperare i dati dal database delle prenotazioni.
            Statement st = conn.createStatement();
            String query = "SELECT * FROM PRENOTAZIONE";
            ResultSet rst = st.executeQuery(query);

            while (rst.next()) {
                // Per ogni prenotazioni recupero ogni dato.
                int ID = rst.getInt("ID");
                String nome = rst.getString("NOME");
                String corso = rst.getString("CORSO");

                // Istanzio una nuova prenotazione con i dati appena recuperati.
                pr = new Prenotazione(ID, nome, corso);

                // Aggiungo la prenotazione alla lista.
                listaPrenotazioni.add(pr);
            }

        } catch (Exception ex) {
            // In caso di eccezioni, stampo un messaggio sulla console.
            System.out.println("connessioneDatabase.ConnessioneDB.loadPrenotazioni Eccezione : " + ex.toString());
        }
        // Restituisco la lista con tutte le prenotazioni recuperate.
        return listaPrenotazioni;
    }
}