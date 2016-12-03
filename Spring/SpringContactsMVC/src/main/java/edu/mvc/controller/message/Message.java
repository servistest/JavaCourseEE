package edu.mvc.controller.message;

/**
 * Created by Admin on 01.12.2016.
 */
//специальный класс, который хранит сообщение, извлеченное из MessageSource,
//и тип сообщения (т.е. успех или ошибка) для отображения в области сообщений представления
public class Message {
    private String type;
    private String message;

    public Message(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
