package main.controller.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import main.model.EmailAccountBean;
import main.model.EmailConstants;
import main.model.folder.EmailFolderBean;

/**
 * Created by kpant on 6/25/17.
 */
public class CreateAndRegisterEmailAccountService extends Service<Integer> {

    private String emailAddress;
    private String password;
    private EmailFolderBean<String> folderRoot;

    public CreateAndRegisterEmailAccountService(String emailAddress, String password, EmailFolderBean<String> folderRoot) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.folderRoot = folderRoot;
    }

    @Override
    protected Task<Integer> createTask() {
        return new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                EmailAccountBean emailAccount = new EmailAccountBean(emailAddress, password);
                if (emailAccount.getLoginState() == EmailConstants.LOGIN_STATE_SUCCEDED) {
                    EmailFolderBean<String> emailFolderBean = new EmailFolderBean<String>(emailAddress);
                    folderRoot.getChildren().add(emailFolderBean);
                    FetchFoldersService fetchFoldersService = new FetchFoldersService(emailFolderBean, emailAccount);
                    fetchFoldersService.start();
                }
                return emailAccount.getLoginState();
            }
        };
    }
}
