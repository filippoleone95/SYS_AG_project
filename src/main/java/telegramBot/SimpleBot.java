package telegramBot;

import connessioneDatabase.ConnessioneDB;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import prenotazione.Prenotazione;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.TreeSet;

public class SimpleBot extends TelegramLongPollingBot {

    TreeSet<Prenotazione> listaPrenotazioni = new TreeSet<Prenotazione>();
    Connection conn ;

    //@Override
    public void onUpdateReceived(Update update) {
        System.out.print(update.getMessage().getFrom().getFirstName() + ": ");
        System.out.println(update.getMessage().getText());

        String command = update.getMessage().getText();

        if (command.equals("/run"))
        {
            String message = "Vuoi correre?";
            SendMessage response = new SendMessage();
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);


            System.out.print(update.getMessage().getFrom().getFirstName() + ": ");
            System.out.println("Ristampa orario -> " + update.getMessage().getText());

            try{
                execute(response);
            }catch (TelegramApiException E)
            {
                E.printStackTrace();
            }
        }

        if(command.equals("/prenota")) {
            try {
                Connection conn = ConnessioneDB.connectToDB();
                if(conn != null) {
                    listaPrenotazioni = ConnessioneDB.LoadPrenotazioni(conn);
                }
            } catch (Exception ex) {
                System.out.println("telegramBot.SimpleBot.onUpdateReceived - Eccezione : " + ex.toString());
            }

            for (Prenotazione prenotazione: listaPrenotazioni ) {
                String name = update.getMessage().getFrom().getFirstName();
                if(prenotazione.getNome().equals(name)) {
                    String message = prenotazione.toString();
                    SendMessage response = new SendMessage();
                    response.setChatId(update.getMessage().getChatId().toString());
                    response.setText(message);
                    try{
                        execute(response);
                    } catch (TelegramApiException E) {
                        E.printStackTrace();
                    }
                }
            }
        }

        if(command.equals("/prenota_prova"))
        {
            String message = "Inserisci corso";
            SendMessage response = new SendMessage();
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);

            //String orarioPrenotazione = (update.getMessage().getText());


            try{
                execute(response);
            }catch (TelegramApiException E)
            {
                E.printStackTrace();
            }
            System.out.print(update.getMessage().getFrom().getFirstName() + ": ");
            System.out.println("Ristampa orario -> " + update.getMessage().getText());

            String nome = update.getMessage().getFrom().getFirstName();

            String SQL = "INSERT INTO prenotazioni(numero, nome)" + "VALUES (?, ?)";

            try{
                //CREAZIONE DELLA preparedStatement
                PreparedStatement preparedStmt = conn.prepareStatement(SQL);

                //MODIFCA I PARAMETRI VALUES DELLA QUERY
                preparedStmt.setString(1, update.getMessage().getText());
                preparedStmt.setString(2, nome);


                //ESECUZIONE DELLA QUERY
                if(preparedStmt.executeUpdate()>0){

                    //INSERIMENTO NEL TREESET

                    JOptionPane.showMessageDialog(null, "PRENOTAZIONE INSERITA");
                }
                else{
                    JOptionPane.showMessageDialog(null, "PRENOTAZIONE NON INSERITA!","ATTENZIONE",JOptionPane.WARNING_MESSAGE);
                }
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERRORE NEL CARICAMENTO SUL DATABASE!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //@Override
    public String getBotUsername() {
        // TODO
        return "JadeTEST_bot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "5209476175:AAHdCtCczNVG6dvme8lK4JNoIs2ornoUb5g";
    }
}
