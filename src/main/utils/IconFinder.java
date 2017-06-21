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
            returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/home/kpant/images/inbox.png")));
        } else if (lowerCaseTreeItemValue.contains("sent")) {
            returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/home/kpant/images/sent2.png")));
        } else if (lowerCaseTreeItemValue.contains("spam")) {
            returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/home/kpant/images/spam.png")));
        } else if (lowerCaseTreeItemValue.contains("@")) {
            returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/home/kpant/images/email.png")));
        } else
            returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/home/kpant/images/folder.png")));

        return returnIcon;
    }
}
