package main.controller.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.Folder;
import java.util.List;

/**
 * Created by kpant on 6/25/17.
 */
public class FolderUpdaterService extends Service<Void> {

    private List<Folder> foldersList;

    public FolderUpdaterService(List<Folder> foldersList) {
        this.foldersList = foldersList;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (; ; ) {
                    try {
                        Thread.sleep(10000);
                        if (FetchFoldersService.noServicesActive()) {
                            System.out.println("Checking For Emails");
                            for (Folder folder : foldersList) {
                                if (folder.getType() != Folder.HOLDS_FOLDERS && folder.isOpen()) {
                                    folder.getMessageCount();
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}
