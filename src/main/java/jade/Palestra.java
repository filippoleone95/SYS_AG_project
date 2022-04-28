package jade;

import javax.swing.JOptionPane;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Classe che gestisce l'agente Palestra.
 */
@SuppressWarnings("serial")
public class Palestra extends Agent {
    /**
     * Inizializzazione dell'agente Palestra.
     */
    @Override
    protected void setup() {
        // Richiamo il metodo che permette di eseguire azioni in maniera ciclica infinita.
        addBehaviour(new CyclicBehaviour() {

            @Override
            public void action() {
                // Metto in ascolto l'agente per la ricezione di messaggi.
                ACLMessage msg = receive();
                // Se il messaggio ricevuto non è un null, stampo il messaggio a video.
                if(msg != null) {
                    JOptionPane.showMessageDialog(null, "Messaggio ricevuto : " + msg.getContent());
                } else {
                    // Nel caso in cui non è stato ricevuto alcun messaggio, blocco la ricezione dell'agente
                    // per risparmiare carico sulla CPU. L'agente si riattiverà non appena verrà ricevuto un nuovo messaggio.
                    block();
                }
            }
        });
    }
}
