package main.model.folder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import main.model.EmailMessageBean;
import main.view.ViewFactory;

/**
 * Created by kpant on 6/24/17.
 */
public class EmailFolderBean<T> extends TreeItem<String> {
    //indicate the topELement
    private boolean topElement = false;
    private int unreadMessageCount;

    //name of emailFolderBean
    private String name;
    private String completeName;

    private ObservableList<EmailMessageBean> data = FXCollections.observableArrayList();

    /**
     * Constructor for top elements
     */
    public EmailFolderBean(String value) {
        super(value, ViewFactory.defaultViewFactory.resolveIcon(value));
        this.name = value;
        this.completeName = value;
        data = null; //top element does not have any content
        topElement = true;
        this.setExpanded(true);
    }

    public EmailFolderBean(String value, String completeName) {
        super(value, ViewFactory.defaultViewFactory.resolveIcon(value));
        this.name = value;
        this.completeName = completeName;
    }

    private void updateValue() {
        if (unreadMessageCount > 0) {
            this.setValue((String) (name + "(" + unreadMessageCount + ")"));
        } else {
            this.setValue(name);
        }
    }

    public void incrementUnreadMessagesCount(int newMessages) {
        unreadMessageCount = unreadMessageCount + newMessages;
        updateValue();
    }

    public void decrementUnreadMessagesCount() {
        unreadMessageCount--;
        updateValue();
    }

    public void addEmail(EmailMessageBean message) {
        data.add(message);
        if (message.isRead()) {
            incrementUnreadMessagesCount(1);
        }
    }

    public boolean isTopElement() {
        return topElement;
    }

    public ObservableList<EmailMessageBean> getData() {
        return data;
    }
}
