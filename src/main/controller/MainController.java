package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import main.controller.services.CreateAndRegisterEmailAccountService;
import main.model.EmailMessageBean;
import main.model.folder.EmailFolderBean;
import main.model.table.BoldableRowFactory;
import main.view.ViewFactory;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

/**
 * Created by kpant on 6/21/17.
 */
public class MainController extends AbstractController implements Initializable {

    private MenuItem showDetails = new MenuItem("show Detils");


    // TreeView works with TreeItem
    @FXML
    private TreeView<String> emailsFolderTreeView;
    @FXML
    private TableView<EmailMessageBean> emailTableView;
    @FXML
    private TableColumn<EmailMessageBean, String> sizeCol;
    @FXML
    private TableColumn<EmailMessageBean, String> senderCol;
    @FXML
    private TableColumn<EmailMessageBean, String> subjectCol;
    @FXML
    private WebView messageRenderer;
    @FXML
    private Button Button1;

    public MainController(ModelAccess modelAccess) {
        super(modelAccess);
    }


    @FXML
    void Button1Action(ActionEvent event) {



    }

    @FXML
    void changeReadAction() {
        EmailMessageBean message = getModelAccess().getSelectedMessage();
        if (message != null) {
            boolean value = message.isRead();

            message.setRead(!value);
            EmailFolderBean<String> selectedFolder = getModelAccess().getSelectedFolder();
            if (selectedFolder != null) {
                if (value) {
                    selectedFolder.incrementUnreadMessagesCount(1);
                } else {
                    selectedFolder.decrementUnreadMessagesCount();
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ViewFactory viewFactory = ViewFactory.defaultViewFactory;

        emailTableView.setRowFactory(e -> new BoldableRowFactory<>());
        subjectCol.setCellValueFactory(new PropertyValueFactory<EmailMessageBean, String>("subject"));
        senderCol.setCellValueFactory(new PropertyValueFactory<EmailMessageBean, String>("sender"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<EmailMessageBean, String>("size"));

        sizeCol.setComparator(new Comparator<String>() {
            Integer int1, int2;

            @Override
            public int compare(String o1, String o2) {
                int1 = EmailMessageBean.formattedValues.get(o1);
                int2 = EmailMessageBean.formattedValues.get(o2);
                return int1.compareTo(int2);
            }
        });


        emailTableView.setContextMenu(new ContextMenu(showDetails));


        EmailFolderBean<String> root = new EmailFolderBean<String>("");

        emailsFolderTreeView.setRoot(root);
        emailsFolderTreeView.setShowRoot(false);
        emailsFolderTreeView.setOnMouseClicked(e -> {
            //item value is the value in TreeItem constructor, fist Value
            EmailFolderBean<String> item = (EmailFolderBean<String>) emailsFolderTreeView.getSelectionModel().getSelectedItem();
            if (item != null && !item.isTopElement()) {
                emailTableView.setItems(item.getData());
                getModelAccess().setSelectedFolder(item);
                //clear the selected message
                getModelAccess().setSelectedMessage(null);
            }

        });

        CreateAndRegisterEmailAccountService createAndRegisterEmailAccountService1 =
                new CreateAndRegisterEmailAccountService("fakeaccouns377@gmail.com", "completelyfake", root);
        createAndRegisterEmailAccountService1.start();

        CreateAndRegisterEmailAccountService createAndRegisterEmailAccountService2 =
                new CreateAndRegisterEmailAccountService("fakeaccouns377@gmail.com", "completelyfake", root);
        createAndRegisterEmailAccountService2.start();

        emailTableView.setOnMouseClicked(e -> {
            EmailMessageBean message = emailTableView.getSelectionModel().getSelectedItem();
            if (message != null) {
                messageRenderer.getEngine().loadContent(message.getContent());
                getModelAccess().setSelectedMessage(message);
            }
        });

        showDetails.setOnAction(e -> {
            Scene scene = viewFactory.getEmailDetailsScene();
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        });
    }


}
