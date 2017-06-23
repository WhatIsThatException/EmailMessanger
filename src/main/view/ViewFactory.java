package main.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.controller.AbstractController;
import main.controller.EmailDetailsController;
import main.controller.MainController;
import main.controller.ModelAccess;

/**
 * Created by kpant on 6/23/17.
 */
public class ViewFactory {

    private static final String DEFAULT_CSS = "style.css";
    private static final String EMAIL_DETAIL_LAYOUT_FXML = "EmailDetailLayout.fxml";
    private static final String MAIN_LAYOUT_FXML = "MainLayout.fxml";
    public static ViewFactory defaultViewFactory = new ViewFactory();
    private ModelAccess modelAccess = new ModelAccess();

    private MainController mainController;
    private EmailDetailsController emailDetailsController;


    public Scene getMainScene() {
        mainController = new MainController(modelAccess);
        return initializeScene(MAIN_LAYOUT_FXML, mainController);
    }

    public Scene getEmailDetailsScene() {
        emailDetailsController = new EmailDetailsController(modelAccess);
        return initializeScene(EMAIL_DETAIL_LAYOUT_FXML, emailDetailsController);
    }

    public Node resolveIcon(String treeItemValue) {
        String lowerCaseTreeItemValue = treeItemValue.toLowerCase();
        ImageView returnIcon;
        try {
            if (lowerCaseTreeItemValue.contains("inbox")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/main/view/images/inbox.png")));
            } else if (lowerCaseTreeItemValue.contains("sent")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/main/view/images/sent2.png")));
            } else if (lowerCaseTreeItemValue.contains("spam")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/main/view/images/spam.png")));
            } else if (lowerCaseTreeItemValue.contains("@")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/main/view/images/email.png")));
            } else
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/main/view/images/folder.png")));
        } catch (NullPointerException e) {
            System.out.println("Invalid image location!!!");
            e.printStackTrace();
            returnIcon = new ImageView();
        }
        returnIcon.setFitHeight(16);
        returnIcon.setFitWidth(16);
        return returnIcon;
    }

    private Scene initializeScene(String fxmlPath, AbstractController controller) {
        FXMLLoader loader;
        Parent parent;
        Scene scene;
        try {
            //not FXMLLoader.load(getClass().getResource(fxmlPath)
            loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loader.setController(controller);
            parent = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        scene = new Scene(parent);
        scene.getStylesheets().add(getClass().getResource(DEFAULT_CSS).toExternalForm());
        return scene;

    }
}
