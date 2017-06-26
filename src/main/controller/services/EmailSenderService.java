package main.controller.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import main.model.EmailAccountBean;
import main.model.EmailConstants;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kpant on 6/26/17.
 */
public class EmailSenderService extends Service<Integer> {

    private int result;
    private EmailAccountBean emailAccountBean;
    private String subject;
    private String recipent;
    private String content;

    private List<File> attachments = new ArrayList<>();

    public EmailSenderService(EmailAccountBean emailAccountBean, String subject, String recipent, String content, List<File> attachments) {
        this.emailAccountBean = emailAccountBean;
        this.subject = subject;
        this.recipent = recipent;
        this.content = content;
        this.attachments = attachments;
    }


    @Override
    protected Task<Integer> createTask() {
        return new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                try {
                    //setup
                    Session session = emailAccountBean.getSession();
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(emailAccountBean.getEmailAddress());
                    message.addRecipients(Message.RecipientType.TO, recipent);
                    message.setSubject(subject);

                    //setting the content
                    Multipart multipart = new MimeMultipart();
                    BodyPart messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setContent(content, "text/html");
                    ;
                    multipart.addBodyPart(messageBodyPart);

                    //adding attachments
                    if (attachments.size() > 0) {
                        for (File file : attachments) {
                            MimeBodyPart messageBodyPartAttach = new MimeBodyPart();
                            DataSource source = new FileDataSource(file.getAbsoluteFile());
                            messageBodyPartAttach.setDataHandler(new DataHandler(source));
                            messageBodyPartAttach.setFileName(file.getName());
                            multipart.addBodyPart(messageBodyPartAttach);
                        }
                    }

                    message.setContent(multipart);

                    //sending the message
                    Transport transport = session.getTransport();
                    transport.connect(emailAccountBean.getProperties().getProperty("outgoingHost"),
                            emailAccountBean.getEmailAddress(), emailAccountBean.getPassword());
                    transport.sendMessage(message, message.getAllRecipients());
                    transport.close();

                    result = EmailConstants.MESSAGE_SENT_OK;


                } catch (Exception e) {
                    result = EmailConstants.MESSAGE_SENT_ERROR;
                    e.printStackTrace();

                }
                return result;
            }
        };
    }
}
