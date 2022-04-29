package telegramBot;

import connessioneDatabase.ConnessioneDB;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import prenotazione.Prenotazione;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class SimpleBot extends TelegramLongPollingBot {

    TreeSet<Prenotazione> listaPrenotazioni = new TreeSet<Prenotazione>();
    Connection conn;

    //@Override
    public void onUpdateReceived(Update update) {
        System.out.print(update.getMessage().getFrom().getFirstName() + ": ");
        System.out.println(update.getMessage().getText());

        String command = update.getMessage().getText();

        if (command.equals("/run")) {
            String message = "Vuoi correre?";
            SendMessage response = new SendMessage();
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);


            System.out.print(update.getMessage().getFrom().getFirstName() + ": ");
            System.out.println("Ristampa orario -> " + update.getMessage().getText());

            try {
                execute(response);
            } catch (TelegramApiException E) {
                E.printStackTrace();
            }
        }

        if (command.equals("/prenota")) {
            try {
                Connection conn = ConnessioneDB.connectToDB();
                if (conn != null) {
                    listaPrenotazioni = ConnessioneDB.LoadPrenotazioni(conn);
                }
            } catch (Exception ex) {
                System.out.println("telegramBot.SimpleBot.onUpdateReceived - Eccezione : " + ex.toString());
            }

            for (Prenotazione prenotazione : listaPrenotazioni) {
                String name = update.getMessage().getFrom().getFirstName();
                if (prenotazione.getNome().equals(name)) {
                    String message = prenotazione.toString();
                    SendMessage response = new SendMessage();
                    response.setChatId(update.getMessage().getChatId().toString());
                    response.setText(message);
                    try {
                        execute(response);
                    } catch (TelegramApiException E) {
                        E.printStackTrace();
                    }
                }
            }
        }

        if (command.equals("/prenota_prova")) {

            String message = "Inserisci corso";
            SendMessage response = new SendMessage();
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);


            try {
                execute(response);
            } catch (TelegramApiException E) {
                E.printStackTrace();
            }

            String nome = update.getMessage().getFrom().getFirstName();
            String numero = update.getMessage().getText();

            try {
                conn = ConnessioneDB.connectToDB();
                if (conn != null) {
                    JOptionPane.showMessageDialog(null, "CONNESSIONE EFFETTUATA");
                }
            } catch (Exception ex) {
                System.out.println("telegramBot.SimpleBot.onUpdateReceived - Eccezione PRENOTA PROVA: " + ex.toString());
                JOptionPane.showMessageDialog(null, "CONNESSIONE NON EFFETTUATA");
            }

            String SQL = "INSERT INTO prenotazione(numero, nome)" + "VALUES (?, ?)";

            try {
                //CREAZIONE DELLA preparedStatement
                PreparedStatement preparedStmt = conn.prepareStatement(SQL);
                //MODIFCA I PARAMETRI VALUES DELLA QUERY
                preparedStmt.setString(1, numero);
                preparedStmt.setString(2, nome);
                //ESECUZIONE DELLA QUERY
                if (preparedStmt.executeUpdate() > 0) {
                    //INSERIMENTO NEL TREESET
                    JOptionPane.showMessageDialog(null, "PRENOTAZIONE_PROVA INSERITA");
                } else {
                    JOptionPane.showMessageDialog(null, "PRENOTAZIONE_PROVA NON INSERITA!", "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERRORE DB!", JOptionPane.ERROR_MESSAGE);
                System.out.println("telegramBot.SimpleBot.prenotazione_prova - Eccezione: " + e.toString());
                System.out.println("Nome: " + nome + " Numero: " + numero);

            }
        }

        if(update.hasMessage()){
            Message message = update.getMessage();

                if(message.hasText()){
                    String text = message.getText();

                    if(text.equals("/prova")){
                        System.out.println("Prova!");

                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setText("Stai condividendo il tuo numero! Ci servirà per identificarti univocamente!");
                        sendMessage.setParseMode(ParseMode.MARKDOWN);
                        sendMessage.setChatId(String.valueOf((message.getChatId())));

                        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                        //replyKeyboardMarkup.setResizeKeyboard(true).setSelective(true);
                        List<KeyboardRow> keyboardRowList = new ArrayList<KeyboardRow>();
                        KeyboardRow keyboardRow1 = new KeyboardRow();
                        KeyboardButton keyboardButton1 = new KeyboardButton();
                        keyboardButton1.setText("Condividi numero");
                        keyboardButton1.setRequestContact(true);
                        keyboardRow1.add(keyboardButton1);
                        keyboardRowList.add(keyboardRow1);
                        replyKeyboardMarkup.setKeyboard(keyboardRowList);
                        sendMessage.setReplyMarkup(replyKeyboardMarkup);


                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException E) {
                            E.printStackTrace();
                        }

                    }else if(text.equals("Rocco")){
                        SendMessage sendMessage = new SendMessage();
                               sendMessage.setText("Ciao Rocco");
                               sendMessage.setParseMode(ParseMode.MARKDOWN);
                        sendMessage.setChatId(String.valueOf(message.getChatId()));

                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException E) {
                            E.printStackTrace();
                        }
                    }
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
