package jade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;

import connessioneDatabase.ConnessioneDB;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import prenotazione.Prenotazione;

@SuppressWarnings("serial")
public class Cliente extends Agent {

    // Creo un TreeSet che conterrà le prenotazioni da inviare all'utente.
    TreeSet<Prenotazione> listaPrenotazioni = new TreeSet<Prenotazione>();
    // Creo il Frame che conterrà il pulsante che permetterà di inviare un messaggio da un agente ad un altro.
    JFrame frame;
    // Creo il pulsante da aggiungere al frame.
    JButton b1 = new JButton("Aggiungi prenotazione");

    /**
     * Metodo che inizializza il Frame e lo mostra all'utente.
     */
    void showGuy() {
        frame = new JFrame();
        frame.add(b1);
        frame.setSize(200, 50);
        frame.setVisible(true);
    }

    /**
     * Metodo che inizializza l'agente Cliente che invierà il messaggio all'agente Palestra.
     */
    @Override
    protected void setup() {
        // Inizializzo il Frame.
        showGuy();

        // Gestisco la pressione del pulsante presente nel Frame che permette di inviare il messaggio.
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                addBehaviour(new OneShotBehaviour() {
                    @Override
                    public void action() {
                    // Inizializzo il messaggio e lo invio all'agente Palestra.
                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.setContent("Aggiungi una nuova prenotazione per il 28/04/2022");
                    msg.addReceiver(new AID("Palestra", AID.ISLOCALNAME));
                    send(msg);

                    }
                });
            }
        });
    }
}
