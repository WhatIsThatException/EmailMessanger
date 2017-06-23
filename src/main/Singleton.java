package main;

import main.model.EmailMessageBean;

/**
 * Created by kpant on 6/22/17.
 */
public class Singleton {
    private static Singleton instance = null;
    private EmailMessageBean message;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }

    public EmailMessageBean getMessage() {
        return message;
    }

    public void setMessage(EmailMessageBean message) {
        this.message = message;
    }
}
