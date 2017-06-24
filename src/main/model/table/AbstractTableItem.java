package main.model.table;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * Created by kpant on 6/24/17.
 */
public abstract class AbstractTableItem {
    private final SimpleBooleanProperty read = new SimpleBooleanProperty();

    public AbstractTableItem(boolean isRead) {
        this.setRead(isRead);
    }

    public SimpleBooleanProperty getReadProperty() {
        return read;
    }

    public boolean isRead() {
        return read.get();
    }

    public void setRead(boolean isRead) {
        read.set(isRead);
    }

}
