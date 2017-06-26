package main.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import main.model.table.AbstractTableItem;
import main.model.table.FormatableInteger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kpant on 6/21/17.
 */
public class EmailMessageBean extends AbstractTableItem {
    private SimpleStringProperty sender;
    private SimpleStringProperty subject;
    private SimpleObjectProperty<FormatableInteger> size;
    private Message messageReference;
    private SimpleObjectProperty<Date> date;
    //Attachments handling:
    private List<MimeBodyPart> attachmentsList = new ArrayList<>();
    private StringBuffer attachmentsName = new StringBuffer();

    public EmailMessageBean(String subject, String sender, int size, boolean isRead, Date date, Message messageReference) {
        super(isRead);
        this.sender = new SimpleStringProperty(sender);
        this.subject = new SimpleStringProperty(subject);
        this.size = new SimpleObjectProperty<FormatableInteger>(new FormatableInteger(size));
        this.messageReference = messageReference;
        this.date = new SimpleObjectProperty<>(date);
    }

    public List<MimeBodyPart> getAttachmentsList() {
        return attachmentsList;
    }

    public String getAttachmentsName() {
        return attachmentsName.toString();
    }

    public void addAttachment(MimeBodyPart mbp) {
        attachmentsList.add(mbp);
        try {
            attachmentsName.append(mbp.getFileName() + "; ");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public boolean hasAttachments() {
        return attachmentsList.size() > 0;
    }

    //clear methods
    public void clearAttachments() {
        attachmentsList.clear();
        attachmentsName.setLength(0);
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


    public FormatableInteger getSize() {
        return size.get();
    }


    public Message getMessageReference() {
        return messageReference;
    }

    public Date getDate() {
        return date.get();
    }

    public SimpleObjectProperty<Date> dateProperty() {
        return date;
    }
}
