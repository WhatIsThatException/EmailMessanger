package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.EmailAccountBean;
import main.model.EmailMessageBean;
import main.model.folder.EmailFolderBean;

import javax.mail.Folder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kpant on 6/23/17.
 */
public class ModelAccess {

    private Map<String, EmailAccountBean> emailAccounts = new HashMap<>();
    private ObservableList<String> emailAccountsNames = FXCollections.observableArrayList();

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

    public ObservableList<String> getEmailAccountNames() {
        return emailAccountsNames;
    }

    public EmailAccountBean getEmailAccountByName(String name) {
        return emailAccounts.get(name);
    }

    public void addAccount(EmailAccountBean account) {
        emailAccounts.put(account.getEmailAddress(), account);
        emailAccountsNames.add(account.getEmailAddress());
    }
}
