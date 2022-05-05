package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.security.SecureRandom;
import java.util.ArrayList;

public class OsipovBot extends TelegramLongPollingBot {
    UserList a = new UserList();
    ArrayList<String> f = a.getList();
    @Override
    public void onUpdateReceived(Update update) {
        //Извлекаем из объекта сообщение пользователя
        Message inMess = update.getMessage();
        //Достаем из inMess id чата пользователя
        String chatId = inMess.getChatId().toString();
        String userName = getUserName(inMess);
        //Создаем объект класса SendMessage - наш будущий ответ пользователю
        SendMessage outMess = new SendMessage();
        String inMessText = inMess.getText();
        String start = "/start";
        String help = "/help";
        String userList = "/userList";
        try {  // We check if the update has a message and the message has text
            if ((update.hasMessage() && inMess.hasText()) && (inMessText.equals(start))) {
                a.addList(userName); //приложение при срабатывании команды старт записывает пользователя в коллекцию
                outMess.setText("Это команда старт, гражданин " +
                        userName + "."
                        + "\n" +
                        "Мы сохранили Ваш userName в нашем безопасном банке.");
            } else if ((update.hasMessage() && inMess.hasText()) && (inMessText.equals(help))) {
                outMess.setText("Чтобы получить помощь, обратитесь к пользователю @osipov_mr");
            } else if ((update.hasMessage() && inMess.hasText()) && (inMessText.equals(userList))) {
                outMess.setText("Данная команда выводит список пользователей, нажавших /start:"
                        + "\n" +
                        f);
            } else if (update.hasMessage() && inMess.hasText()) {
                outMess.setText("Сообщение не распознано, вот список доступных команд:"
                        + "\n" +
                        "/start"
                        + "\n" +
                        "/help"
                        + "\n" +
                        "/userList"
                );
            }
            outMess.setChatId(chatId);
            execute(outMess);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getUserName(Message msg) {
        User user = msg.getFrom();
        String userName = user.getUserName();
        if (userName != null) {
            return userName;
        } else {
            return String.format("%s %s", user.getLastName(), user.getFirstName());
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
