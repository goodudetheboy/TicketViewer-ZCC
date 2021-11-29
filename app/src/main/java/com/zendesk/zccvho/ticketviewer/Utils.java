package com.zendesk.zccvho.ticketviewer;

/**
 * Static class that provies utility methods for the TicketViewer app.
 */
public class Utils {
    /**
     * If input string is more than "length" character, only show the first 
     * "length" characters otherwise pad with spaces until 20 characters
     * 
     * @param inputString The input string
     * @param length The length of the output string
     * @return The output string truncated to length or padded with spaces
     */
    public static String prettifyString(String inputString, int length) {
        int strlen = inputString.length(); 
        if (strlen > length) {
            return inputString.substring(0, length);
        } else {
            return inputString + String.format("%1$" + (length - strlen + 1) + "s", " ");
        }
    }
}
