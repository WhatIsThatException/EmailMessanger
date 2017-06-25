package main.model;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Properties;

/**
 * Created by kpant on 6/24/17.
 */
public class EmailAccountBean {
    private String emailAddress;
    private String password;
    private Properties properties;

    private Store store;
    private Session session;

    private int loginState = EmailConstants.LOGIN_STATE_NOT_READY;

    public EmailAccountBean(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
        properties = new Properties();

        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.transport.protocol", "smtps");
        properties.put("mail.smtps.host", "smtp.gmail.com");
        properties.put("mail.smtps.auth", "true");
        properties.put("incomingHost", "imap.gmail.com");
        properties.put("outgoingHost", "smtp.gmail.com");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAddress, password);
            }
        };

        //connecting:
        session = Session.getInstance(properties, auth);
        try {
            this.store = session.getStore();
            //connect to email server
            store.connect(properties.getProperty("incomingHost"), emailAddress, password);
            System.out.println("EmailAccountBean constructed successfully");
            loginState = EmailConstants.LOGIN_STATE_SUCCEDED;
        } catch (Exception e) {
            e.printStackTrace();
            loginState = EmailConstants.LOGIN_STATE_FAILED_BY_CREDENTIALS;
        }
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Properties getProperties() {
        return properties;
    }

    public Store getStore() {
        return store;
    }

    public Session getSession() {
        return session;
    }

    public int getLoginState() {
        return loginState;
    }


}
