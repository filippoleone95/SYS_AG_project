package jade;

import javax.swing.JOptionPane;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class Palestra extends Agent {

    @Override
    protected void setup() {

        addBehaviour(new CyclicBehaviour() {

            @Override
            public void action() {
                ACLMessage msg = receive();

                if(msg != null) {
                    JOptionPane.showMessageDialog(null, "Messaggio ricevuto : " + msg.getContent());
                } else {
                    block();
                }

            }
        });

    }

}
