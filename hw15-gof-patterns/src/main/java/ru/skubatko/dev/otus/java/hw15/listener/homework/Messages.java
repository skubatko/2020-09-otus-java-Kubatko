package ru.skubatko.dev.otus.java.hw15.listener.homework;

import ru.skubatko.dev.otus.java.hw15.Message;
import ru.skubatko.dev.otus.java.hw15.ObjectForMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class Messages {

    private Messages() {throw new UnsupportedOperationException(); }

    // todo: 4. Сделать Listener для ведения истории: старое сообщение - новое (подумайте, как сделать, чтобы сообщения не портились)
    public static Message copyOf(Message message) {
        ObjectForMessage objectForMessage = new ObjectForMessage();
        List<String> data = Optional.ofNullable(message.getField13())
                                    .map(ObjectForMessage::getData)
                                    .orElseGet(Collections::emptyList);
        objectForMessage.setData(new ArrayList<>(data));

        return new Message.Builder(message.getId())
                       .field1(message.getField1())
                       .field2(message.getField2())
                       .field3(message.getField3())
                       .field4(message.getField4())
                       .field5(message.getField5())
                       .field6(message.getField6())
                       .field7(message.getField7())
                       .field8(message.getField8())
                       .field9(message.getField9())
                       .field10(message.getField10())
                       .field11(message.getField11())
                       .field12(message.getField12())
                       .field13(objectForMessage)
                       .build()
                ;
    }
}
