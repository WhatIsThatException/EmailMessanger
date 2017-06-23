package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.view.ViewFactory;

public class MainLauncher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewFactory viewFactory = new ViewFactory();
        Scene scene = viewFactory.getMainScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewFactory viewFactory = new ViewFactory();
        Scene scene = viewFactory.getMainScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
