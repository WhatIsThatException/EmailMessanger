package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;
import main.Singleton;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kpant on 6/22/17.
 */
public class EmailDetailsController implements Initializable {

    @FXML
    private Label senderLabel;

    @FXML
    private WebView webView;

    @FXML
    private Label subjectLabel;
    private Singleton singleton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        singleton = Singleton.getInstance();
        System.out.println("EmailDetailsController initialized");

        subjectLabel.setText("Subject: " + singleton.getMessage().getSubject());
        senderLabel.setText("Sender: " + singleton.getMessage().getSender());

        webView.getEngine().loadContent(singleton.getMessage().getContent());
    }
}
