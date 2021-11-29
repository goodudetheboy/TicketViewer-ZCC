package com.zendesk.zccvho.ticketviewer;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TicketData {
    List<Ticket>    tickets;
    String          nextPage;
    int             total;

    /**
     * Get a specific ticket from the list of tickets using the ticket id.
     * 
     * @param ticketId The id of the ticket to get.
     * @return The ticket with the given id.
     */
    public Ticket findTicketById(int ticketId) {
        for (Ticket ticket : tickets) {
            if (ticket.id == ticketId) {
                return ticket;
            }
        }
        return null;
    }

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Ticket ticket : tickets) {
            sb.append(ticket.toString() + "\n");
        }
        return sb.toString();
    }
}
