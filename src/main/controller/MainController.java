package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import main.model.EmailMessageBean;
import main.model.SampleData;
import main.model.table.BoldableRowFactory;
import main.view.ViewFactory;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

/**
 * Created by kpant on 6/21/17.
 */
public class MainController extends AbstractController implements Initializable {

    private ViewFactory viewFactory = ViewFactory.defaultViewFactory;
    private MenuItem showDetails = new MenuItem("show Detils");

    private SampleData sampleData = new SampleData();

    // TreeView works with TreeItem
    @FXML
    private TreeView<String> emailsFolderTreeView;
    private TreeItem<String> root = new TreeItem<>();
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
        System.out.println("button1 clicked");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        emailTableView.setRowFactory(e -> new BoldableRowFactory<EmailMessageBean>());
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


        root.setValue("example@yahoo.com");
        root.setGraphic(viewFactory.resolveIcon(root.getValue()));
        TreeItem<String> inBox = new TreeItem("inBox", viewFactory.resolveIcon("inbox"));
        TreeItem<String> sent = new TreeItem("sent", viewFactory.resolveIcon("sent"));

        TreeItem<String> subItem = new TreeItem<>("subItem", viewFactory.resolveIcon(""));
        TreeItem<String> subItem1 = new TreeItem<>("subItem1", viewFactory.resolveIcon(""));
        sent.getChildren().addAll(subItem, subItem1);

        TreeItem<String> spam = new TreeItem("spam", viewFactory.resolveIcon("spam"));
        TreeItem<String> trash = new TreeItem("trash", viewFactory.resolveIcon("trash"));
        root.getChildren().addAll(inBox, sent, spam, trash);
        root.setExpanded(true);

        emailTableView.setContextMenu(new ContextMenu(showDetails));
        emailsFolderTreeView.setRoot(root);

        emailsFolderTreeView.setOnMouseClicked(e -> {
            //item value is the value in TreeItem constructor, fist Value
            TreeItem<String> item = emailsFolderTreeView.getSelectionModel().getSelectedItem();
            System.out.println("HEREHERE");
            System.out.println("item = " + item);
            if (item != null) {
                emailTableView.setItems(sampleData.emailFolders.get(item.getValue()));
            }

        });

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
