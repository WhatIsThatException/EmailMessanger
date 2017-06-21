package main;

import javafx.beans.property.SimpleStringProperty;
import main.utils.MessageHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kpant on 6/21/17.
 */
public class EmailMessageBean {

    public static Map<String, Integer> formattedValues = new HashMap<>();
    private SimpleStringProperty sender;
    private SimpleStringProperty subject;
    private SimpleStringProperty size;

    public EmailMessageBean(String subject, String sender, int size) {
        this.sender = new SimpleStringProperty(sender);
        this.subject = new SimpleStringProperty(subject);
        this.size = new SimpleStringProperty(MessageHelper.formatSize(size));
    }

    public String getSender() {
        return sender.get();
    }

    public String getSubject() {
        return subject.get();
    }


    public String getSize() {
        return size.get();
    }

}
