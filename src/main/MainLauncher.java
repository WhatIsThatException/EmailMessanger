package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.model.EmailAccountBean;
import main.model.EmailMessageBean;
import main.view.ViewFactory;

public class MainLauncher extends Application {

    public static void main(String[] args) {
        launch(args);
        EmailAccountBean emailAccountBean = new EmailAccountBean("yourEmailAddress", "password");
        ObservableList<EmailMessageBean> data = FXCollections.observableArrayList();
        emailAccountBean.addEmailsToData(data);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewFactory viewFactory = ViewFactory.defaultViewFactory;
        Scene scene = viewFactory.getMainScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
