package connessioneDatabase;

import java.sql.Connection;

import java.sql.DriverManager;

import javax.swing.JOptionPane;

class Connessione_db {


    public Connection connetti() {

        Connection conn=null;
        String driver = "jdbc:mysql://localhost/databasejadeprova?user=root&password=123ITAde";

        //creo la connessione al database
        try
        {
            conn= DriverManager.getConnection(driver);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
        return conn;
    }
}