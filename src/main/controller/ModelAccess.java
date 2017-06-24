package main.controller;

import main.model.EmailMessageBean;
import main.model.folder.EmailFolderBean;

/**
 * Created by kpant on 6/23/17.
 */
public class ModelAccess {

    private EmailMessageBean selectedMessage;
    private EmailFolderBean<String> selectedFolder;

    public EmailMessageBean getSelectedMessage() {
        return selectedMessage;
    }

    public void setSelectedMessage(EmailMessageBean selectedMessage) {
        this.selectedMessage = selectedMessage;
    }

    public EmailFolderBean<String> getSelectedFolder() {
        return selectedFolder;
    }

    public void setSelectedFolder(EmailFolderBean<String> selectedFolder) {
        this.selectedFolder = selectedFolder;
    }
}
