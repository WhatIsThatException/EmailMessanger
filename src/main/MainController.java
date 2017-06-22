package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

/**
 * Created by kpant on 6/21/17.
 */
public class MainController implements Initializable{
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

    @FXML
    void Button1Action(ActionEvent event) {
        System.out.println("button1 clicked");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        root.setGraphic(resolveIcon(root.getValue()));
        TreeItem<String> inBox = new TreeItem("inBox", resolveIcon("inbox"));
        TreeItem<String> sent = new TreeItem("sent", resolveIcon("sent"));

        TreeItem<String> subItem = new TreeItem<>("subItem", resolveIcon(""));
        TreeItem<String> subItem1 = new TreeItem<>("subItem1", resolveIcon(""));
        sent.getChildren().addAll(subItem, subItem1);

        TreeItem<String> spam = new TreeItem("spam", resolveIcon("spam"));
        TreeItem<String> trash = new TreeItem("trash", resolveIcon("trash"));
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
            }
        });

        showDetails.setOnAction(e -> {
            System.out.println("menuItem clicked");
        });
    }

    private Node resolveIcon(String treeItemValue) {
        String lowerCaseTreeItemValue = treeItemValue.toLowerCase();
        ImageView returnIcon;
        try {
            if (lowerCaseTreeItemValue.contains("inbox")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/inbox.png")));
            } else if (lowerCaseTreeItemValue.contains("sent")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/sent2.png")));
            } else if (lowerCaseTreeItemValue.contains("spam")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/spam.png")));
            } else if (lowerCaseTreeItemValue.contains("@")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/email.png")));
            } else
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/folder.png")));
        } catch (NullPointerException e) {
            System.out.println("Invalid image location!!!");
            e.printStackTrace();
            returnIcon = new ImageView();
        }
        returnIcon.setFitHeight(16);
        returnIcon.setFitWidth(16);
        return returnIcon;
    }

}
