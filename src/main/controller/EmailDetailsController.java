package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;
import main.model.EmailMessageBean;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kpant on 6/22/17.
 */
public class EmailDetailsController extends AbstractController implements Initializable {

    @FXML
    private Label senderLabel;

    @FXML
    private WebView webView;

    @FXML
    private Label subjectLabel;

    public EmailDetailsController(ModelAccess modelAccess) {
        super(modelAccess);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("EmailDetailsController initialized");
        EmailMessageBean selectedMessage = getModelAccess().getSelectedMessage();
        subjectLabel.setText("Subject: " + selectedMessage.getSubject());
        senderLabel.setText("Sender: " + selectedMessage.getSender());

        webView.getEngine().loadContent(getModelAccess().getSelectedMessage().getContent());
    }
}
