package jade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class Cliente extends Agent {

    JFrame frame;

    JButton b1 = new JButton("Aggiungi prenotazione");

    void showGuy() {

        frame = new JFrame();

        frame.add(b1);

        frame.setSize(200, 50);

        frame.setVisible(true);

    }


    @Override
    protected void setup() {

        showGuy();

        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                addBehaviour(new OneShotBehaviour() {

                    @Override
                    public void action() {

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
