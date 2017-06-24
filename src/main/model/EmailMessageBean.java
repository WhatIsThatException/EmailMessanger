package main.model;

import javafx.beans.property.SimpleStringProperty;
import main.model.table.AbstractTableItem;
import main.utils.MessageHelper;

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
    private String content;

    public EmailMessageBean(String subject, String sender, int size, String content, boolean isRead) {
        super(isRead);
        this.sender = new SimpleStringProperty(sender);
        this.subject = new SimpleStringProperty(subject);
        this.size = new SimpleStringProperty(MessageHelper.formatSize(size));
        this.content = content;
    }

    @Override
    public String toString() {
        return "EmailMessageBean{" +
                "sender=" + sender +
                ", subject=" + subject +
                ", size=" + size +
                ", content='" + content + '\'' +
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

    public String getContent() {
        return content;
    }

}
