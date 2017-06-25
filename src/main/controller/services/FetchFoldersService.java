package main.controller.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import main.controller.ModelAccess;
import main.model.EmailAccountBean;
import main.model.folder.EmailFolderBean;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.event.MessageCountAdapter;
import javax.mail.event.MessageCountEvent;

/**
 * Created by kpant on 6/25/17.
 */
public class FetchFoldersService extends Service<Void> {

    private static int NUMBER_OF_FETCHFODLERSSERVICES_ACTIVE = 0;
    private EmailFolderBean<String> foldersRoot;
    private EmailAccountBean emailAccountBean;
    private ModelAccess modelAccess;

    public FetchFoldersService(EmailFolderBean<String> foldersRoot, EmailAccountBean emailAccountBean, ModelAccess modelAccess) {
        this.foldersRoot = foldersRoot;
        this.emailAccountBean = emailAccountBean;
        this.modelAccess = modelAccess;
        this.setOnSucceeded(e -> {
            NUMBER_OF_FETCHFODLERSSERVICES_ACTIVE--;
        });
    }

    public static boolean noServicesActive() {
        return NUMBER_OF_FETCHFODLERSSERVICES_ACTIVE == 0;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                NUMBER_OF_FETCHFODLERSSERVICES_ACTIVE++;
                if (emailAccountBean != null) {
                    //list a return of folders in emailAccount
                    Folder[] folders = emailAccountBean.getStore().getDefaultFolder().list();
                    for (Folder folder : folders) {
                        modelAccess.addFolder(folder);
                        EmailFolderBean<String> item = new EmailFolderBean<String>(folder.getName(), folder.getFullName());
                        foldersRoot.getChildren().add(item);
                        item.setExpanded(true);
                        addMessageListenerToFolder(folder, item);
                        FetchMessagesOnFolderService fetchMessagesOnFolderService = new FetchMessagesOnFolderService(item, folder);
                        fetchMessagesOnFolderService.start();
                        System.out.println("Added: " + folder.getName());
                        Folder[] subFolders = folder.list();

                        for (Folder subFolder : subFolders) {
                            modelAccess.addFolder(subFolder);
                            EmailFolderBean<String> subItem = new EmailFolderBean<String>(subFolder.getName(), subFolder.getFullName());
                            item.getChildren().add(subItem);
                            addMessageListenerToFolder(subFolder, subItem);
                            FetchMessagesOnFolderService fetchMessagesOnSubFolderService = new FetchMessagesOnFolderService(subItem, subFolder);
                            fetchMessagesOnSubFolderService.start();
                            System.out.println("Added: " + subFolder.getName());

                        }
                    }
                }

                return null;
            }
        };


    }

    private void addMessageListenerToFolder(Folder folder, EmailFolderBean<String> item) {
        folder.addMessageCountListener(new MessageCountAdapter() {
            @Override
            public void messagesAdded(MessageCountEvent e) {
                for (int i = 0; i < e.getMessages().length; i++) {
                    try {
                        Message currentMessage = folder.getMessage(folder.getMessageCount() - i);
                        item.addEmail(0, currentMessage);
                    } catch (MessagingException e1) {
                        e1.printStackTrace();
                    }
                }
            }


        });
    }
}
