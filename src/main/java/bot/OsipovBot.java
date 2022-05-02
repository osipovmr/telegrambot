package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class OsipovBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        //Извлекаем из объекта сообщение пользователя
        Message inMess = update.getMessage();
        //Достаем из inMess id чата пользователя
        String chatId = inMess.getChatId().toString();
        String userName = getUserName(inMess);
        UserList a = new UserList();
        //Создаем объект класса SendMessage - наш будущий ответ пользователю
        SendMessage outMess = new SendMessage();

        try {  // We check if the update has a message and the message has text
            if (update.hasMessage() && inMess.hasText()) {
                switch (inMess.getText()) {
                    case "/start" -> {
                        a.addList(userName);
                        outMess.setText("Это команда старт, гражданин " + userName);
                        break;
                    }
                    case "/help" -> {
                        outMess.setText("Чтобы получить помощь, обратитесь к пользователю osipov_mr");
                        break;
                    }
                    case "/usersList" -> {
                        String f = a.getList().toString();
                        outMess.setText("Это команда выводит список пользователей, выбравших команду /start:"
                                + "\n" +
                                f);
                        break;
                    }
                    default -> {
                        outMess.setText("Сообщение не распознано, вот список команд:"
                                + "\n" +
                                "/start"
                                + "\n" +
                                "/help"
                                + "\n" +
                                "/usersList");
                        break;
                    }
                }
                outMess.setChatId(chatId);
                execute(outMess);
            }
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
