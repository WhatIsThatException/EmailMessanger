package main.model.table;

import javafx.scene.control.TableRow;

/**
 * Created by kpant on 6/24/17.
 */
public class BoldableRowFactory<T extends AbstractTableItem> extends TableRow<T> {

    @Override
    final protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !item.isRead()) {
            setStyle("-fx-font-weight: bold");
        } else {
            setStyle("");
        }
    }

}
