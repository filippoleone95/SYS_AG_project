import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class simpleBot extends TelegramLongPollingBot {

    //@Override
    public void onUpdateReceived(Update update) {
        System.out.print(update.getMessage().getFrom().getFirstName() + ": ");
        System.out.println(update.getMessage().getText());

        String command = update.getMessage().getText();

        if (command.equals("/run"))
        {
            String message = "Vincenzo è un trimone";
            SendMessage response = new SendMessage();
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            try{
                execute(response);
            }catch (TelegramApiException E)
            {
                E.printStackTrace();
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
