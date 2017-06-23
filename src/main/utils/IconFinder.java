package main.utils;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by kpant on 6/22/17.
 */
public class IconFinder {
    private Node resolveIcon(String treeItemValue) {
        String lowerCaseTreeItemValue = treeItemValue.toLowerCase();
        ImageView returnIcon;
        if (lowerCaseTreeItemValue.contains("inboox")) {
            returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/home/kpant/main.view.images/inbox.png")));
        } else if (lowerCaseTreeItemValue.contains("sent")) {
            returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/home/kpant/main.view.images/sent2.png")));
        } else if (lowerCaseTreeItemValue.contains("spam")) {
            returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/home/kpant/main.view.images/spam.png")));
        } else if (lowerCaseTreeItemValue.contains("@")) {
            returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/home/kpant/main.view.images/email.png")));
        } else {
            returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/home/kpant/main.view.images/folder.png")));
        }

        return returnIcon;
    }
}
