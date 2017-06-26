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
import main.controller.services.FolderUpdaterService;
import main.controller.services.MessageRendererService;
import main.controller.services.SaveAttachmentsService;
import main.model.EmailMessageBean;
import main.model.folder.EmailFolderBean;
import main.model.table.BoldableRowFactory;
import main.model.table.FormatableInteger;
import main.view.ViewFactory;

import java.net.URL;
import java.util.Date;
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
    private TableColumn<EmailMessageBean, FormatableInteger> sizeCol;
    @FXML
    private TableColumn<EmailMessageBean, String> senderCol;
    @FXML
    private TableColumn<EmailMessageBean, String> subjectCol;
    @FXML
    private WebView messageRenderer;

    @FXML
    private Label downAttachLabel;
    @FXML
    private TableColumn<EmailMessageBean, Date> dateCol;

    @FXML
    private ProgressBar downAttachProgress;

    private SaveAttachmentsService saveAttachmentsService;
    @FXML
    private Button downAttachBtn;
    private MessageRendererService messageRendererService;
    public MainController(ModelAccess modelAccess) {
        super(modelAccess);
    }


    @FXML
    void Button1Action(ActionEvent event) {
        Scene scene = ViewFactory.defaultViewFactory.getComposeMessageScene();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void downAttachBtnAction(ActionEvent event) {
        System.out.println("Download Attachment btn clicked");
        EmailMessageBean message = emailTableView.getSelectionModel().getSelectedItem();
        if (message != null && message.hasAttachments()) {
            System.out.println("MEssage has attachment");
            saveAttachmentsService.setMessageToDownload(message);
            saveAttachmentsService.restart();
        }
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
        downAttachProgress.setVisible(false);
        downAttachLabel.setVisible(false);
        messageRendererService = new MessageRendererService(messageRenderer.getEngine());
        saveAttachmentsService = new SaveAttachmentsService(downAttachProgress, downAttachLabel);
        downAttachProgress.progressProperty().bind(saveAttachmentsService.progressProperty());
        FolderUpdaterService folderUpdaterService = new FolderUpdaterService(getModelAccess().getFoldersList());
        folderUpdaterService.start();
        ViewFactory viewFactory = ViewFactory.defaultViewFactory;

        emailTableView.setRowFactory(e -> new BoldableRowFactory<>());
        subjectCol.setCellValueFactory(new PropertyValueFactory<EmailMessageBean, String>("subject"));
        senderCol.setCellValueFactory(new PropertyValueFactory<EmailMessageBean, String>("sender"));
        dateCol.setCellValueFactory(new PropertyValueFactory<EmailMessageBean, Date>("date"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<EmailMessageBean, FormatableInteger>("size"));


        //BUG: size doesn't get it's default comparator overridden, so we do it by hand.
        sizeCol.setComparator(new FormatableInteger(0));
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
                new CreateAndRegisterEmailAccountService("fakeaccouns377@gmail.com", "completelyfake", root, getModelAccess());
        createAndRegisterEmailAccountService1.start();

        CreateAndRegisterEmailAccountService createAndRegisterEmailAccountService2 =
                new CreateAndRegisterEmailAccountService("fakeaccouns377@gmail.com", "completelyfake", root, getModelAccess());
        createAndRegisterEmailAccountService2.start();

        emailTableView.setOnMouseClicked(e -> {
            EmailMessageBean message = emailTableView.getSelectionModel().getSelectedItem();
            if (message != null) {
                getModelAccess().setSelectedMessage(message);
                messageRendererService.setMessageToRender(message);
                messageRendererService.restart();
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
