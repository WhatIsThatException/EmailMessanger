package main.model.table;

import java.util.Comparator;

/**
 * Created by kpant on 6/26/17.
 */
public class FormatableInteger implements Comparator<FormatableInteger> {

    private int size;

    public FormatableInteger(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        String returnValue;
        if (size < 0) {
            returnValue = "0";
        } else if (size < 1024) {
            returnValue = size + " B";
        } else if (size < (1024 * 1024)) {
            returnValue = size / 1024 + " kB";
        } else {
            returnValue = size / (1024 * 1024) + " MB";
        }
        return returnValue;
    }

    @Override
    public int compare(FormatableInteger o1, FormatableInteger o2) {
        Integer int1 = o1.size;
        Integer int2 = o2.size;
        return int1.compareTo(int2);
    }
}
