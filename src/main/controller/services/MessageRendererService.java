package main.controller.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.web.WebEngine;
import main.model.EmailMessageBean;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import java.io.IOException;

/**
 * Created by kpant on 6/25/17.
 */
public class MessageRendererService extends Service<Void> implements Runnable {
    private EmailMessageBean messageToRender;
    private WebEngine messageRendererEngine;
    private StringBuffer sb = new StringBuffer();


    public MessageRendererService(WebEngine messageRendererEngine) {
        this.messageRendererEngine = messageRendererEngine;
    }

    public void setMessageToRender(EmailMessageBean messageToRender) {
        this.messageToRender = messageToRender;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                renderMessage();
                return null;
            }
        };
    }


    private void renderMessage() {
        //clear the sb
        sb.setLength(0);
        Message message = messageToRender.getMessageReference();
        try {
            String messageType = message.getContentType();
            if (messageType.contains("TEXT/HTML") || messageType.contains("TEXT/PLAIN") ||
                    messageType.contains("text")) {
                sb.append(message.getContent().toString());

            } else if (messageType.contains("multipart")) {
                Multipart mp = (Multipart) message.getContent();
                for (int i = mp.getCount() - 1; i >= 0; i--) {
                    BodyPart bp = mp.getBodyPart(i);
                    String contentType = bp.getContentType();
                    if (contentType.contains("TEXT/HTML") || contentType.contains("TEXT/PLAIN") ||
                            contentType.contains("text") || contentType.contains("mixed")) {
                        //Here the risk of incomplete message are shown, for messages that contain both
                        //html and text content, but these messages are very rare
                        if (sb.length() == 0) {
                            sb.append(bp.getContent().toString());
                        }
                        //here the attachments are handled TODO by someone who cares
                    } else if (contentType.toLowerCase().contains("application")) {
                        MimeBodyPart mbp = (MimeBodyPart) bp;
                        //Sometimes the text content of the message is encapsulated in another multipart.
                        //so we have to iterate again through it.
                    } else if (bp.getContentType().contains("multipart")) {
                        Multipart mp2 = (Multipart) bp.getContent();
                        for (int j = mp2.getCount() - 1; j >= 0; j--) {
                            BodyPart bp2 = mp2.getBodyPart(j);
                            if ((bp2.getContentType().contains("TEXT/HTML")) || (bp2.getContentType().contains("TEXT/PLAIN"))) {
                                sb.append(bp2.getContent().toString());
                            }

                        }
                    }
                }
            }
            messageRendererEngine.loadContent(sb.toString());

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void run() {
        renderMessage();
    }
}
