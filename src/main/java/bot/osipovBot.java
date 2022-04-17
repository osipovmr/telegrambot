package bot;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class osipovBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
           /* message.setChatId(update.getMessage().getChatId().toString());
            message.setText(update.getMessage().getText()+"\n"+"У тебя получилось!");*/
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText("Сообщение получено, спасибо за Ваше обращение."+"\n"+"Алена! Я сделал бота))"+"\n"+"Осталось разобраться с гитигнором и всё закоммитить.");
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {

        return "osipovBot";
    }

    @Override
    public String getBotToken() {
        return "5394392586:AAGOhm-MrPWZZqUC4p8xYwBRCO3xlr__n60";
    }
}
