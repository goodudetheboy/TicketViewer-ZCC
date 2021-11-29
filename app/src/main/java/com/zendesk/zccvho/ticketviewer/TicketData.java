package com.zendesk.zccvho.ticketviewer;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TicketData {
    List<Ticket>    tickets;
    String          nextPage;
    int             total;

    /**
     * Deserialize the JSON string to a TicketData object for ease of use.
     * 
     * @param jsonString The JSON string
     * @return a corresponding TicketData object
     */
    public static TicketData stringToTicketData(String jsonString) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Ticket.class, new TicketDeserializer());
        gsonBuilder.registerTypeAdapter(TicketData.class, new TicketDataDeserializer());
        Gson gsonDeser = gsonBuilder.create();
        return gsonDeser.fromJson(jsonString, TicketData.class);
    }
}
