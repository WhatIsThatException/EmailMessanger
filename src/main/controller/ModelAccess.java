package main.controller;

import main.model.EmailMessageBean;
import main.model.folder.EmailFolderBean;

import javax.mail.Folder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kpant on 6/23/17.
 */
public class ModelAccess {

    private EmailMessageBean selectedMessage;
    private EmailFolderBean<String> selectedFolder;
    private List<Folder> foldersList = new ArrayList<>();

    public List<Folder> getFoldersList() {
        return foldersList;
    }

    public void addFolder(Folder folder) {
        foldersList.add(folder);
    }

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
