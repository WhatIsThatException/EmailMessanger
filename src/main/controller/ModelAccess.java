package main.controller;

import main.model.EmailMessageBean;

/**
 * Created by kpant on 6/23/17.
 */
public class ModelAccess {

    private EmailMessageBean selectedMessage;

    public EmailMessageBean getSelectedMessage() {
        return selectedMessage;
    }

    public void setSelectedMessage(EmailMessageBean selectedMessage) {
        this.selectedMessage = selectedMessage;
    }
}
