package main.model;

import javafx.beans.property.SimpleStringProperty;
import main.model.table.AbstractTableItem;
import main.utils.MessageHelper;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    //Attachments handling:
    private List<MimeBodyPart> attachmentsList = new ArrayList<>();
    private StringBuffer attachmentsName = new StringBuffer();

    public EmailMessageBean(String subject, String sender, int size, boolean isRead, Message messageReference) {
        super(isRead);
        this.sender = new SimpleStringProperty(sender);
        this.subject = new SimpleStringProperty(subject);
        this.size = new SimpleStringProperty(MessageHelper.formatSize(size));
        this.messageReference = messageReference;
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


    public String getSize() {
        return size.get();
    }


    public Message getMessageReference() {
        return messageReference;
    }
}
