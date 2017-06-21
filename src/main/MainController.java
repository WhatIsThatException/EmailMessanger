package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

/**
 * Created by kpant on 6/21/17.
 */
public class MainController implements Initializable{


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

    final ObservableList<EmailMessageBean> data = FXCollections.observableArrayList(
      new EmailMessageBean("Hello from Karan","abc@abc.com",550000 ),
      new EmailMessageBean("Hello from Sita","sita@sita.com",234 ),
      new EmailMessageBean("Hello from rita","rita@rita.com",3214),
      new EmailMessageBean("Hello from Geeta","Geeta@Geeta.com",1414)
    );
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messageRenderer.getEngine().loadContent("<b>lorem ipsum</b>");
        subjectCol.setCellValueFactory(new PropertyValueFactory<EmailMessageBean, String>("subject"));
        senderCol.setCellValueFactory(new PropertyValueFactory<EmailMessageBean, String>("sender"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<EmailMessageBean, String>("size"));
        emailTableView.setItems(data);

        sizeCol.setComparator(new Comparator<String>() {
            Integer int1, int2;
            @Override
            public int compare(String o1, String o2) {
               int1 = EmailMessageBean.formattedValues.get(o1);
               int2 = EmailMessageBean.formattedValues.get(o2);
                return int1.compareTo(int2);
            }
        });
    }


}
