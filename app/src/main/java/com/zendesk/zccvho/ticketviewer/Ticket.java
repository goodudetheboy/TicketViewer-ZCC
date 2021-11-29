package com.zendesk.zccvho.ticketviewer;

import java.util.List;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + status + "] ");
        sb.append("Ticket [" + id + "] ");
        sb.append("(Subject: " + subject + ") ");
        sb.append("- Created at: " + createdAt + " ");
        sb.append(" by: " + requesterId);
        return sb.toString();
    }
}
