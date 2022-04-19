package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class osipovBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        try {  // We check if the update has a message and the message has text
            if (update.hasMessage() && update.getMessage().hasText()) {
                //Извлекаем из объекта сообщение пользователя
                Message inMess = update.getMessage();
                //Достаем из inMess id чата пользователя
                String chatId = inMess.getChatId().toString();
                String userName = getUserName(inMess);
                //Получаем текст сообщения пользователя, отправляем в написанный нами обработчик
                String response = parseMessage(inMess.getText());
                //Создаем объект класса SendMessage - наш будущий ответ пользователю
                SendMessage outMess = new SendMessage();
                //Добавляем в наше сообщение id чата а также наш ответ
                outMess.setChatId(chatId);
                outMess.setText(response + userName+ ".");
                //Отправка в чат
                execute(outMess);
            }
        }// Call method to send the message
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String getUserName(Message msg) {
        User user = msg.getFrom();
        String userName = user.getUserName();
        return (userName != null) ? userName : String.format("%s %s", user.getLastName(), user.getFirstName());
    }

    public String parseMessage(String textMsg) {
        String response;
        if (textMsg.equals("/start"))
            response = "Приветствую, ";
        else
            response = "Сообщение не распознано, нажмите /start, гражданин ";

        return response;
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
