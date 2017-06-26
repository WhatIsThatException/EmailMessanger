package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import main.controller.services.EmailSenderService;
import main.model.EmailConstants;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by kpant on 6/26/17.
 */
public class ComposeMessageController extends AbstractController implements Initializable {

    @FXML
    private Label attachmentsLabel;

    @FXML
    private ChoiceBox<String> senderChoice;

    @FXML
    private TextField recipientField;

    @FXML
    private TextField subjectField;

    @FXML
    private Label errorLabel;

    @FXML
    private HTMLEditor composeArea;

    private List<File> attachments = new ArrayList<>();

    public ComposeMessageController(ModelAccess modelAccess) {
        super(modelAccess);
    }

    @FXML
    void attachBtnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            attachments.add(selectedFile);
            attachmentsLabel.setText(attachmentsLabel.getText() + selectedFile.getName() + "; ");
        }
    }

    @FXML
    void sendBtnAction(ActionEvent event) {
        errorLabel.setText("");
        EmailSenderService emailSenderService = new EmailSenderService(getModelAccess().getEmailAccountByName(
                senderChoice.getValue()),
                subjectField.getText(),
                recipientField.getText(), composeArea.getHtmlText(), attachments);
        emailSenderService.restart();
        emailSenderService.setOnSucceeded(e -> {
            if (emailSenderService.getValue() == EmailConstants.MESSAGE_SENT_OK) {
                errorLabel.setText("Message sent successfully");
            } else {
                errorLabel.setText("Message sending error..");

            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        senderChoice.setItems(getModelAccess().getEmailAccountNames());
        senderChoice.setValue(getModelAccess().getEmailAccountNames().get(0));
    }
}
