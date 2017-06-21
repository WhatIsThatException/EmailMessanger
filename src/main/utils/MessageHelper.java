package main.utils;

import main.EmailMessageBean;

/**
 * Created by kpant on 6/21/17.
 */
public final class MessageHelper {


    public static String formatSize(int size){
        String returnValue;
        if(size < 0) {
            returnValue = "0";
        } else if(size < 1024) {
            returnValue = size + " B";
        } else if(size < (1024*1024)) {
            returnValue = size/1024 + " kB";
        } else {
            returnValue = size/(1024*1024) + " MB";
        }
        EmailMessageBean.formattedValues.put(returnValue, size);
        return returnValue;
    }
}
