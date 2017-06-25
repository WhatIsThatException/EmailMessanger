package main.controller.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import main.model.EmailAccountBean;
import main.model.folder.EmailFolderBean;

import javax.mail.Folder;

/**
 * Created by kpant on 6/25/17.
 */
public class FetchFoldersService extends Service<Void> {

    private EmailFolderBean<String> foldersRoot;
    private EmailAccountBean emailAccountBean;

    public FetchFoldersService(EmailFolderBean<String> foldersRoot, EmailAccountBean emailAccountBean) {
        this.foldersRoot = foldersRoot;
        this.emailAccountBean = emailAccountBean;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                if (emailAccountBean != null) {
                    //list a return of folders in emailAccount
                    Folder[] folders = emailAccountBean.getStore().getDefaultFolder().list();
                    for (Folder folder : folders) {
                        EmailFolderBean<String> item = new EmailFolderBean<String>(folder.getName(), folder.getFullName());
                        foldersRoot.getChildren().add(item);
                        item.setExpanded(true);
                        System.out.println("Added: " + folder.getName());
                        Folder[] subFolders = folder.list();

                        for (Folder subFolder : subFolders) {
                            EmailFolderBean<String> subItem = new EmailFolderBean<String>(subFolder.getName(), subFolder.getFullName());
                            item.getChildren().add(subItem);
                            System.out.println("Added: " + subFolder.getName());

                        }
                    }
                }

                return null;
            }
        };
    }
}
