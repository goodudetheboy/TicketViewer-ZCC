package com.zendesk.zccvho.ticketviewer;

public class Utils {
    public static String prettifyString(String inputString, int length) {
        int strlen = inputString.length(); 
        if (strlen > length) {
            return inputString.substring(0, length);
        } else {
            return inputString + String.format("%1$" + (length - strlen) + "s", " ");
        }
    }
}
