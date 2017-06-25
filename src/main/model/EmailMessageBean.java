package main.model;

import javafx.beans.property.SimpleStringProperty;
import main.model.table.AbstractTableItem;
import main.utils.MessageHelper;

import javax.mail.Message;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kpant on 6/21/17.
 */
public class EmailMessageBean extends AbstractTableItem {
    public static Map<String, Integer> formattedValues = new HashMap<>();
    private SimpleStringProperty sender;
    private SimpleStringProperty subject;
    private SimpleStringProperty size;
    private Message messageReference;

    public EmailMessageBean(String subject, String sender, int size, boolean isRead, Message messageReference) {
        super(isRead);
        this.sender = new SimpleStringProperty(sender);
        this.subject = new SimpleStringProperty(subject);
        this.size = new SimpleStringProperty(MessageHelper.formatSize(size));
        this.messageReference = messageReference;
    }

    @Override
    public String toString() {
        return "EmailMessageBean{" +
                "sender=" + sender +
                ", subject=" + subject +
                ", size=" + size +
                '}';
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


    public Message getMessageReference() {
        return messageReference;
    }
}
