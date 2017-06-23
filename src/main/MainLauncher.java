package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.view.ViewFactory;

public class MainLauncher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewFactory viewFactory = ViewFactory.defaultViewFactory;
        Scene scene = viewFactory.getMainScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
