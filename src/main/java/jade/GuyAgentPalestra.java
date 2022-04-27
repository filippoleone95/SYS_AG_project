package jade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GuyAgentPalestra {

    JFrame frame;

    JButton b1 = new JButton("Aggiungi prenotazione");

    boolean result = false;

    void showGuy() {

        frame = new JFrame();

        frame.add(b1);

        frame.setSize(300, 300);

        frame.setVisible(true);

    }

    public Boolean pressedButton() {


        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                result = true;
            }
        });

        return result;
    }

}
