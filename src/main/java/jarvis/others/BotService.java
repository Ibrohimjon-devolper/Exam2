package jarvis.others;

import jarvis.others.EslatmaBot;
import lombok.Getter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class BotService extends TelegramLongPollingBot {

    @Getter
    private static EslatmaBot instance;

    public static EslatmaBot creadInstance(String token) {
        if (instance == null) {
            instance = new EslatmaBot(token);
        }
        return instance;
    }


    private BotService(String botToken) {
        super(botToken);
    }

    @Override
    public String getBotUsername() {
        return "@test_prosta_jarvis_bot";
    }


    @Override
    public void onUpdateReceived(Update update) {


        if (update.hasMessage()) {

            Message message = update.getMessage();

            if (message.hasText()) {
                getMessageText(message);
            }

        }
    }

    private void getMessageText(Message message) {

        String text = message.getText();

        if (text.equals("/start")) {

            SendMessage sendMessage = new SendMessage();

            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Xush kelibsiz \uD83D\uDE42");


            ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup();
            replyMarkup.setResizeKeyboard(true);

            List<KeyboardRow> qatorlar = new ArrayList<>();

            KeyboardRow qator1 = new KeyboardRow();


            KeyboardButton button1 = new KeyboardButton();
            button1.setText("Eslat text bo'limi");

            KeyboardButton button2 = new KeyboardButton();
            button2.setText("Valyutalar bo'limi");

            qator1.add(button1);
            qator1.add(button2);


            qatorlar.add(qator1);

            replyMarkup.setKeyboard(qatorlar);

            sendMessage.setReplyMarkup(replyMarkup);


            getSendMsg(sendMessage);

        }

        if (text.equals("Eslat text bo'limi")) {
            SendMessage sendMessage = new SendMessage();

            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("""
                    Siz eslatma text bo'limidasiz 
                    Textni kiriting : 
                    """);

            getSendMsg(sendMessage);
            eslatmaTextBolimi(message);


        }


    }


    private void eslatmaTextBolimi(Message message) {
        SendMessage sendMessage = new SendMessage();
        String eslatmaText = message.getText();

        sendMessage.setChatId(message.getChatId());

        sendMessage.setText("Endi vaqtini kiriting : (secuntda)");

        getSendMsg(sendMessage);



    }


    private void getSendMsg(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
