package com.zendesk.zccvho.ticketviewer;

import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

public class Ticket {
    String          assigneId;
    boolean         canBeSolvedByMe;
    List<String>    collaboratorsIds;
    String          createdAt;
    List<String>    customFields;
    String          description;	
    String          dueAt;
    List<String>    emailCCIds;
    int             followupSourceId;
    String          groupId;
    int             id;
    boolean         isPublic;
    String          organizationId;
    String          priority;
    String          recipient;
    String          requesterId;
    boolean         solved;
    String          status;
    String          subject;
    int             ticketFormId;
    String          type;
    String          updatedAt;
    String          url;
    Object          via;

    public Ticket() {
        // empty constructor
    }

    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        String fDesc = WordUtils.wrap(description, 60, "\n", true);

        sb.append("\t From: " + requesterId + "\n");
        sb.append("\t Subject: " + subject + "\n\n");
        sb.append("\t " + fDesc + "\n\n");
        sb.append("\t Created at: " + createdAt + "\t\t Status: " + status + "\n");

        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[" + status + "]  \t");
        sb.append("Ticket [" + id + "] \t");
        sb.append("Subject: " + Utils.prettifyString(subject, 40) + " ");
        sb.append("- Created at: " + createdAt + " ");
        sb.append(" by: " + requesterId);

        return sb.toString();
    }
}
