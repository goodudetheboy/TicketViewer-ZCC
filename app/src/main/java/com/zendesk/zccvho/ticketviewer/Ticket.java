package com.zendesk.zccvho.ticketviewer;

import java.util.List;

public class Ticket {
    int             assigneId;
    boolean         canBeSolvedByMe;
    List<String>    collaboratorsIds;
    String          createdAt;
    List<String>    customFields;
    String          description;	
    String          dueAt;
    List<String>    emailCCIds;
    int             followupSourceId;
    int             groupId;
    int             id;
    boolean         isPublic;
    int             organizationId;
    String          priority;
    String          recipient;
    int             requesterId;
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
        sb.append("Ticket [" + id + "] ");
        sb.append("(" + subject + ") ");
        sb.append("- Created at: " + createdAt);
        return sb.toString();
    }
}
