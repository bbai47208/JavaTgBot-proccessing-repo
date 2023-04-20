import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyTelegramBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Обработка текстового сообщения
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            SendMessage message = new SendMessage()
                    .setChatId(chatId)
                    .setText("Вы отправили текстовое сообщение: " + messageText);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.hasMessage() && update.getMessage().hasPhoto()) {
            // Обработка сообщения с фото
            Long chatId = update.getMessage().getChatId();
            int photoCount = update.getMessage().getPhoto().size();
            SendMessage message = new SendMessage()
                    .setChatId(chatId)
                    .setText("Вы отправили сообщение с фото. Количество фото: " + photoCount);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotToken() {
        return "your_bot_token_here";
    }

    @Override
    public String getBotUsername() {
        return "your_bot_username_here";
    }
}
